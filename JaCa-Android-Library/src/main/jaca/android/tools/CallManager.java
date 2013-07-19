package jaca.android.tools;

import jaca.android.dev.TelephonyManagerArtifact;
import android.telephony.TelephonyManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;

/**
 * Artifact enabling agents to monitoring incoming call and call state 
 * <br/><br/>
 * Require the permission android.permission.READ_PHONE_STATE
 * <br/>
 * 
 * 
 * 
 * <p><strong> #### Observable Properties ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 * 			{@code call_state(State)}. {@code State = {"idle", "offhook", "ringing"}}
 * 		</li>
 * 		<li>
 * 			{@code incoming_number(Number)}. {@code Number} = An int representing the incoming number
 * 		</li>
 *  </ul>
 *  
 *  
 * <p><strong> #### Observable Events ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 * 			{@code ringing(Source)}. Source = A String representing the source of the incoming call
 * 		</li>
 * 		<li>
 * 			{@code offhook}. Event generated after a call offhook 
 * 		</li>
 * 		<li>
 * 			{@code idle}. Event generated once the device become idle
 * 		</li>
 *  </ul>
 * 
 * @author mguidi
 *
 */
public class CallManager extends TelephonyManagerArtifact {

	/**
	 * call_state
	 */
	public static final String CALL_STATE_PROPERTY = "call_state";
	public static final String INCOMING_NUMBER = "incoming_number";
	
	//call_state obs prop possible values
	public static final String CALL_STATE_IDLE = "idle";
	public static final String CALL_STATE_OFFHOOK = "offhook";
	public static final String CALL_STATE_RINGING = "ringing";
	
	private boolean pIncomingNumber;
	
	public static final String ARTIFACT_DEF_NAME =  "call-manager";
	
	protected void init() {
		super.init();
		
		pIncomingNumber = false;
		linkOnCallStateChangedEventToOp("onCallStateChanged");
		defineObsProperty(CALL_STATE_PROPERTY, (Object) null);
		register();
	}
	
	protected void dispose() {
		super.dispose();
		unregister();
	}
		
	@INTERNAL_OPERATION void onCallStateChanged(int state, String incomingNumber){
		
		ObsProperty prop = getObsProperty(CALL_STATE_PROPERTY);
		switch(state){
		case TelephonyManager.CALL_STATE_IDLE:
			prop.updateValue(CALL_STATE_IDLE);
			if(pIncomingNumber){
				removeObsProperty(INCOMING_NUMBER);
				pIncomingNumber = false;
			}
			signal(CALL_STATE_IDLE);
			break;
			
		case TelephonyManager.CALL_STATE_OFFHOOK:
			prop.updateValue(CALL_STATE_OFFHOOK);
			signal(CALL_STATE_OFFHOOK);
			break;
			
		case TelephonyManager.CALL_STATE_RINGING:
			pIncomingNumber = true;
			defineObsProperty(INCOMING_NUMBER, incomingNumber);
			prop.updateValue(CALL_STATE_RINGING);
			signal(CALL_STATE_RINGING, incomingNumber);
			break;
		}
	}
	
	/*
	 * there isn't api for answer/reject a call
	 * we provide a basic drop-call functionality
	 * using a work-around that works for Android 
	 * versions <= 2.2
	 */
	
	
	/*@OPERATION void answer(){
		// TODO
	}*/
	
	@OPERATION void dropCall(){
		try {
			if(mTelephonyService != null)
				mTelephonyService.endCall();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}