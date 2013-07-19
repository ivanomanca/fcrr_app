package extgame;

import cartago.*;

public class PlayerBridge extends Artifact {

	private static final int NO_MOVE_REQUESTED = 0;
	private static final int NEW_MOVE_REQUESTED = 1;
	private static final int MOVE_DONE = 2;
	
	IGameEngine fwork;
	
	
	void init(IGameEngine fwork){
		this.fwork = fwork;
		defineObsProperty("game_state",NO_MOVE_REQUESTED);
	}
	
	// to do action on the framework
	
	@OPERATION void doActionX(){
		fwork.actionX();
	}

	@OPERATION void doActionY(){
		fwork.actionY();
	}
	
	/**
	 * action to notify that the agent has completed his move
	 */
	@OPERATION void moveDone(){
		updateObsProperty("game_state",MOVE_DONE);
	}
	
	/**
	 * Called by game engine framework agent 
	 */
	@OPERATION void triggerNewMove(){
		log("triggered new move.");
		updateObsProperty("game_state",NEW_MOVE_REQUESTED);
		await("isMoveDone");
	}

	@GUARD boolean isMoveDone(){
		try {
			return getObsProperty("game_state").intValue() == MOVE_DONE;
		} catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
}

