package cartago.infrastructure.lipermi;

import cartago.AgentBody;
import cartago.AgentId;
import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.IAlignmentTest;
import cartago.Op;
import cartago.WorkspaceId;

/**
 * 
 * @author mguidi
 *
 */
public class AgentBodyRemote implements IAgentBodyRemote {

	private static final long serialVersionUID = 1L;
	private AgentBody mCtx;
	private long mLastPingFromMind;
	
	public AgentBodyRemote(AgentBody ctx) {
		mCtx = ctx;
		mLastPingFromMind = System.currentTimeMillis();
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
	public void doAction(long actionId, Op op, IAlignmentTest test, long timeout)
			throws CartagoException {
		mCtx.doAction(actionId, op, test, timeout);
		
	}

	@Override
	public AgentId getAgentId() throws CartagoException {
		return mCtx.getAgentId();
	}

	@Override
	public WorkspaceId getWorkspaceId() throws CartagoException {
		return mCtx.getWorkspaceId();
	}

	@Override
	public synchronized void ping() {
		mLastPingFromMind = System.currentTimeMillis();
	}
	
	public synchronized long getLastPing(){
		return mLastPingFromMind;
	}	
	
	public AgentBody getContext(){
		return mCtx;
	}

}
