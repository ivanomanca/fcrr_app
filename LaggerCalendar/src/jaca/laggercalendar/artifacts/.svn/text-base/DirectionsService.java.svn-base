package jaca.laggercalendar.artifacts;

import jaca.laggercalendar.util.LaggerEvent;
import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class DirectionsService extends Artifact {
	
	private final static long TRAVEL_TIME = (1000 * 60 * 60 * 2);
	
	@OPERATION void calculateTravelTime(String departure, String arrival, OpFeedbackParam<Long> estTime){
		estTime.set(TRAVEL_TIME);
	}
	
	@OPERATION void calculateTravelTime(Object latitude, Object longitude, LaggerEvent eventInfo, OpFeedbackParam<Long> estTime){
		estTime.set(TRAVEL_TIME);
	}
	
	@OPERATION void calculateTravelTime(OpFeedbackParam<Long> estTime){
		estTime.set(TRAVEL_TIME);
	}
}