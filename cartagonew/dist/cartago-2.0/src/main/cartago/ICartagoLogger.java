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
 * Interface for implementing logging components.
 * 
 * @author aricci
 *
 */
public interface ICartagoLogger {
	
	/**
	 * Log the execution of a use action.
	 * @param when timestamp
	 * @param who agent identifier
	 * @param aid target artifact
	 * @param opName operation name
	 * @param tag tag used
	 */
	void logActionUseExecuted(long when, AgentId who, ArtifactId aid, String opName);
	
	/**
	 * Log the execution of a focus
	 * @param when timestamp
	 * @param who agent focussing 
	 * @param aid target artifact
	 * @param sid sensor used
	 */
	void logActionFocusExecuted(long when, AgentId who, ArtifactId aid);

	/**
	 * Log the execution of a stopFocus
	 * @param when timestamp
	 * @param who agent focussing 
	 * @param aid target artifact
	 * @param sid sensor used
	 */
	void logActionStopFocusExecuted(long when, AgentId who, ArtifactId aid);
	
	/**
	 * Log observe property execution
	 * 
	 * @param when timestamp
	 * @param who agent observing
	 * @param aid target artifact
	 * @param prop property 
	 */
	void logActionObservePropertyExecuted(long when, AgentId who, ArtifactId aid, ArtifactObsProperty prop);
	
	/**
	 * Log linking artifacts
	 * @param when timestamp
	 * @param who agent observing
	 * @param source source artifact
	 * @param target source artifact
	 */
	void logActionLinkArtifactsExecuted(long when, AgentId who, ArtifactId source, ArtifactId dest);
	
	/**
	 * Log the arrival of a stimulous
	 * 
	 * @param when timestamp
	 * @param who agent sensing
	 * @param tag used
	 * @param ev stimulous
	 */
	void logStimulousArrived(long when, AgentId who, ArtifactObsEvent ev);
		
	/**
	 * Log the creation of a new artifact
	 * @param when timestamp
	 * @param id artifact name
	 */
	void logArtifactCreated(long when, ArtifactId id);
	
	/**
	 * Log the disposal of an artifact
	 * @param when timestamp
	 * @param id artifact name
	 */
	void logArtifactDisposed(long when, ArtifactId id);
	
	/**
	 * Log the change of the observable state name of an artifact
	 * @param when timestamp
	 * @param id artifact name
	 * @param state new state
	 */
	void logArtifactStateChanged(long when, ArtifactId id, String state);
	
	/**
	 * Log the change of an observable property of an artifact
	 * @param when timestamp
	 * @param id artifact name
	 * @param prop the new property
	 */
	void logArtifactObsPropertyChanged(long when, ArtifactId id, ArtifactObsProperty prop);

	/**
	 * Log an operation execution begin 
	 * @param when timestamp
	 * @param opId operation id 
	 * @param where artifact name
	 */
	void logArtifactOperationServed(long when, OpId opId, ArtifactId where);
	
	/**
	 * Log the completion of an operation execution
	 * @param when timestamp
	 * @param opId operation id
	 * @param where artifact name
	 */
	void logArtifactOperationCompleted(long when, OpId opId, ArtifactId where);
	
	/**
	 * Log the failure of an operation execution
	 * @param when
	 * @param opId
	 * @param where
	 */
	void logArtifactOperationFailed(long when, OpId opId, ArtifactId where);

	/**
	 * Log the generation of an observable event
	 * @param when timestamp
	 * @param id source artifact
	 * @param descr event description
	 */
	void logArtifactEventGenerated(long when, ArtifactId id, String descr);
}
