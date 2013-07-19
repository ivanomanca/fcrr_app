package events;

import java.util.ArrayList;

import cartago.Artifact;
import cartago.GUARD;
import cartago.OPERATION;
import cartago.ObsProperty;

public class EventBoard extends Artifact {

	private ArrayList<String> participants;
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String DESCRIPTION = "description";
	private static final String CURRENT_PARTICIPANT = "current_participant";
	private static final String MIN_PARTICIPANT = "min_participant";
	private static final String EVENT_STATUS = "event_status";
	private static final String STATUS_WAITING = "status_waiting";
	private static final String STATUS_READY = "status_ready";
	private static final String STATUS_RUNNING = "status_running";
	private static final String STATUS_ENDED = "status_ended";


	private int minParticipantNumber;
	
	@OPERATION void init(String eventName, double latitude, double longitude, String description, int minParticipantNumber){
		participants = new ArrayList<String>();
		this.minParticipantNumber = minParticipantNumber;
		defineObsProperty(LATITUDE, eventName, latitude);
		defineObsProperty(LONGITUDE, eventName, longitude);
		defineObsProperty(DESCRIPTION, eventName, description);
		defineObsProperty(CURRENT_PARTICIPANT, eventName, 0);
		defineObsProperty(MIN_PARTICIPANT, eventName, minParticipantNumber);
		defineObsProperty(EVENT_STATUS, eventName, STATUS_WAITING);		
	}
	
	@OPERATION void addParticipant(String participantInfo){
		participants.add(participantInfo);
		ObsProperty propParticipants = getObsProperty(CURRENT_PARTICIPANT);
		ObsProperty propStatus = getObsProperty(EVENT_STATUS);
		propParticipants.updateValue(1,propParticipants.intValue(1)+1);
		//Min participants number reached-> event ready
		if((propParticipants.intValue(1) >= minParticipantNumber) 
				&& (propStatus.stringValue(1).equals(STATUS_WAITING))){
			getObsProperty(EVENT_STATUS).updateValue(1,STATUS_READY);
		}
	}
	
	@OPERATION void removeParticipant(String particinaptInfo){
		if (participants.contains(particinaptInfo)){
			participants.remove(particinaptInfo);
			ObsProperty propParticipants = getObsProperty(CURRENT_PARTICIPANT);
			ObsProperty propStatus = getObsProperty(CURRENT_PARTICIPANT);
			propParticipants.updateValue(1,propParticipants.intValue(1)-1);
			//No more enough participants -> event not ready
			if((propParticipants.intValue(1) < minParticipantNumber) && (propStatus.stringValue(1).equals(STATUS_READY))) 
				getObsProperty(EVENT_STATUS).updateValue(1,STATUS_WAITING);
		}
		else failed("The specified participand does not exist");
	}

	@OPERATION void updateDescription(String description){
		getObsProperty(DESCRIPTION).updateValue(1, description);
	}

	@OPERATION(guard="isEventWaitingOrReady") void startEvent(){
		getObsProperty(EVENT_STATUS).updateValue(1, STATUS_RUNNING);
	}

	@OPERATION(guard="isEventRunning") void endEvent(){
		getObsProperty(EVENT_STATUS).updateValue(1, STATUS_ENDED);
		getObsProperty(CURRENT_PARTICIPANT).updateValue(1, 0);
	}

	@OPERATION(guard="isEventEnded") void restartEvent(int minParticipantNumber){
		getObsProperty(CURRENT_PARTICIPANT).updateValue(1, 0);
		this.minParticipantNumber = minParticipantNumber;
		getObsProperty(MIN_PARTICIPANT).updateValue(1, minParticipantNumber);
		getObsProperty(EVENT_STATUS).updateValue(1, STATUS_WAITING);
	}
	
	@GUARD boolean isEventWaitingOrReady(){
		return 
			getObsProperty(EVENT_STATUS).stringValue(1).equals(STATUS_WAITING) || 
			getObsProperty(EVENT_STATUS).stringValue(1).equals(STATUS_READY);
	}

	@GUARD boolean isEventReady(){
		return getObsProperty(EVENT_STATUS).stringValue(1).equals(STATUS_READY);
	}

	@GUARD boolean isEventRunning(){
		return getObsProperty(EVENT_STATUS).stringValue(1).equals(STATUS_RUNNING);
	}

	@GUARD boolean isEventEnded(int minParticipantNumber){
		return getObsProperty(EVENT_STATUS).stringValue(1).equals(STATUS_ENDED);
	}
}