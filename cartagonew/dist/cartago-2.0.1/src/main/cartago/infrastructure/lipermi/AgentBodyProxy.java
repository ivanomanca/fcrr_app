package cartago.infrastructure.lipermi;

import cartago.AgentId;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.IAlignmentTest;
import cartago.ICartagoContext;
import cartago.Op;
import cartago.WorkspaceId;

/**
 * 
 * @author mguidi
 *
 */
public class AgentBodyProxy implements ICartagoContext {

	private static final long serialVersionUID = 1L;
	private IAgentBodyRemote mCtx;
	
	public AgentBodyProxy(IAgentBodyRemote ctx) {
		mCtx = ctx;
	}
	
	@Override
	public void doAction(long actionId, Op op, IAlignmentTest test, long timeout)
			throws CartagoException {
		
		mCtx.doAction(actionId, op, test, timeout);
	}

	@Override
	public void doAction(long actionId, ArtifactId id, Op op,
			IAlignmentTest test, long timeout) throws CartagoException {
	
		mCtx.doAction(actionId, id, op, test, timeout);
	}

	@Override
	public void doAction(long actionId, String name, Op op,
			IAlignmentTest test, long timeout) throws CartagoException {
		
		mCtx.doAction(actionId, name, op, test, timeout);
	}

	@Override
	public AgentId getAgentId() throws CartagoException {
		return mCtx.getAgentId();
	}

	@Override
	public WorkspaceId getWorkspaceId() throws CartagoException {
		return mCtx.getWorkspaceId();
	}
	
	public void ping() throws CartagoException {
		mCtx.ping();
	}
	

}
