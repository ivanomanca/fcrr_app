package events;

import cartago.Artifact;
import cartago.GUARD;
import cartago.OPERATION;

public class BallSceneState extends Artifact {

	private static final String PRESENTER_CONTROL = "presenter_control";
	private static final String BALL_DIRECTION = "ball_direction";
	
	@OPERATION public void init(){
		defineObsProperty(PRESENTER_CONTROL, "off");
	}
	
	@OPERATION public void togglePresenterControl(){
		if(getObsProperty(PRESENTER_CONTROL).stringValue().equals("off")){
			getObsProperty(PRESENTER_CONTROL).updateValue("on");
			defineObsProperty(BALL_DIRECTION, "");
			signal("go");
		}
		else{
			getObsProperty(PRESENTER_CONTROL).updateValue("off");
			removeObsProperty(BALL_DIRECTION);
			signal("freeze");
		}
	}
	
	@OPERATION(guard="checkPresenterControl") public void setBallsDirection(String direction){
		getObsProperty(BALL_DIRECTION).updateValue(direction);
	}
	
	@GUARD public boolean checkPresenterControl(String direction){
		return (getObsProperty(PRESENTER_CONTROL).stringValue().equals("on"));
	}
}