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
 */package cartago.util;
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
 import cartago.*;
import cartago.events.ArtifactObsEvent;

/**
 * Adapter for logging components.
 * 
 * @author aricci
 *
 */
public class BasicLogger implements  ICartagoLogger {


	public void logAgentSensorLinked(long when, AgentId who, String sname) {
		log("LINKED NEW SENSOR: "+sname+" who:"+who.getAgentName()+" when:"+when);
	}

	public void logAgentSensorUnlinked(long when, AgentId who, String sname) {
		log("UNLINKED  SENSOR: "+sname+" who:"+who.getAgentName()+" when:"+when);
	}

	public void logArtifactObsPropertyChanged(long when, ArtifactId id,
			ArtifactObsProperty prop) {
		log("ARTIFACT OBS PROPERTY CHANGED:"+id.getName()+" obs prop:"+prop+" when:"+when);
	}

	public void logActionUseExecuted(long when, AgentId who, ArtifactId aid, String opName) {
		// TODO Auto-generated method stub
		log("USE EXECUTED agent:"+who.getAgentName()+" op:"+opName+" artifact:"+aid.getName()+" when:"+when);
	}

	public void logArtifactCreated(long when, ArtifactId id) {
		// TODO Auto-generated method stub
		log("ARTIFACT CREATED artifact:"+id.getName()+" when:"+when);
	}

	public void logArtifactDisposed(long when, ArtifactId id) {
		// TODO Auto-generated method stub
		log("ARTIFACT DISPOSED artifact:"+id.getName()+" when:"+when);
	}

	public void logArtifactStateChanged(long when, ArtifactId id, String state){
		log("ARTIFACT STATE CHANGED artifact:"+id.getName()+" new_state:"+state+" when:"+when);
	}
	
	public void logArtifactEventGenerated(long when, ArtifactId id, String descr) {
		// TODO Auto-generated method stub
		log("ARTIFACT EVENT GENERATED artifact:"+id.getName()+" event:"+descr+" when:"+when);
	}

	public void logArtifactOperationCompleted(long when, OpId opId, ArtifactId where) {
		// TODO Auto-generated method stub
		log("ARTIFACT OPERATION COMPLETED artifact:"+where.getName()+" op:"+opId.getOpName()+" when:"+when);
	}

	public void logArtifactOperationServed(long when, OpId opId, ArtifactId where) {
		// TODO Auto-generated method stub
		log("ARTIFACT OPERATION SERVED artifact:"+where.getName()+" op:"+opId.getOpName()+" when:"+when);
	}

	public void logStimulousArrived(long when, AgentId who, ArtifactObsEvent ev) {
		// TODO Auto-generated method stub
		log("STIMULOUS ARRIVED agent:"+who.getAgentName()+" stimulous:"+ev+" when:"+when);
	}
	
	public void logArtifactOperationFailed(long when, OpId opId, ArtifactId where) {
		// TODO Auto-generated method stub
		log("ARTIFACT OPERATION FAILED artifact:"+where.getName()+" op:"+opId.getOpName()+" when:"+when);
	}
	
	public void logActionFocusExecuted(long when, AgentId who, ArtifactId aid) {
		// TODO Auto-generated method stub
		log("FOCUS EXECUTED agent:"+who.getAgentName()+" artifact:"+aid.getName()+" when:"+when);
	}

	public void logActionLinkArtifactsExecuted(long when, AgentId who,
			ArtifactId source, ArtifactId dest) {
		// TODO Auto-generated method stub
		log("LINK ARTIFACT EXECUTED agent:"+who.getAgentName()+" artifact-source:"+source.getName()+" artifact-dest:"+dest.getName()+" when:"+when);
	}

	public void logActionObservePropertyExecuted(long when, AgentId who,
			ArtifactId aid, ArtifactObsProperty prop) {
		log("OBSERVE PROPERTY EXECUTED agent:"+who.getAgentName()+" artifact:"+aid.getName()+" prop:"+prop+" when:"+when);
	}

	public void logActionStopFocusExecuted(long when, AgentId who, ArtifactId aid) {
		// TODO Auto-generated method stub
		log("STOP-FOCUS EXECUTED agent:"+who.getAgentName()+" artifact:"+aid.getName()+" when:"+when);
	}

	private void log(String msg){
		System.out.println("[Basic logger] "+msg);
	}
}
