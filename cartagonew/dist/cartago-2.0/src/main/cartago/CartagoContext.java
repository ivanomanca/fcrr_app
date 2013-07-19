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

import java.net.URL;
import java.util.*;

import cartago.security.SecurityException;
import cartago.security.AgentCredential;

/**
 * Cartago context - keeping track of agent session in a workspace
 * 
 * @author aricci
 *
 */
public class CartagoContext implements ICartagoContext {
	
	private AgentId id;
	private WorkspaceKernel wspKernel;
			
	/* agent callback to notify action/obs events */
	protected ICartagoCallback cartagoEventListener;
	
	
	CartagoContext(AgentId id, WorkspaceKernel env, ICartagoCallback agentCallback){
		this.id = id;
		this.wspKernel = env;
		this.cartagoEventListener = agentCallback;
	}
	
	public AgentId getAgentId() {
		return id;
	}
	
	public WorkspaceId getWorkspaceId(){
		return wspKernel.getId();
	}
	
	public WorkspaceKernel getWSPKernel(){
		return wspKernel;
	}

	//
	
	public void doAction(long actionId, ArtifactId aid, Op op, IAlignmentTest test, long timeout) throws CartagoException {
		if (timeout == -1){
			timeout = Integer.MAX_VALUE;
		}
		wspKernel.execOp(actionId, id, cartagoEventListener, aid, op, timeout, test);
	}

	public void doAction(long actionId, String name, Op op, IAlignmentTest test, long timeout) throws CartagoException {
		if (timeout == -1){
			timeout = Integer.MAX_VALUE;
		}
		wspKernel.execOp(actionId, id, cartagoEventListener, op, timeout, test);
	}
	
	public void doAction(long actionId, Op op, IAlignmentTest test, long timeout) throws CartagoException {
		if (timeout == -1){
			timeout = Integer.MAX_VALUE;
		}
		wspKernel.execOp(actionId, id, cartagoEventListener, op, timeout, test);
	}

	
	// called by the WSP kernel
	
	public ICartagoCallback getListener(){
		return cartagoEventListener;
	}

}
