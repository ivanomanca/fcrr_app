package cartago.infrastructure.lipermi;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;

import cartago.AgentId;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.CartagoNode;
import cartago.IAlignmentTest;
import cartago.ICartagoCallback;
import cartago.ICartagoContext;
import cartago.NodeId;
import cartago.Op;
import cartago.OpId;
import cartago.infrastructure.CartagoInfrastructureLayerException;
import cartago.infrastructure.ICartagoInfrastructureLayer;
import cartago.security.AgentCredential;

/**
 * 
 * @author mguidi
 *
 */
public class CartagoInfrastructureLayer implements ICartagoInfrastructureLayer {

	static public final int DEFAULT_PORT = 20100; 
	private KeepRemoteBodyAliveManagerAgent mKeepAliveAgent;
	private ConcurrentLinkedQueue<AgentBodyProxy> mRemoteCtxs;
	private CartagoNodeRemote mService;
	private CallHandler mCallHandler;
		
	public CartagoInfrastructureLayer(){
		mCallHandler = new CallHandler();
		mRemoteCtxs = new ConcurrentLinkedQueue<AgentBodyProxy>();
		mKeepAliveAgent = new KeepRemoteBodyAliveManagerAgent(mRemoteCtxs,500);
		mKeepAliveAgent.start();
	}
	
	@Override
	public OpId execRemoteInterArtifactOp(ICartagoCallback callback,
			long callbackId, AgentId userId, ArtifactId srcId,
			ArtifactId targetId, String address, Op op, long timeout,
			IAlignmentTest test) throws CartagoInfrastructureLayerException,
			CartagoException {
		
		try {
			ICartagoCallbackRemote srv = new CartagoCallbackRemote(callback);
			mCallHandler.exportObject(ICartagoCallbackRemote.class, srv);
			ICartagoNodeRemote env = _getCartagoNodeRemote(address);
			return env.execInterArtifactOp(srv, callbackId, userId, srcId, targetId, op, timeout, test);
			
		} catch (LipeRMIException e) {
			e.printStackTrace();
			throw new CartagoInfrastructureLayerException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartagoInfrastructureLayerException();
		}
	}

	@Override
	public NodeId getNodeAt(String address)
			throws CartagoInfrastructureLayerException, CartagoException {
		
		try {
			ICartagoNodeRemote env = _getCartagoNodeRemote(address);
			return env.getNodeId();		
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new CartagoInfrastructureLayerException();
		} 
	}

	@Override
	public boolean isServiceRunning() {
		return mService != null;
	}

	@Override
	public ICartagoContext joinRemoteWorkspace(String wspName, String address,
			AgentCredential cred, ICartagoCallback eventListener)
			throws CartagoInfrastructureLayerException, CartagoException {
		
		try {
			ICartagoCallbackRemote srv = new CartagoCallbackRemote(eventListener);
			mCallHandler.exportObject(ICartagoCallbackRemote.class, srv);
			ICartagoNodeRemote env = _getCartagoNodeRemote(address);
			IAgentBodyRemote rctx = env.join(wspName, cred, srv);
			ICartagoContext ctx = new  AgentBodyProxy(rctx);
			mRemoteCtxs.add((AgentBodyProxy)ctx);
			return ctx;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new CartagoInfrastructureLayerException();
		} catch (LipeRMIException e) {
			e.printStackTrace();
			throw new CartagoInfrastructureLayerException();
		} 
	}

	@Override
	public void shutdownLayer() throws CartagoException {
		mKeepAliveAgent.shutdown();
	}

	@Override
	public void shutdownService() throws CartagoException {
		if (mService != null){
			mService.shutdownService();
			mService = null;
		}
	}

	@Override
	public void startService(CartagoNode node, String address)
			throws CartagoInfrastructureLayerException {
		
		if (mService != null){
			throw new CartagoInfrastructureLayerException();
		}
		try {
			int port = DEFAULT_PORT;
			mService = new CartagoNodeRemote(node);
			int port1 = getPort(address);
			if (port1 != -1){
				port = port1;
			}
			mService.install(port);
		} catch (Exception ex){
			ex.printStackTrace();
			throw new CartagoInfrastructureLayerException();
		}
	}
	
	private ICartagoNodeRemote _getCartagoNodeRemote(String fullAddress) throws IOException {
		String address = getAddress(fullAddress);
		int port = DEFAULT_PORT;
		int port1 = getPort(fullAddress);
		if (port1 != -1){
			port = port1;
		}
		Client client = new Client(address, port, mCallHandler);
		ICartagoNodeRemote remnode =  (ICartagoNodeRemote) client.getGlobal(ICartagoNodeRemote.class);
		return remnode;
	}
	
	
	private static String getAddress(String address) {
		int index = address.indexOf(":");
		if (index == -1) {
			return address;
		} else {
			return address.substring(0, index);
		}
	}
	
	private static int getPort(String address){
		int index = address.indexOf(":");
		if (index != -1){
			return Integer.parseInt(address.substring(index+1));
		} else {
			return -1;
		}
	}

}
