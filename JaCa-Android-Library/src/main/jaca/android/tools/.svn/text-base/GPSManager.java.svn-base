package jaca.android.tools;

import jaca.android.dev.LocationManagerArtifact;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import cartago.INTERNAL_OPERATION;

/**
 * Artifact used for managing the GPS of the device
 * 
 * <br/><br/>
 * Requires either of the permissions android.permission.ACCESS_COARSE_LOCATION or android.permission.ACCESS_FINE_LOCATION.
 * <br/>
 *
 * <p><strong> #### Observable Properties ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 *  		{@code latitude(Latitude). Latitude} = DOUBLE
 * 		</li>
 *  	<li>   
 *			{@code longitude(Longitude).  Longitude} = DOUBLE
 * 		</li>
 *  	<li>
 * 			{@code altitude(Altitude) Altitude} = DOUBLE
 * 		</li>
 *  	<li> 
 * 			{@code accurancy(Accuracy) Accuracy} = FLOAT
 * 		</li>
 *  	<li> 
 * 			{@code bearing(Bearing) Bearing} = FLOAT
 * 		</li>
 *  	<li> 
 * 			{@code speed(Speed) Speed} = FLOAT
 * 		</li>
 *  	<li> 
 * 			{@code time(Time) Time} = LONG
 * 		</li>
 *  </ul>
 *
 * 
 * <p><strong> #### Observable Events ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 *  		{@code onLocationChange(Provider). Provider = {gps, network}}
 *  	</li>
 *  	<li>
 *  		{@code onProviderEnabled(Provider). Provider = {gps, network}}
 *  	</li>
 *  	<li>
 *  		{@code onProviderDisabled(Provider) Provider = {gps, network}}
 *  	</li>
 *  </ul>
 * 
 * @author mguidi
 *
 */
public class GPSManager extends LocationManagerArtifact {

	/**
	 * Name of the event generated each time the location change
	 */
	public static final String ON_LOCATION_CHANGE = "onLocationChange";
	
	/**
	 * Name of the event generated each time the provider is enabled
	 */
	public static final String ON_PROVIDER_ENABLED = "onProviderEnabled";
	
	/**
	 * Name of the event generated each time the provider is disabled
	 */
	public static final String ON_PROVIDER_DISABLED = "onProviderDisabled";
	
	/**
	 * Identify the operation to be executed each time a 
	 * {@link GPSManager#ON_PROVIDER_DISABLED} event is fetched
	 */
	public static final String OP_ON_PROVIDER_DISABLED = "onProviderDisabled";

	/**
	 * Identify the operation to be executed each time a 
	 * {@link GPSManager#ON_PROVIDER_ENABLED} event is fetched
	 */
	public static final String OP_ON_PROVIDER_ENABLED = "onProviderEnabled";
	
	/**
	 * Identify the operation to be executed each time a 
	 * {@link GPSManager#ON_LOCATION_CHANGE} event is fetched
	 */
	public static final String OP_ON_LOCATION_CHANGE = "onLocationChange";
	
	/**
	 * Observable property containing the current latitude
	 */
	public static final String LATITUDE = "latitude";

	/**
	 * Observable property containing the current longitude
	 */
	public static final String LONGITUDE = "longitude";
	
	/**
	 * Observable property containing the current altitude
	 */
	public static final String ALTITUDE = "altitude";

	/**
	 * Observable property containing the current accurancy
	 */
	public static final String ACCURANCY = "accurancy";
	
	
	/**
	 * Observable property containing the current bearing
	 */
	public static final String BEARING = "bearing";

	/**
	 * Observable property containing the current speed
	 */
	public static final String SPEED = "speed";

	/**
	 * Observable property containing the current time
	 */
	public static final String TIME = "time";
	
	/**
	 * Artifact default name
	 */
	public static final String ARTIFACT_DEF_NAME =  "gps-manager";
	
	/**
	 * Default init
	 */
	public void init() {
		//Log.v("gpsmanager", "ON_INIT");
		init(0,0);
	}
		
	/**
	 * Init with specific parameters
	 * @param minTime
	 * @param minDistance
	 */
	public void init(int minTime, int minDistance) {
		super.init(minTime, minDistance);
		
		linkOnLocationChangedEventToOp(LocationManager.GPS_PROVIDER, OP_ON_LOCATION_CHANGE);
		linkOnProviderEnabledEventToOp(LocationManager.GPS_PROVIDER, OP_ON_PROVIDER_ENABLED);
		linkOnProviderDisabledEventToOp(LocationManager.GPS_PROVIDER, OP_ON_PROVIDER_DISABLED);

		Location location = getLocationManager().getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location!=null) {
			defineObsProperty(LATITUDE, location.getLatitude());
			defineObsProperty(LONGITUDE, location.getLongitude());
			defineObsProperty(ALTITUDE, location.getAltitude());
			defineObsProperty(ACCURANCY, location.getAccuracy());
			defineObsProperty(BEARING, location.getBearing());
			defineObsProperty(SPEED, location.getSpeed());
			defineObsProperty(TIME, location.getTime());
		} else {
			defineObsProperty(LATITUDE, 0);
			defineObsProperty(LONGITUDE, 0);
			defineObsProperty(ALTITUDE, 0);
			defineObsProperty(ACCURANCY, 0);
			defineObsProperty(BEARING, 0);
			defineObsProperty(SPEED, 0);
			defineObsProperty(TIME, 0);
		}
	}
		
	protected void dispose() {
		super.dispose();
		removeLocationUpdates(LocationManager.GPS_PROVIDER);
	}
	
	@INTERNAL_OPERATION void onLocationChange(Location arg0) {
		//Log.v("gpsmanager", "locationchangefound");
		signal(ON_LOCATION_CHANGE, arg0.getProvider());
		getObsProperty(LATITUDE).updateValue(arg0.getLatitude());
		getObsProperty(LATITUDE).updateValue(arg0.getLatitude());
		getObsProperty(LONGITUDE).updateValue(arg0.getLongitude());
		getObsProperty(ALTITUDE).updateValue(arg0.getAltitude());
		getObsProperty(ACCURANCY).updateValue(arg0.getAccuracy());
		getObsProperty(BEARING).updateValue(arg0.getBearing());
		getObsProperty(SPEED).updateValue(arg0.getSpeed());
		getObsProperty(TIME).updateValue(arg0.getTime());
		//Log.v("gpsmanager", "locationchangeserved");
	}
	
	@INTERNAL_OPERATION void onProviderEnabled(String provider) {
		//Log.v("gpsmanager", "onProviderEnabled");
		signal(ON_PROVIDER_ENABLED,provider);
	}
	
	@INTERNAL_OPERATION void onProviderDisabled(String provider) {
		signal(ON_PROVIDER_DISABLED,provider);
	}
}