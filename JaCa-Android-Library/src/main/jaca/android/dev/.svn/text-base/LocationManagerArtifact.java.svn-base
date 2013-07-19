package jaca.android.dev;

import java.util.HashMap;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * This class provides access to the system location services.
 * Developers can extend this class to define an artifact usable 
 * from agents for obtain periodic updates of the device's geographical location.
 * 
 * @author mguidi
 *
 */
public abstract class LocationManagerArtifact extends JaCaArtifact {

	private HashMap<String, LocationListener> mProviderToListenerLinks;
	private HashMap<String, HashMap<String,String>> mEvToOpLinks;
	private LocationManager mLocationManager;
	private boolean mStopped;
	private BaseEventFetcher mProc;
	private int mMinTime;
	private int mMinDistance;

	/**
     * This method must be override to initialize the LocationManagerArtifact. 
     * First instruction of the override method must be super.init().
     */
	protected void init(int minTime, int minDistance) {
		mMinTime = minTime;
		mMinDistance = minDistance;
		mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		mProviderToListenerLinks = new HashMap<String, LocationListener>();
		mEvToOpLinks = new HashMap<String, HashMap<String,String>>();
		mStopped = false;
		mProc = new BaseEventFetcher();
		execInternalOp("fetchLocationManagerEvents");
	}
	
	/**
	 * Internal operation ciclically in execution inside the LocationManagerArtifact 
	 * that process incoming events
	 */
	@INTERNAL_OPERATION protected void fetchLocationManagerEvents() {
		//Log.v("gpsmanager", "Starting background event fetching");
    	while (!mStopped) {
    		await(mProc);
    		//Log.v("gpsmanager", "New event");
    		EventOpInfo eventOp = mProc.getCurrentEventFetched();
    		if (eventOp!=null) {
	    		HashMap<String,String> map = mEvToOpLinks.get(eventOp.getSource());
	    		if (map!=null) {
	    			String opName = map.get(eventOp.getMethodName());
	    			if (opName!=null) {
	    				//Log.v("gpsmanager", "New event detected, will be executed" + eventOp.getMethodName());
	    				execInternalOp(opName, eventOp.getParam());
	    			}
	    		}
    		} else {
    			mStopped = true;
    		}
    	}
    }
	
	/**
	 * Get Android location manager service
	 * 
	 * @return Android location manager service
	 */
	protected LocationManager getLocationManager() {
		return mLocationManager;
	}
    
    /**
     * Registers the current artifact to be notified periodically by the named provider.
     * <br/><br/>
     * The frequency of notification may be controlled using the minTime and minDistance parameters. 
     * If minTime is greater than 0, the LocationManager could potentially rest for minTime milliseconds 
     * between location updates to conserve power. If minDistance is greater than 0, a location will only 
     * be broadcasted if the device moves by minDistance meters. To obtain notifications as frequently as 
     * possible, set both parameters to 0.
     * Be careful about setting a sufficiently high minTime so that the device doesn't consume too much power 
     * by keeping the GPS or wireless radios on all the time. In particular, values under 60000ms are not recommended.
     * <br/><br/>
     * Requires either of the permissions android.permission.ACCESS_COARSE_LOCATION or android.permission.ACCESS_FINE_LOCATION.
     * 
     * @param provider the name of the provider with which to register
     * @param minTime the minimum time interval for notifications, in milliseconds. This field is only used as a hint to conserve power, and actual time between location updates may be greater or lesser than this value.
     * @param minDistance the minimum distance interval for notifications, in meters
     */
	protected void requestLocationUpdates(final String provider, final long minTime, final float minDistance) {
		if (mProviderToListenerLinks.get(provider) == null) {
			LocationListener listener = new GpsReceiver();
			mProviderToListenerLinks.put(provider, listener);
			
			// in alternativa si potrebbe usare un HandlerThread differente dal main thread
			mLocationManager.requestLocationUpdates(provider, minTime, minDistance, listener, getApplicationContext().getMainLooper());
			
		} else {
			throw new RuntimeException("LocationListener already linked to provider");
		}
	}
	
	/**
     * Remove the current artifact to be notified periodically by the named provider.
     * 
     * @param provider
     */
	protected void removeLocationUpdates(String provider) {
		mEvToOpLinks.remove(provider);
		LocationListener listener = mProviderToListenerLinks.remove(provider);
		if(listener != null) mLocationManager.removeUpdates(listener);
	}
    
	/**
	 * Link location changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onLocationChange(Location arg0)
	 * 
	 * @param provider provider name
	 * @param opName operation name
	 */
	protected void linkOnLocationChangedEventToOp(String provider, String opName) {		
		insertEventToOp(provider, "onLocationChanged", opName);
	}
	
	/**
	 * Link provider disabled event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onProviderDisabled(String provider)
	 * 
	 * @param provider provider name
	 * @param opName operation name
	 */
	protected void linkOnProviderDisabledEventToOp(String provider, String opName) {		
		insertEventToOp(provider, "onProviderDisabled", opName);
	}
	
	/**
	 * Link provider enabled event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onProviderEnabled(String provider)
	 * 
	 * @param provider provider name
	 * @param opName operation name
	 */
	protected void linkOnProviderEnabledEventToOp(String provider, String opName) {
		insertEventToOp(provider, "onProviderEnabled", opName);
	}
	
	/**
	 * Link status changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onStatusChanged(String arg0, int arg1, Bundle arg2)
	 * 
	 * @param provider provider name
	 * @param opName operation name
	 */
	protected void linkOnStatusChangedEventToOp(String provider, String opName) {		
		insertEventToOp(provider, "onStatusChanged", opName);
	}
	
	private void insertEventToOp(String provider, String type, String opName) {
		HashMap<String,String> map = mEvToOpLinks.get(provider);
		if (map==null) {
			map = new HashMap<String,String>();
			mEvToOpLinks.put(provider, map);
		}
		map.put(type, opName);
	}
	
	/**
	 * Internal implementation of the LocationListener interface for retrieving
	 * location changes events  
	 */
	private class GpsReceiver implements LocationListener {

		@Override
		public void onLocationChanged(Location arg0) {
			//Log.v("gpsmanager", "new event found");
			mProc.putEvent(new EventOpInfo(arg0.getProvider(), "onLocationChanged", arg0));
		}

		@Override
		public void onProviderDisabled(String arg0) { 
			mProc.putEvent(new EventOpInfo(arg0, "onProviderDisabled", arg0));
		}

		@Override
		public void onProviderEnabled(String arg0) {
			mProc.putEvent(new EventOpInfo(arg0, "onProviderEnabled", arg0));
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			mProc.putEvent(new EventOpInfo(arg0, "onStatusChanged", arg0, arg1, arg2));
		}
	}
	
	/**
	 * Artifact operation used for starting retrieving location updates from the LocationManager 
	 */
	@OPERATION public void startMonitoring(){
		requestLocationUpdates(LocationManager.GPS_PROVIDER, mMinTime, mMinDistance);
	}
	
	/**
	 * Artifact operation used for terminating the retrieval of location updates from the LocationManager
	 */
	@OPERATION public void stopMonitoring(){
		removeLocationUpdates(LocationManager.GPS_PROVIDER);
	}
}