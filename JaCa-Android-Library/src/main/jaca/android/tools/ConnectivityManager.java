package jaca.android.tools;

import jaca.android.dev.BroadcastReceiverArtifact;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

/**
 * Artifact that manages every kind of connectivity available on the device.
 * Up to now just wifi and airplane mode management are supported.
 * 
 * <p><strong> #### Observable Properties ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 * 			{@code airplane_mode_status(Val). Val = {"on", "off"}}
 *  	</li>
 *  	<li>
 *  		{@code wifi_status(Val). Val = {"on", "off"}}
 *  	</li>
 *  </ul>
 * @author asanti
 *
 */
public class ConnectivityManager extends BroadcastReceiverArtifact {
	
	/**
	 * Observable property indicating the current value of the airplane mode 
	 * in the device
	 */
	public static final String AIRPLANE_MODE_STATUS = "airplane_mode_status"; // on/off
	
	/**
	 * Observable property indicating the current wifi status in the device
	 */
	public static final String WIFI_STATUS = "wifi_status"; // on/off
	
	/**
	 * Observable value property indicating that the kind of connectivity
	 * referred by the observable property is currently enabled
	 */
	public static final String ON_VALUE = "on";

	/**
	 * Observable value property indicating that the kind of connectivity
	 * referred by the observable property is currently disabled
	 */
	public static final String OFF_VALUE = "off";
	
	/**
	 * Artifact default name 
	 */
	public static final String ARTIFACT_DEF_NAME =  "connectivity-manager";
	
	private WifiManager wifiManager;
	
	protected void init() {
		super.init();
		
		boolean isAirplaneEnabled = Settings.System.getInt(
				getApplicationContext().getContentResolver(), 
				Settings.System.AIRPLANE_MODE_ON, 0) == 1;

		wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		
		if(isAirplaneEnabled)
			defineObsProperty(AIRPLANE_MODE_STATUS, ON_VALUE);
		else
			defineObsProperty(AIRPLANE_MODE_STATUS, OFF_VALUE);
		
		if(wifiManager.isWifiEnabled())
			defineObsProperty(WIFI_STATUS, ON_VALUE);
		else
			defineObsProperty(WIFI_STATUS, OFF_VALUE);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.SERVICE_STATE");
		filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		linkBroadcastReceiverToOp(filter, "onReceive");
	}
	
	@INTERNAL_OPERATION void onReceive(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		boolean isAirplaneEnabled = Settings.System.getInt(
				getApplicationContext().getContentResolver(), 
				Settings.System.AIRPLANE_MODE_ON, 0) == 1;
		
		boolean obsIsAirplaneOn = getObsProperty(AIRPLANE_MODE_STATUS).equals(ON_VALUE);
		boolean obsIsWifiOn = getObsProperty(WIFI_STATUS).equals(ON_VALUE);
		
		//Updating AIRPLANE_MODE_STATUS if needed
		if(obsIsAirplaneOn!=isAirplaneEnabled){
			
			if(isAirplaneEnabled)
				getObsProperty(AIRPLANE_MODE_STATUS).updateValue(ON_VALUE);
			else
				getObsProperty(AIRPLANE_MODE_STATUS).updateValue(OFF_VALUE);
		}

		//Updating WIFI_STATUS if needed
		if(obsIsWifiOn!=wifiManager.isWifiEnabled()){
			if(obsIsWifiOn)
				getObsProperty(WIFI_STATUS).updateValue(ON_VALUE);
			else
				getObsProperty(WIFI_STATUS).updateValue(OFF_VALUE);
		}
	}
	
	/**
	 * Operation that enables the wifi in the device
	 */
	@OPERATION public void enableWiFi(){
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
	}

	/**
	 * Operation that disables the wifi in the device
	 */
	@OPERATION public void disableWiFi(){
		if(wifiManager.isWifiEnabled()){
			WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
		}
	}

	/**
	 * Artifact operation that enables airplane mode 
	 * (disabling any kind of connectivity on the device).
	 */
	@OPERATION void enableAirplaneMode(){
		
		boolean isEnabled = Settings.System.getInt(
				getApplicationContext().getContentResolver(), 
				Settings.System.AIRPLANE_MODE_ON, 0) == 1;
		
		if(!isEnabled) {
			// toggle airplane mode
			Settings.System.putInt(
					getApplicationContext().getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 1);
	
			// Post an intent to reload
			reloadServiceState();
		}
	}
	
	/**
	 * Artifact operation that disables airplane mode
	 */
	@OPERATION void disableAirplaneMode(){
		
		boolean isDisabled = Settings.System.getInt(
				getApplicationContext().getContentResolver(), 
				Settings.System.AIRPLANE_MODE_ON, 0) == 0;
		
		if(!isDisabled) {
			// toggle airplane mode
			Settings.System.putInt(
					getApplicationContext().getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 0);
			//Post an intent to reload
			reloadServiceState();	
		}
	}

	/**
	 * Artifact operation that set the airplane mode configuration
	 * @param mode A comma separated list of radios that need to be disabled when airplane mode is on (wifi, bluetooth, cell)
	 */
	
	@OPERATION void enableAirplaneSpecific(String mode) {
		Settings.System.putString(
				getApplicationContext().getContentResolver(),
				Settings.System.AIRPLANE_MODE_RADIOS,
				mode);
		// Post an intent to reload
		reloadServiceState();
	}

	private void reloadServiceState(){
		// Post an intent to reload
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", false);
		getApplicationContext().sendBroadcast(intent);
	}
}