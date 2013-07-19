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

import cartago.events.ArtifactObsEvent;

/**
 * Adapter for logging components.
 * 
 * @author aricci
 *
 */
public class CartagoLoggerAdapter implements ICartagoLogger {

	public void logArtifactObsPropertyChanged(long when, ArtifactId id,
			ArtifactObsProperty state) {
		// TODO Auto-generated method stub
		
	}

	public void logActionObservePropertyExecuted(long when, AgentId who, ArtifactId aid,
			ArtifactObsProperty prop) {
		// TODO Auto-generated method stub
		
	}

	public void logActionFocusExecuted(long when, AgentId who, ArtifactId aid) {
		// TODO Auto-generated method stub
		
	}

	public void logActionStopFocusExecuted(long when, AgentId who, ArtifactId aid) {
		// TODO Auto-generated method stub
		
	}

	public void logArtifactOperationFailed(long when, OpId opId,
			ArtifactId where) {
		// TODO Auto-generated method stub
		
	}

	public void logActionUseExecuted(long when, AgentId who, ArtifactId aid, String opName) {
		// TODO Auto-generated method stub
		
	}

	public void logActionLinkArtifactsExecuted(long when, AgentId who, ArtifactId source, ArtifactId dest) {
		
	}

	public void logArtifactCreated(long when, ArtifactId id) {
		// TODO Auto-generated method stub
		
	}

	public void logArtifactDisposed(long when, ArtifactId id) {
		// TODO Auto-generated method stub
		
	}

	public void logArtifactEventGenerated(long when, ArtifactId id, String descr) {
		// TODO Auto-generated method stub
		
	}

	public void logArtifactOperationCompleted(long when, OpId opId, ArtifactId where) {
		// TODO Auto-generated method stub
		
	}

	public void logArtifactOperationServed(long when, OpId opId, ArtifactId where) {
		// TODO Auto-generated method stub
		
	}

	public void logArtifactStateChanged(long when, ArtifactId id, String state){
		
	}

	public void logStimulousArrived(long when, AgentId who, ArtifactObsEvent ev) {
		// TODO Auto-generated method stub
	}

	public void logWorkspaceCreated(long when, AgentId who, String wspName) {
	}
	
}
