package jaca.android.middlewaremanager;

import jaca.android.dev.JaCaArtifact;
import android.content.Context;
import cartago.LINK;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

/**
 * Artifac that allow to manages the middleware preferences. 
 * @author asanti
 *
 */
public class MiddlewarePreferenceManagerArtifact extends JaCaArtifact{

	private int numArtPresent;
	public static final String NUM_ART_SELECTED = "num_art_selected";

	@OPERATION public void init(){
		boolean prefValue;
		numArtPresent = 0;
		Context ctx = getApplicationContext();
		
		prefValue = MiddlewarePreferenceManager.getAutoStart(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_AUTO_START, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getGPSManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_GPS_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getAcceleromenterSensorManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_ACCELEROMETER_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getGeomagSensorManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_GEOMAGNETIC_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getOrientationSensorManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_ORIENTATION_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getAllSensorManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_ALL_SENSORS_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getConnectivityManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_CONNECTIVITY_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getPhoneManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_PHONE_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getCallManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_CALL_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getBatteryManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_BATTERY_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getContactsManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_CONTACTS_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getSMSManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_SMS_MANAGER, 
				prefValue);

		prefValue = MiddlewarePreferenceManager.getCalendarManager(ctx);
		if(prefValue)numArtPresent++;
		defineObsProperty(MiddlewarePreferenceManager.PREF_CALENDAR_MANAGER, 
				prefValue);

		defineObsProperty(NUM_ART_SELECTED, 
				numArtPresent);
	}
	
	@LINK public void updatePreferenceObsProp(String name, boolean value){
		getObsProperty(name).updateValue(value);
	}
	
	@OPERATION public void showMiddlewareIconInStatusBar(){
		MiddlewareNotificationManager.getInstance(getApplicationContext()).showMiddlewareIcon();
	}
	
	@OPERATION public void getLaunchTypeBackgroundPreference(OpFeedbackParam<Boolean> res){
		res.set(MiddlewarePreferenceManager.getLaunchTypeBackgroundPreference(getApplicationContext()));
	}
	
	@OPERATION public void setLaunchTypeBackgroundPreference(boolean value){
		MiddlewarePreferenceManager.setLaunchTypeBackgroundPreference(getApplicationContext(), value);
	}
	
	@OPERATION public void clearLaunchTypeBackgroundPreference(){
		MiddlewarePreferenceManager.clearLaunchTypeBackgroundPreference(getApplicationContext());
	}
}