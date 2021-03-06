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
package cartago.util.agent;

import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.Op;
import cartago.WorkspaceId;
import cartago.security.AgentCredential;


/**
 * Base class for defining very simple agents on top of CArtAgO
 * 
 * @author aricci
 *
 */
public class Agent extends Thread {

	protected CartagoBasicContext ctx;

	public Agent(String agentName) {
		super(agentName);
		ctx = new CartagoBasicContext(agentName);
	}

	public Agent(String agentName, String workspaceName, String workspaceHost) {
		super(agentName);
		ctx = new CartagoBasicContext(agentName, workspaceName, workspaceHost);
	}
	
	protected String getAgentName(){
		return ctx.getName();
	}

	protected ActionFeedback doActionAsync(Op op) throws CartagoException {
		return ctx.doActionAsync(op);
	}


	protected ActionFeedback doActionAsync(ArtifactId aid, Op op, long timeout) throws CartagoException {
		return ctx.doActionAsync(aid, op, timeout);
	}

	protected ActionFeedback doActionAsync(Op op, long timeout) throws CartagoException {
		return ctx.doActionAsync(op, timeout);
	}

	protected void doAction(Op op, long timeout) throws CartagoException {
		ctx.doAction(op, timeout);
	}

	public void doAction(Op op) throws CartagoException {
		this.doAction(op, -1);
	}

	protected void doAction(ArtifactId aid, Op op, long timeout) throws CartagoException {
		ctx.doAction(aid, op, timeout);
	}

	protected void doAction(ArtifactId aid, Op op) throws CartagoException {
		this.doAction(aid,op,-1);
	}

	protected Percept fetchPercept() throws InterruptedException {
		return ctx.fetchPercept();
	}

	protected Percept fetchPercept(IEventFilter filter) throws InterruptedException {
		return ctx.fetchPercept(filter);
	}

	protected void log(String msg){
		System.out.println("["+ctx.getName()+"] "+msg);
	}


	//Utility methods

	public WorkspaceId joinWorkspace(String wspName, AgentCredential cred) throws CartagoException {
		return ctx.joinWorkspace(wspName, cred);
	}

	protected WorkspaceId joinRemoteWorkspace(String wspName, String address, String roleName, AgentCredential cred)  throws CartagoException {
		return ctx.joinRemoteWorkspace(wspName, address, roleName, cred);
	}

	protected ArtifactId lookupArtifact(String artifactName) throws CartagoException {
		return ctx.lookupArtifact(artifactName);
	}

	protected ArtifactId makeArtifact(String artifactName, String templateName) throws CartagoException {
		return ctx.makeArtifact(artifactName, templateName);
	}

	protected ArtifactId makeArtifact(String artifactName, String templateName, Object[] params) throws CartagoException {
		return ctx.makeArtifact(artifactName, templateName, params);
	}

	protected void disposeArtifact(ArtifactId artifactId) throws CartagoException {
		ctx.disposeArtifact(artifactId);
	}

	protected void focus(ArtifactId artifactId) throws CartagoException {
		ctx.focus(artifactId);
	}

	protected void focus(ArtifactId artifactId, IEventFilter filter) throws CartagoException {
		ctx.focus(artifactId, filter);
	}
}