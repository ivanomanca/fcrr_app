package events;

import java.util.HashMap;

import cartago.Artifact;
import cartago.ArtifactConfig;
import cartago.ArtifactId;
import cartago.OPERATION;
import cartago.OperationException;

public class EventsReg extends Artifact {

	private HashMap<String,ArtifactId> eventsMap;
	private static final String EVENT = "event";
	
	@OPERATION void init(){
		eventsMap = new HashMap<String, ArtifactId>();
	}
	
	@OPERATION void addEvent(String eventName, double latitude, double longitude, String description, int minParticipantNumber){
		if (!eventsMap.containsKey(eventName)){
			ArtifactId artId = null;
			try {
				artId = makeArtifact(eventName, "events.EventBoard", new ArtifactConfig(new Object[]{eventName, latitude, longitude, description, minParticipantNumber}));
			} catch (OperationException e) {
				failed("Error in creating the EventBoard for the event" + eventName);
			}
			eventsMap.put(eventName, artId);
			defineObsProperty(EVENT, eventName);
		}
		else this.failed("Already added an event with the same name");
	}

	@OPERATION void removeEvent(String eventName){
		if (eventsMap.containsKey(eventName)){
			try {
				dispose(eventsMap.get(eventName));
			} catch (OperationException e) {
				failed("Error in disposing EventBoard artifact for the event" + eventName);
			}
			removeObsPropertyByTemplate(EVENT, eventName);
		}
		else this.failed("Event "+ eventName + "does not exist");
	}
}