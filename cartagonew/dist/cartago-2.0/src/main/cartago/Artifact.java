/**
 * CArtAgO - DEIS, University of Bologna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package cartago;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.io.*;

/**
 * Base class for defining artifacts.
 * 
 * @author aricci
 * 
 */
public abstract class Artifact {

	// private Logger logger = Logger.getLogger("log");

	private ArtifactId id;

	private WorkspaceKernel env;

	protected OpId thisOpId;
	private OpExecutionFrame opExecFrame;
	private ArrayList<OpExecutionFrame> opsInExecution;

	private HashMap<String, Method> guardMap;
	private HashMap<String, OpDescriptor> operationMap;

	private int obsPropId;
	private ObsPropMap obsPropertyMap;
	private HashMap<String, ArtifactOutPort> outPortsMap;

	private ReentrantLock lock;
	private Condition guards;

	private InterArtifactCallback opCallback;
	private Manual manual;
	private java.util.concurrent.atomic.AtomicInteger opIds;
	
	final void bind(ArtifactId id, WorkspaceKernel env) throws CartagoException {
		this.id = id;
		this.env = env;
		obsPropId = 0;
		opIds = new java.util.concurrent.atomic.AtomicInteger(0);

		lock = new ReentrantLock(true);
		guards = lock.newCondition();

		opsInExecution = new ArrayList<OpExecutionFrame>();
		guardMap = new HashMap<String, Method>();
		operationMap = new HashMap<String, OpDescriptor>();
		outPortsMap = new HashMap<String, ArtifactOutPort>();

		obsPropertyMap = new ObsPropMap();
		opCallback = new InterArtifactCallback(this.lock);

		if (getClass().isAnnotationPresent(ARTIFACT_INFO.class)) {
			ARTIFACT_INFO info = getClass().getAnnotation(ARTIFACT_INFO.class);

			for (OUTPORT port : info.outports()) {
				outPortsMap.put(port.name(), new ArtifactOutPort(port.name()));
			}

			/*
			 * loading the manual
			 */
			if (!info.manual_file().equals("")) {
				try {
					String src = env.loadManualSrc(info.manual_file());
					manual = env.registerManual(this.getClass().getName(), src);
					// log("artifact manual loaded "+manual.getName());
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("LOCAL PATH: "
							+ new File(".").getAbsolutePath());
				}
			} else {
				manual = Manual.EMPTY_MANUAL;
			}
		}

		// setting ops and obs properties
		try {
			setupOperations();
		} catch (CartagoException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Set up artifact operations.
	 * 
	 * Method called during artifact initialization to set up operations. By
	 * default, reflection is used to link operations to annotated methods.
	 * 
	 * @throws CartagoException
	 */
	protected void setupOperations() throws CartagoException {
		Class<?> c = getClass();
		while (c != null) {
			Method[] methods = c.getDeclaredMethods();
			for (Method m : methods) {
				// log("method "+m.getName());
				// log("annotations "+m.getAnnotations().length);
				if (m.isAnnotationPresent(OPERATION.class)) {
					OPERATION op = m.getAnnotation(OPERATION.class);
					String guard = op.guard();
					ArtifactGuardMethod guardBody = null;
					if (!guard.equals("")) {
						Method guardMethod = getMethodInHierarchy(guard, m
								.getParameterTypes());
						if (guardMethod == null) {
							throw new CartagoException("invalid guard: "
									+ guard);
						} else {
							guardBody = new ArtifactGuardMethod(this,
									guardMethod);
						}
					}
					String name = null;
					if (!m.isVarArgs()) {
						name = Artifact.getOpKey(m.getName(), m
								.getParameterTypes().length);
					} else {
						name = Artifact.getOpKey(m.getName(), -1);
					}
					OpDescriptor opdesc = new OpDescriptor(
							new ArtifactOpMethod(this, m), guardBody,
							OpDescriptor.OpType.UI);
					// log("registering "+name);
					operationMap.put(name, opdesc);
				} else if (m.isAnnotationPresent(LINK.class)) {
					LINK op = m.getAnnotation(LINK.class);
					String guard = op.guard();
					ArtifactGuardMethod guardBody = null;
					if (!guard.equals("")) {
						Method guardMethod = getMethodInHierarchy(guard, m
								.getParameterTypes());
						if (guardMethod == null) {
							throw new CartagoException("invalid guard: "
									+ guard);
						} else {
							guardBody = new ArtifactGuardMethod(this,
									guardMethod);
						}
					}
					String name = Artifact.getOpKey(m.getName(), m
							.getParameterTypes().length);
					OpDescriptor opdesc = new OpDescriptor(
							new ArtifactOpMethod(this, m), guardBody,
							OpDescriptor.OpType.LINK);
					// log("registering "+name);
					operationMap.put(name, opdesc);
				} else if (m.isAnnotationPresent(INTERNAL_OPERATION.class)) {
					INTERNAL_OPERATION op = m
							.getAnnotation(INTERNAL_OPERATION.class);
					String guard = op.guard();
					ArtifactGuardMethod guardBody = null;
					if (!guard.equals("")) {
						Method guardMethod = getMethodInHierarchy(guard, m
								.getParameterTypes());
						if (guardMethod == null) {
							throw new CartagoException("invalid guard: "
									+ guard);
						} else {
							guardBody = new ArtifactGuardMethod(this,
									guardMethod);
						}
					}
					String name = Artifact.getOpKey(m.getName(), m
							.getParameterTypes().length);
					OpDescriptor opdesc = new OpDescriptor(
							new ArtifactOpMethod(this, m), guardBody,
							OpDescriptor.OpType.INTERNAL);
					// log("registering "+name);
					operationMap.put(name, opdesc);
				} else if (m.isAnnotationPresent(GUARD.class)) {
					String name = Artifact.getOpKey(m.getName(), m
							.getParameterTypes().length);
					guardMap.put(name, m);
				}
			}
			c = c.getSuperclass();
		}
	}

	/**
	 * Get the name of the file containing the manual for the specified artifact
	 * template, by accessing to ARTIFACT_INFO annotation.
	 * 
	 * @param artType
	 *            artifact template
	 * @return file name
	 * @throws UnknownArtifactException
	 */
	public static String getManualSrcFile(String artType)
			throws UnknownArtifactException {
		try {
			Class<Artifact> cl = (Class<Artifact>) Class.forName(artType);
			if (cl.isAnnotationPresent(ARTIFACT_INFO.class)) {
				ARTIFACT_INFO info = cl.getAnnotation(ARTIFACT_INFO.class);
				return info.manual_file();
			} else {
				return "";
			}
		} catch (Exception ex) {
			throw new UnknownArtifactException(artType);
		}
	}

	/**
	 * Get the key of an operation, given its name and n args.
	 * 
	 * The key is used in maps.
	 * 
	 * @param opName
	 *            op name
	 * @param nargs
	 *            number of parameters
	 */
	public static String getOpKey(String opName, int nargs) {
		if (nargs >= 0) {
			return opName + "/" + nargs;
		} else {
			// var args
			return opName + "/_";
		}
	}

	private void doInit(ArtifactConfig cfg) throws CartagoException {
		try {
			lock.lock();
			Method m = getMethodInHierarchy2("init", cfg.getTypes());
			if (m != null) {
				try {
					m.setAccessible(true);
					m.invoke(this, cfg.getValues());
					commitObsStateChanges();
				} catch (Exception ex) {
					ex.printStackTrace();
					obsPropertyMap.rollbackChanges();
					throw new CartagoException("init_failed");
				}
			} else if (cfg.getTypes().length > 0) {
				throw new CartagoException("init_failed");
			}
		} finally {
			lock.unlock();
		}
	}

	private Method getMethodInHierarchy(String name, Class<?>[] types) {
		Class<?> cl = getClass();
		do {
			try {
				return cl.getDeclaredMethod(name, types);
			} catch (Exception ex) {
				cl = cl.getSuperclass();
			}
		} while (cl != null);
		return null;
	}

	private Method getMethodInHierarchy2(String name, Class<?>[] types) {
		Class<?> cl = getClass();
		do {
			Method[] methods = cl.getDeclaredMethods();
			for (Method m : methods) {
				if (m.getName().equals(name)
						&& m.getParameterTypes().length == types.length) {
					return m;
				}
			}
			cl = cl.getSuperclass();
		} while (cl != null);
		return null;
	}

	/**
	 * Core method that executes an artifact operation. Called by the kernel
	 * through the adapter.
	 * 
	 * @param info
	 * @throws CartagoException
	 */
	private void doOperation(OpExecutionFrame info) throws CartagoException {
		try {
			lock.lock();
			Op op = info.getOperation();
			// log("inside doOperation "+op.getName()+" "+info.getAgentBodyId().getAgentName());
			String name = op.getName();
			// System.out.println("LOOKING FOR "+name+"_"+param.getValues().length);
			OpDescriptor opDesc = operationMap.get(Artifact.getOpKey(name, op
					.getParamValues().length));
			boolean varargs = false;
			if (opDesc == null) {
				// try with var args op
				// log("doOp with varargs: "+op);
				opDesc = operationMap.get(Artifact.getOpKey(name, -1));
				if (opDesc == null) {
					if (!info.isInternalOp()) {
						info.notifyOpFailed("Unknown operation", new Tuple("unknown_aligned"));
					}
					return;
				}
				varargs = true;
			}

			IAlignmentTest test = info.getAlignmentTest();
			IArtifactOp opBody = null;
			IArtifactGuard guardBody = null;
			opBody = opDesc.getOp();
			guardBody = opDesc.getGuard();
			Object[] params = null;
			if (!varargs) {
				params = op.getParamValues();
			} else {
				Object[] flat = op.getParamValues();
				int len = opBody.getNumParameters();
				params = new Object[len];
				int var = flat.length - len + 1;
				Object[] varlist = new Object[var];
				for (int i = 0; i < len - 1; i++) {
					params[i] = flat[i];
				}
				params[len - 1] = varlist;
				for (int i = 0; i < var; i++) {
					varlist[i] = flat[len - 1 + i];
				}
				// log("prepared params: "+params.length);
			}
			try {
				// check guards
				boolean guardOK = true;
				if (guardBody != null) {
					guardOK = guardBody.eval(params);
					while (!guardOK) {
						guards.await();
						guardOK = guardBody.eval(params);
					}
				}

				/*
				 * Alignment test: check if the observable state of the artifact
				 * matches the one expected by the user
				 */
				if (test != null) {
					boolean aligned = test.match(this.obsPropertyMap);
					if (!aligned) {
						info.notifyOpFailed("Test alignment failed", new Tuple("not_aligned"));
						return;
					}
				}

				opExecFrame = info;
				thisOpId = opExecFrame.getOpId();
				ICartagoLoggerManager log = env.getLoggerManager();
				try {
					if (log.isLogging()) {
						log.logArtifactOperationServed(System.currentTimeMillis(), this.thisOpId, id);
					}
					try {
						opBody.exec(params);	
						commitObsStateChanges();
					} catch (InvocationTargetException ex) {
						if (!(ex.getTargetException() instanceof OperationFailedException)) {
							throw ex;
						}
					}
					if (!info.isInternalOp()) {
						if (!info.isFailed()) {
							
							// notify operation completed successfully
							if (!info.completionNotified()){
								info.notifyOpCompletion();
							}
							
							if (log.isLogging()) {
								log.logArtifactOperationCompleted(System.currentTimeMillis(),
												this.thisOpId, id);
							}
						} else {
							//log("OP FAILED: "+info.getFailureMsg()+" "+info.getFailureReason());
							obsPropertyMap.rollbackChanges();
							info.notifyOpFailed();
							if (log.isLogging()) {
								log.logArtifactOperationFailed(System.currentTimeMillis(),
												this.thisOpId, id);
							}
						}
					}
				} catch (Exception ex) {
					/*
					ex.printStackTrace();
					System.out.println("expected param length: "+opBody.getNumParameters());
					for (Object p:params){
						System.out.println("ACTUAL PARAM "+p+" "+p.getClass());
					}
					for (Object p:((ArtifactOpMethod)opBody).getMethod().getParameterTypes()){
						System.out.println("EXPECTED PARAM "+p+" "+p.getClass());
					}
					*/
					
					if (!info.isInternalOp()) {
						info.setFailed("Unknown Operation", new Tuple(
								"unknown_operation", opBody.getName() + "/"
										+ opBody.getNumParameters()));
						info.notifyOpFailed();
					}
					if (log.isLogging()) {
						log.logArtifactOperationFailed(System.currentTimeMillis(), this.thisOpId, id);
					}
				}
			} catch (Exception ex) {
				// ex.printStackTrace();
				info.setFailed("Artifact internal error", new Tuple(
						"internal_error", opBody.getName() + "/"
								+ opBody.getNumParameters()));
				info.notifyOpFailed();
				ICartagoLoggerManager log = env.getLoggerManager();
				if (log.isLogging()) {
					log.logArtifactOperationFailed(System.currentTimeMillis(),
							this.thisOpId, id);
				}
			}
		} finally {
			guards.signalAll();
			lock.unlock();
		}
	}

	/*
	 * Commit and make it observable the obs state 
	 */
	private void commitObsStateChanges(){
		//log("committing obs state changed:");
		ArtifactObsProperty[] changed = obsPropertyMap.getPropsChanged();
		ArtifactObsProperty[] added = obsPropertyMap.getPropsAdded();
		ArtifactObsProperty[] removed = obsPropertyMap.getPropsRemoved();	
		try {
			if (changed != null || added != null || removed != null){
				env.notifyObsEvent(id, null, changed, added, removed);
				guards.signalAll();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		obsPropertyMap.commitChanges();
	}
	
	/*
	 * Commit and make it observable the obs state and a signal
	 */
	private void commitObsStateChangesAndSignal(AgentId target, Tuple signal){
		//log("committing obs state changed:");
		ArtifactObsProperty[] changed = obsPropertyMap.getPropsChanged();
		ArtifactObsProperty[] added = obsPropertyMap.getPropsAdded();
		ArtifactObsProperty[] removed = obsPropertyMap.getPropsRemoved();	
		try {
			if (target == null){
				env.notifyObsEvent(id, signal, changed, added, removed);
			} else {
				env.notifyObsEventToAgent(id, target, signal, changed, added, removed);
			}
			guards.signalAll();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		obsPropertyMap.commitChanges();
	}
	
	// inherited

	@OPERATION void observeProperty(String name, OpFeedbackParam<ArtifactObsProperty> prop){
		ObsProperty p = obsPropertyMap.getByName(name);
		if (p != null) {
			prop.set(p.getUserCopy());
		} else {
			failed("Property not found "+name);
		}
	}
	
	// API for programming artifacts

	/**
	 * Get the artifact unique identifier
	 */
	protected ArtifactId getId() {
		return id;
	}

	OpExecutionFrame getOpFrame(){
		return opExecFrame;
	}

	
	/**
	 * Get the identifier of the current user
	 * 
	 */
	protected AgentId getOpUserId() {
		return this.opExecFrame.getAgentId();
	}

	/**
	 * Get the name of the current user
	 * 
	 */
	protected String getOpUserName() {
		return this.opExecFrame.getAgentId().getAgentName();
	}

	protected void commit(){
		commitObsStateChanges();
	}
	
	/**
	 * Primitive to generate an event.
	 */
	protected void signal(String type, Object... objs) {
		try {
			commitObsStateChangesAndSignal(null, new Tuple(type, objs));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Error in generating the event.");
		}
	}

	/**
	 * Primitive to generate an event.
	 */
	protected void signal(AgentId target, String type, Object... objs) {
		try {
			commitObsStateChangesAndSignal(target,new Tuple(type, objs));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Error in generating the event.");
		}
	}
	/**
	 * Terminate current operation with a failure
	 * 
	 * @param reason
	 *            description of the failure
	 */
	protected void failed(String reason) {
		this.opExecFrame.setFailed(reason, null);
		throw new OperationFailedException();
	}

	/**
	 * Terminate current operation with a failure
	 * 
	 * @param reason
	 *            description of the failure
	 * @param tupleDesc
	 *            functor of a machine readable tuple describing the failure
	 * @param params
	 *            parameters of a machine readable tuple describing the failure
	 */
	protected void failed(String reason, String tupleDesc, Object... params) {
		this.opExecFrame.setFailed(reason, new Tuple(tupleDesc, params));
		throw new OperationFailedException();
	}

	// Observable property management functions
	
	/**
	 * Add an observable property
	 * 
	 * @param name
	 *            name of the property
	 * @param values
	 *            values of the property
	 */
	protected void defineObsProperty(String name, Object... values) {			
			try {
				String fullId="obs_id_"+this.id.getId()+"_"+obsPropId;
				ObsProperty prop = new ObsProperty(obsPropertyMap,obsPropId, fullId, name, values); 
				obsPropertyMap.add(prop);
				obsPropId++;
				//env.notifyObsPropAddedEvent(id, prop.getUserCopy());
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new IllegalArgumentException(
						"invalid observable property: " + name);
			}
	}

	/**
	 * Remove an observable property
	 * 
	 * @param name
	 *            name of the property
	 */
	protected void removeObsProperty(String name) {
			ObsProperty prop = obsPropertyMap.removeByName(name);
			if (prop == null){
				throw new IllegalArgumentException(
						"invalid observable property: " + name);
			}
	}


	/**
	 * Remove an observable property
	 * 
	 * @param name name of the property
	 * @param values arguments of the property
	 */
	protected void removeObsPropertyByTemplate(String name, Object... values) {
			ObsProperty prop = obsPropertyMap.remove(name,values);
			if (prop == null){
				throw new IllegalArgumentException(
						"invalid observable property: " + name);
			}
	}

	protected ObsProperty getObsProperty(String name){
		return obsPropertyMap.getByName(name);
	}

	protected ObsProperty getObsPropertyByTemplate(String name, Object... values){
		return obsPropertyMap.get(name,values);
	}

	/**
	 * Blocks the execution of current operation until the condition specified
	 * by the guard is satisfied.
	 * 
	 * By calling await the execution of current atomic operation step is
	 * completed.
	 * 
	 * @param guardName
	 *            - name of the boolean method implementing the guard
	 * @param params
	 *            - parameters of the method
	 */
	protected void await(String guardName, Object... params) {
		try {
			commitObsStateChanges();
			String name = Artifact.getOpKey(guardName, params.length);
			Method guard = guardMap.get(name);
			guard.setAccessible(true);
			boolean guardOK = (Boolean) guard.invoke(this, params);
			while (!guardOK) {
				guards.await();
				guardOK = (Boolean) guard.invoke(this, params);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new IllegalArgumentException("Exception in await "
					+ guardName);
		}
	}

	/**
	 * Blocks the execution of current operation until the specified amount of
	 * time has passed.
	 * 
	 * By calling await the execution of current atomic operation step is
	 * completed.
	 * 
	 * @param dt
	 *            - amount of time in milliseconds
	 */
	protected void await_time(long dt) {
		try {
			commitObsStateChanges();
			lock.unlock();
			Thread.sleep(dt);
			lock.lock();
		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new IllegalArgumentException("Exception in await " + dt);
		}
	}

	/**
	 * 
	 * Blocks the execution of current operation until the specified blocking
	 * command has been executed.
	 * 
	 * By calling await the execution of current atomic operation step is
	 * completed.
	 * 
	 * @param cmd
	 *            - the command to be executed
	 */
	protected void await(IBlockingCmd cmd) {
		try {
			commitObsStateChanges();
			lock.unlock();
			cmd.exec();
		} catch (Exception ex) {
			// ex.printStackTrace();
			throw new IllegalArgumentException("Exception in await " + cmd);
		} finally {
			lock.lock();
		}
	}

	/**
	 * 
	 * Start the execution of an internal operation.
	 * 
	 * The execution/success semantics of the new operation is completely
	 * independent from current operation.
	 * 
	 * @param op
	 *            - the operation to be executed
	 */
	protected void execInternalOp(String opName, Object... params) {
		try {
			env.doInternalOp(this.id, new Op(opName, params));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException(
					"Error in executing internal op.");
		}
	}

	/**
	 * Execute a linked operation
	 * 
	 * @param outPortName
	 *            name of the out port
	 * @param opName
	 *            name of the operation to execute
	 * @param params
	 *            parameters of the operation
	 * @throws OperationException
	 */
	protected void execLinkedOp(String outPortName, String opName, Object... params) throws OperationException {
		ArtifactOutPort port = outPortsMap.get(outPortName);
		if (port == null) {
			throw new OperationException("Wrong out port name.");
		} else if (port.getArtifactList().isEmpty()) {
			throw new OperationException("No artifact linked.");
		} else {
			ArtifactId aid = port.getArtifactList().get(0);
			try {
				PendingOp pop = opCallback.createPendingOp();
				AgentId userId = this.getOpUserId();
				Op op = new Op(opName, params);
				CartagoNode.getInstance().execInterArtifactOp(opCallback, pop.getActionId(),
								userId, this.getId(), aid, op,
								Integer.MAX_VALUE, null);
				try {
					this.commitObsStateChanges();
					lock.unlock();
					pop.waitForCompletion();
				} finally {
					lock.lock();
				}
				if (!pop.hasSucceeded()) {
					throw new OperationException("op failed.");
				}
			} catch (Exception ex) {
				throw new OperationException("execLinkedOp failed " + ex);
			}
		}
	}

	/**
	 * Execute a linked operation, given the artifact id
	 * 
	 * @param aid
	 *            artifact identifier
	 * @param opName
	 *            name of the operation to execute
	 * @param params
	 *            parameters of the operation
	 * @throws OperationException
	 */
	protected void execLinkedOp(ArtifactId aid, String opName, Object... params)
			throws OperationException {
		try {
			PendingOp pop = opCallback.createPendingOp();
			AgentId userId = this.getOpUserId();
			Op op = new Op(opName, params);
			CartagoNode.getInstance().execInterArtifactOp(opCallback, pop.getActionId(), userId,
							this.getId(), aid, op, Integer.MAX_VALUE, null);
			try {
				this.commitObsStateChanges();
				lock.unlock();
				pop.waitForCompletion();
			} finally {
				lock.lock();
			}
			if (!pop.hasSucceeded()) {
				throw new OperationException("op failed.");
			}
		} catch (Exception ex) {
			throw new OperationException("execLinkedOp failed " + ex);
		}
	}

	/**
	 * Create an artifact from another artifact.
	 * 
	 * @param name
	 *            name of the artifact
	 * @param type
	 *            template
	 * @param params
	 *            parameters - use ArtifactConfig.DEFAULT_CONFIG for default
	 *            configuration
	 * @return artifact id
	 * @throws OperationException
	 */
	protected ArtifactId makeArtifact(String name, String type, ArtifactConfig params) throws OperationException {
		try {
			return env.makeArtifact(this.getOpUserId(), name, type, params);
		} catch (Exception ex) {
			throw new OperationException("makeArtifact failed: " + name + " "
					+ type);
		}
	}

	/**
	 * Dispose an artifact
	 * 
	 * @param aid
	 *            artifact id
	 * @throws OperationException
	 */
	protected void dispose(ArtifactId aid) throws OperationException {
		try {
			env.disposeArtifact(this.getOpUserId(),id);
		} catch (Exception ex) {
			throw new OperationException("disposeArtifact failed: " + aid);
		}
	}

	/**
	 * Lookup an artifact
	 * 
	 * @param name
	 *            artifact name
	 * @return artifact id
	 * @throws OperationException
	 */
	protected ArtifactId lookupArtifact(String name) throws OperationException {
		try {
			return env.lookupArtifact(this.getOpUserId(),name);
		} catch (Exception ex) {
			throw new OperationException("lookupArtifact failed: " + name);
		}
	}

	/**
	 * Delay the execution of next instruction of the specified amount of time
	 * 
	 * Note that this operation blocks the artifact access
	 * 
	 * @param ms
	 *            amount of time in milliseconds
	 */
	protected void delay(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception ex) {
		}
	}

	/**
	 * Log the information on standard output.
	 * 
	 */
	protected void log(String msg) {
		System.out.println("[" + this.getId().getName() + "] " + msg);
	}

	// meta

	/**
	 * Defining a new artifact operation
	 * 
	 * 
	 * @param op
	 *            operation
	 * @param guard
	 *            guard
	 */
	protected void defineOp(IArtifactOp op, IArtifactGuard guard) {
		String name = null;
		if (!op.isVarArgs()) {
			name = Artifact.getOpKey(op.getName(), op.getNumParameters());
		} else {
			name = Artifact.getOpKey(op.getName(), -1);
		}
		OpDescriptor opdesc = new OpDescriptor(op, guard,
				OpDescriptor.OpType.UI);
		// log("registering "+name);
		operationMap.put(name, opdesc);

	}
	
	
	/**
	 * Method automatically called when the artifact is 
	 * disposed. To be overridden by derived Artifact classes. 
	 * 
	 * Dual method with respect to init. 
	 */
	protected void dispose(){		
	}

	// API for the adapter (that allows the kernel to access the artifact)

	/*
	private void abortOperation(OpId id) throws CartagoException {
		if (thisOpId != null && thisOpId.equals(id)) {
			opExecFrame.getServingThread().interrupt();
			genEvent(id, "op_exec_aborted", opExecFrame.getOpId().getOpName());
		} else {
			Iterator<OpExecutionFrame> it = opsInExecution.iterator();
			while (it.hasNext()) {
				OpExecutionFrame opFrame = it.next();
				if (opFrame.getOpId().equals(id)) {
					it.remove();
					genEvent(opFrame.getOpId(), "op_exec_aborted", opExecFrame
							.getOpId().getOpName());
					return;
				}
			}
			throw new CartagoException("Operation not in execution");
		}
	}
	*/
	/*
	private void abortAllOperations() throws CartagoException {
		if (opExecFrame != null) {
			opExecFrame.getServingThread().interrupt();
			genEvent(opExecFrame.getOpId(), "op_exec_aborted", opExecFrame
					.getOpId().getOpName());
		}
		Iterator<OpExecutionFrame> it = opsInExecution.iterator();
		while (it.hasNext()) {
			OpExecutionFrame opFrame = it.next();
			it.remove();
			genEvent(opFrame.getOpId(), "op_exec_aborted", opExecFrame
					.getOpId().getOpName());
		}
	}
    */
	
	private void linkTo(ArtifactId aid, String outPort) throws CartagoException {
		ArtifactOutPort port = null;
		synchronized (outPortsMap) {
			if (outPort == null) {
				Iterator<ArtifactOutPort> it = outPortsMap.values().iterator();
				if (it.hasNext()) {
					port = it.next();
				}
			} else {
				port = outPortsMap.get(outPort);
			}
			if (port != null) {
				port.addArtifact(aid);
			} else {
				throw new CartagoException("Invalid out port: " + outPort);
			}
		}
	}

	private List<ArtifactObsProperty> readProperties() {
		return obsPropertyMap.readAll();
	}

	private String[] getUsageInterface() {
		;
		try {
			synchronized (obsPropertyMap) {
				String[] opNames = new String[operationMap.size()];
				operationMap.keySet().toArray(opNames);
				return opNames;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private OperationInfo[] getOpInExecution() {
		try {
			synchronized (opsInExecution) {
				OperationInfo[] opInfo = new OperationInfo[opsInExecution
						.size()];
				for (int i = 0; i < opInfo.length; i++) {
					OpExecutionFrame op = opsInExecution.get(i);
					int id = op.getOpId().getId();
					String name = op.getOpId().getOpName();
					opInfo[i] = new OperationInfo(id, name);
				}
				return opInfo;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Gets the adapter of the artifact
	 * 
	 * @return the adapter
	 */
	AbstractArtifactAdapter getAdapter() {
		return new ArtifactAdapter(this);
	}

	/**
	 * Gets a fresh identifier for a new operation execution
	 * 
	 */
	OpId getFreshId(String opName, AgentId ctxId) {
		int opid = opIds.getAndIncrement();
		OpId oid = new OpId(id, opName, opid, ctxId);
		return oid;
	}

	/**
	 * Class representing the adapter used to interface the artifact to the
	 * environment
	 * 
	 */
	class ArtifactAdapter extends AbstractArtifactAdapter {

		public ArtifactAdapter(Artifact art) {
			super(art);
		}

		/**
		 * Read a property
		 */
		/*
		public ArtifactObsProperty readProperty(String propertyName)
				throws CartagoException {
			return artifact.getObsProperty(propertyName);
		}*/

		/**
		 * Read properties
		 */
		public List<ArtifactObsProperty> readProperties() {
			return artifact.readProperties();
		}

		public void initArtifact(ArtifactConfig cfg) throws CartagoException {
			artifact.doInit(cfg);
		}

		/**
		 * Invoke an operation on the artifact
		 * 
		 */
		public void doOperation(OpExecutionFrame info) throws CartagoException {
			// log("doOperation:"+info.getOperation().getName()+" on "+this.artifact.getId()+"...");
			artifact.doOperation(info);
		}

		// meta ops

		public Manual getManual() {
			return artifact.manual;
		}

		public void linkTo(ArtifactId aid, String portName)
				throws CartagoException {
			artifact.linkTo(aid, portName);
		}

		public String[] getUsageInterface() {
			;
			return artifact.getUsageInterface();
		}

		public OperationInfo[] getOpInExecution() {
			return artifact.getOpInExecution();
		}

		public boolean hasOperation(Op op) {
			String name = Artifact.getOpKey(op.getName(),
					op.getParamValues().length);
			return artifact.operationMap.containsKey(name);
		}
	}

}
