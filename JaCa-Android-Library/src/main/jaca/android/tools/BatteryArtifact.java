package jaca.android.tools;

import jaca.android.dev.BroadcastReceiverArtifact;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import cartago.INTERNAL_OPERATION;

/**
 * Artifact that provides information about the device battery.
 * 
 * <p><strong> #### Observable Properties ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 *  	{@code battery_level(Value)}. 
 *  		{@code Value} = int value indicating the current device charge [0-100]% 
 * 		</li>
 *  	<li>
 *  	{@code battery_healt(Value). Value = {"dead", "good"}} 
 * 		</li>
 *  	<li>
 *  	{@code battery_status(Value). Value = {"ok", "low"}}
 * 		</li>
 *  </ul>
 * </p>
 *
 *
 * @author asanti
 *
 */
public class BatteryArtifact extends BroadcastReceiverArtifact {


	//Intent managed by this artifact
	public static final String BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
	public static final String BATTERY_LOW = "android.intent.action.BATTERY_LOW";
	public static final String BATTERY_OK = "android.intent.action.ACTION_BATTERY_OKAY";
	
	/**
	 * Observable properties that store the current battery level
	 */
	public static final String LEVEL = "battery_level";
	
	/**
	 * Observable properties that store the current battery healt
	 */
	public static final String HEALT = "battery_healt";
	
	/**
	 * Observable properties that store the current battery status
	 */
	public static final String STATUS = "battery_status";

	//Possible values for HEALT observable property
	
	/**
	 * Value indicating a dead battery. 
	 * Value for the {@link BatteryArtifact#HEALT} observable property
	 */
	public static final String HEALT_DEAD = "dead";
	
	/**
	 * Value indicating a battery in good status. 
	 * Value for the {@link BatteryArtifact#HEALT} observable property
	 */
	public static final String HEALT_GOOD = "good";

	//Possible values for STATUS observable property
	
	/**
	 * Value indicating a battery with a good amount of charge. 
	 * Value for the {@link BatteryArtifact#STATUS} observable property
	 */
	public static final String STATUS_OK = "ok";
	
	/**
	 * Value indicating a battery with a low amount of charge. 
	 * Value for the {@link BatteryArtifact#STATUS} observable property
	 */
	public static final String STATUS_LOW = "low";

	/**
	 * Artifact default name
	 */
	public static final String ARTIFACT_DEF_NAME =  "battery-artifact";

	/**
	 * Name of the internal operation to be called for handling the event
	 * coming from the batteryInfoReceived listener 
	 */
	public static final String OP_BATTERY_INFO_RECEIVED = "batteryInfoReceived";
	
	/**
	 * Name of the internal operation to be called for handling the event
	 * coming from the batteryLowReceived listener 
	 */
	public static final String OP_BATTERY_LOW_RECEIVED = "batteryLowReceived";

	/**
	 * Name of the internal operation to be called for handling the event
	 * coming from the batteryOkReceived listener 
	 */
	public static final String OP_BATTERY_OK_RECEIVED  = "batteryOkReceived";
	
	/**
	 * Standar initialisation
	 */
	protected void init() {
		init(false);
	}
	
	/**
	 * Initialisation that allow to disable the propagation of the battery
	 * broadcast received
	 * @param abortBroadcast
	 */
	protected void init(boolean abortBroadcast) {
		super.init();
		
		defineObsProperty(HEALT, HEALT_GOOD);
		defineObsProperty(STATUS, STATUS_OK);
		defineObsProperty(LEVEL, 100);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(BATTERY_CHANGED);
		IntentFilter filter2 = new IntentFilter();
		filter.addAction(BATTERY_LOW);
		IntentFilter filter3 = new IntentFilter();
		filter3.addAction(BATTERY_OK);

		linkBroadcastReceiverToOp(filter, OP_BATTERY_INFO_RECEIVED, abortBroadcast);
		linkBroadcastReceiverToOp(filter2, OP_BATTERY_LOW_RECEIVED, abortBroadcast);
		linkBroadcastReceiverToOp(filter3, OP_BATTERY_OK_RECEIVED, abortBroadcast);
	}
	
	@INTERNAL_OPERATION public void batteryOkReceived(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		getObsProperty(STATUS).updateValue(STATUS_OK);
	}

	@INTERNAL_OPERATION public void batteryLowReceived(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		getObsProperty(STATUS).updateValue(STATUS_LOW);
	}
	
	@INTERNAL_OPERATION public void batteryInfoReceived(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		if (intent.getAction().equals(BATTERY_CHANGED)) {
			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 100);
			getObsProperty(LEVEL).updateValue(level);

			int healt = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_GOOD);
			String obsHealt = getObsProperty(HEALT).stringValue();
			
			if(healt!=BatteryManager.BATTERY_HEALTH_GOOD && obsHealt.equals(HEALT_GOOD))
				getObsProperty(HEALT).updateValue(HEALT_DEAD);
			else if(healt!=BatteryManager.BATTERY_HEALTH_DEAD && obsHealt.equals(HEALT_DEAD))
				getObsProperty(HEALT).updateValue(HEALT_GOOD);
		} 
	}
	
	protected void dispose(){
		super.dispose();
		unlinkBroadcastReceiverToOp(OP_BATTERY_INFO_RECEIVED);
		unlinkBroadcastReceiverToOp(OP_BATTERY_LOW_RECEIVED);
		unlinkBroadcastReceiverToOp(OP_BATTERY_OK_RECEIVED);
	}
}