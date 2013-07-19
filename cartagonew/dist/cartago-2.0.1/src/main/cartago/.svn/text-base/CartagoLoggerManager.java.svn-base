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

	@Override
	public void agentJoined(long when, AgentId id) {
		for (ICartagoLogger l:loggers){
			l.agentJoined(when, id);
		}
	}

	@Override
	public void agentQuit(long when, AgentId id) {
		for (ICartagoLogger l:loggers){
			l.agentQuit(when, id);
		}
	}

	@Override
	public void artifactCreated(long when, ArtifactId id, AgentId creator) {
		for (ICartagoLogger l:loggers){
			l.artifactCreated(when, id, creator);
		}
	}

	@Override
	public void artifactDisposed(long when, ArtifactId id, AgentId disposer) {
		for (ICartagoLogger l:loggers){
			l.artifactDisposed(when, id, disposer);
		}
	}

	@Override
	public void artifactFocussed(long when, AgentId who, ArtifactId id,
			IEventFilter ev) {
		for (ICartagoLogger l:loggers){
			l.artifactFocussed(when, who, id, ev);
		}
	}

	@Override
	public void artifactNoMoreFocussed(long when, AgentId who, ArtifactId id) {
		for (ICartagoLogger l:loggers){
			l.artifactNoMoreFocussed(when, who, id);
		}
	}

	@Override
	public void artifactsLinked(long when, AgentId id, ArtifactId linking,
			ArtifactId linked) {
		for (ICartagoLogger l:loggers){
			l.artifactsLinked(when, id, linking, linked);
		}
	}

	@Override
	public void newPercept(long when, ArtifactId aid, Tuple signal,
			ArtifactObsProperty[] added, ArtifactObsProperty[] removed,
			ArtifactObsProperty[] changed) {
		for (ICartagoLogger l:loggers){
			l.newPercept(when, aid, signal, added, removed, changed);
		}
	}

	@Override
	public void opCompleted(long when, OpId oid, ArtifactId aid, Op op) {
		for (ICartagoLogger l:loggers){
			l.opCompleted(when, oid, aid, op);
		}
	}

	@Override
	public void opFailed(long when, OpId oid, ArtifactId aid, Op op, String msg,
			Tuple descr) {
		for (ICartagoLogger l:loggers){
			l.opFailed(when, oid, aid, op, msg, descr);
		}
	}

	@Override
	public void opRequested(long when, AgentId who, ArtifactId aid, Op op) {
		for (ICartagoLogger l:loggers){
			l.opRequested(when, who, aid, op);
		}
	}

	@Override
	public void opResumed(long when, OpId oid, ArtifactId aid, Op op) {
		for (ICartagoLogger l:loggers){
			l.opResumed(when, oid, aid, op);
		}
	}

	@Override
	public void opStarted(long when, OpId oid, ArtifactId aid, Op op) {
		for (ICartagoLogger l:loggers){
			l.opStarted(when, oid, aid, op);
		}
	}

	@Override
	public void opSuspended(long when, OpId oid, ArtifactId aid, Op op) {
		for (ICartagoLogger l:loggers){
			l.opSuspended(when, oid, aid, op);
		}
	}
		
}
