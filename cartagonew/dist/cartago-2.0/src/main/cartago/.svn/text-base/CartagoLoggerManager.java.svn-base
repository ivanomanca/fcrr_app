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

import java.util.*;

import cartago.events.ArtifactObsEvent;

/**
 * Manager of the logging components.
 * 
 * @author aricci
 *
 */
class CartagoLoggerManager implements ICartagoLoggerManager {

	private boolean isLogging;
    LinkedList<ICartagoLogger> loggers;
	
    public CartagoLoggerManager(){
    	isLogging = false;
    	loggers = new LinkedList<ICartagoLogger>();
    }
    
	public boolean isLogging(){
		return isLogging;
	}
	
	public   void registerLogger(ICartagoLogger logger){
		loggers.add(logger);
		isLogging = true;
	}

	public   void unregisterLogger(ICartagoLogger logger){
		loggers.remove(logger);
		if (loggers.size()==0){
			isLogging = false;
		}
	}
		
	public void logActionUseExecuted(long when, AgentId who, ArtifactId aid, String opName){
		for (ICartagoLogger l:loggers){
			l.logActionUseExecuted(when,who,aid,opName);
		}
	}

	public void logActionFocusExecuted(long when, AgentId who, ArtifactId aid){
		for (ICartagoLogger l:loggers){
			l.logActionFocusExecuted(when,who,aid);
		}
	}
	
	public void logActionStopFocusExecuted(long when, AgentId who, ArtifactId aid){
		for (ICartagoLogger l:loggers){
			l.logActionStopFocusExecuted(when,who,aid);
		}
	}

	public void logActionObservePropertyExecuted(long when, AgentId who, ArtifactId aid, ArtifactObsProperty prop){
		for (ICartagoLogger l:loggers){
			l.logActionObservePropertyExecuted(when,who,aid,prop);
		}
	}

	public void logActionLinkArtifactsExecuted(long when, AgentId who, ArtifactId source, ArtifactId dest) {
		for (ICartagoLogger l:loggers){
			l.logActionLinkArtifactsExecuted(when,who,source,dest);
		}
	}

	public void logStimulousArrived(long when, AgentId who, ArtifactObsEvent ev){
		for (ICartagoLogger l:loggers){
			l.logStimulousArrived(when,who,ev);
		}
	}
	
	public void logArtifactCreated(long when, ArtifactId id){
		for (ICartagoLogger l:loggers){
			l.logArtifactCreated(when,id);
		}
	}
	
	public void logArtifactDisposed(long when, ArtifactId id){
		for (ICartagoLogger l:loggers){
			l.logArtifactDisposed(when,id);
		}
	}
	
	
	public void logArtifactOperationServed(long when, OpId opId, ArtifactId where){
		for (ICartagoLogger l:loggers){
			l.logArtifactOperationServed(when,opId,where);
		}
	}
	
	public void logArtifactOperationCompleted(long when, OpId opId, ArtifactId where){
		for (ICartagoLogger l:loggers){
			l.logArtifactOperationCompleted(when,opId,where);
		}
	}
	
	public void logArtifactEventGenerated(long when, ArtifactId id, String descr){
		for (ICartagoLogger l:loggers){
			l.logArtifactEventGenerated(when,id,descr);
		}
	}
	
	public void logArtifactStateChanged(long when, ArtifactId id, String state){
		for (ICartagoLogger l:loggers){
			l.logArtifactStateChanged(when,id,state);
		}
	}

	public void logArtifactOperationFailed(long when, OpId opId,
			ArtifactId where) {
		for (ICartagoLogger l:loggers){
			l.logArtifactOperationFailed(when, opId, where);
		}
	}

	public void logArtifactObsPropertyChanged(long when, ArtifactId id,
			ArtifactObsProperty prop) {
		for (ICartagoLogger l:loggers){
			l.logArtifactObsPropertyChanged(when, id, prop);
		}	
	}
}
