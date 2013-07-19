package jaca.android.middlewaremanager;

import jaca.android.dev.ActivityEventsFetcher;
import jaca.android.dev.IJaCaActivity;
import jaca.android.dev.PreferenceChangeEventsFetcher;
import jaca.android.dev.PreferenceGUIArtifact;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import cartago.ARTIFACT_INFO;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OUTPORT;
import cartago.OperationException;

@ARTIFACT_INFO(outports = {@OUTPORT(name = "out-port")})

public class MiddlewareManagerGUIArtifact extends PreferenceGUIArtifact{

	public static final String NUM_ART_SELECTED = "num_art_selected";
	MiddlewareManagerActivity mMiddlewareManagerActivity;
	
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mMiddlewareManagerActivity = (MiddlewareManagerActivity) activity;

		Context ctx = getApplicationContext();
		int numArtPresent = 0;
		boolean prefValue = false;
		prefValue = MiddlewarePreferenceManager.getAutoStart(ctx);
		if(prefValue)setPreferenceEditable(false);
		prefValue = MiddlewarePreferenceManager.getGPSManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getAcceleromenterSensorManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getGeomagSensorManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getOrientationSensorManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getAllSensorManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getConnectivityManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getPhoneManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getCallManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getBatteryManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getContactsManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getSMSManager(ctx);
		if(prefValue)numArtPresent++;
		prefValue = MiddlewarePreferenceManager.getCalendarManager(ctx);
		if(prefValue)numArtPresent++;

		/** Linking artifact ops to events **/
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_AUTO_START, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_GPS_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_ACCELEROMETER_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_GEOMAGNETIC_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_ORIENTATION_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_ALL_SENSORS_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_CONNECTIVITY_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_PHONE_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_CALL_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_BATTERY_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_CONTACTS_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_SMS_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnPreferenceChangeEvToOp(MiddlewarePreferenceManager.PREF_CALENDAR_MANAGER, 
				PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE);
		
		linkOnClickEventToOp(mMiddlewareManagerActivity.getBtnStart(), ActivityEventsFetcher.ON_CLICK);
		linkOnClickEventToOp(mMiddlewareManagerActivity.getBtnStop(), ActivityEventsFetcher.ON_CLICK);
		
		mMiddlewareManagerActivity.getProgressBar().setMax(numArtPresent);
	}
	
	@OPERATION public void onClick(View view) {
		if(view==mMiddlewareManagerActivity.getBtnStart()){
			this.startMiddleware();
		}
		else if(view==mMiddlewareManagerActivity.getBtnStop()){
			this.stopMiddleware();
		}
	}

	@OPERATION public void disableButton(String btn){
		mMiddlewareManagerActivity.disableButton(btn);
	}
	
	@OPERATION public void enableButton(String btn){
		mMiddlewareManagerActivity.enableButton(btn);
	}
	
    @INTERNAL_OPERATION public void onPreferenceChange(Preference preference, Object newValue) {
    	if((Boolean)newValue) {
    		mMiddlewareManagerActivity.getProgressBar().setMax(mMiddlewareManagerActivity.getProgressBar().getMax()+1);
    	}
    	else{
    		mMiddlewareManagerActivity.getProgressBar().setMax(mMiddlewareManagerActivity.getProgressBar().getMax()-1);
    	}
    	try {
			execLinkedOp("out-port","updatePreferenceObsProp", preference.getKey(), newValue);
		} catch (OperationException e) {
			e.printStackTrace();
		}
    }
    
    @OPERATION public void setPreferenceEditable(boolean value){
    	mMiddlewareManagerActivity.setPreferenceEditable(value);
    }
    
    @OPERATION public void stopMiddleware(){
    	signal("stop_middleware");
    }

    @OPERATION public void startMiddleware(){
    	signal("start_middleware");
    }
    
    @OPERATION public void incrementProgressBar(){
    	mMiddlewareManagerActivity.incrementProgressBar();
    }
    
    @OPERATION public void decrementProgressBar(){
    	mMiddlewareManagerActivity.decrementProgressBar();
    }    
    @OPERATION public void updateStatusIcon(int iconId){
    	mMiddlewareManagerActivity.updateStatusIcon(iconId);
    }

    @OPERATION public void updateStatusIcon(String iconId){
    	mMiddlewareManagerActivity.updateStatusIcon(getApplicationContext().getResources().getIdentifier(iconId, null, null));
    }
    
    @OPERATION public void notifyStartupError(){
    	mMiddlewareManagerActivity.notifyStartupError();   
    }
    
    @OPERATION public void updateStatusTxt(String strId){
    	mMiddlewareManagerActivity.updateStatusTxt(getApplicationContext().getResources().getIdentifier(strId, null, null));
    }
}