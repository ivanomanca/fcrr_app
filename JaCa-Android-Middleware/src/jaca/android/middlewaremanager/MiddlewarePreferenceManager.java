package jaca.android.middlewaremanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Base class used for accessing to the middleware preferences
 * @author asanti
 *
 */
public class MiddlewarePreferenceManager {
	
	public static void setLaunchTypeBackgroundPreference(Context ctx, boolean value){
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean("background_launch", value);
        editor.commit();
	}
	
	public static void clearLaunchTypeBackgroundPreference(Context ctx){
        Editor editor = getSharedPreferences(ctx).edit();
        editor.remove("background_launch");
        editor.commit();
	}
	
	public static boolean getLaunchTypeBackgroundPreference(Context ctx){
		return getSharedPreferences(ctx).getBoolean("background_launch", false);
	}
	
    /**
     * Preference key for JaCa-Android auto-start
     */
    public static final String PREF_AUTO_START = "pref_auto_start";

    /** Preference key containing the GPSManager setting*/
	public static final String PREF_GPS_MANAGER = "pref_gps_manager";
    
    /** Preference key containing the AccelerometerManager setting*/
	public static final String PREF_ACCELEROMETER_MANAGER = "pref_acc_manager";

    /** Preference key containing the GeomagSensorManager setting*/
	public static final String PREF_GEOMAGNETIC_MANAGER = "pref_geomag_manager";

	/** Preference key containing the OrientationSensorManager setting*/
	public static final String PREF_ORIENTATION_MANAGER = "pref_orientation_manager";

    /** Preference key containing the AllSensorsManager setting*/
	public static final String PREF_ALL_SENSORS_MANAGER = "pref_all_sensors_manager";
    
    /** Preference key containing the ConnectivityManager setting*/
	public static final String PREF_CONNECTIVITY_MANAGER = "pref_connectivity_manager";

    /** Preference key containing the PhoneSettingsManager setting*/
	public static final String PREF_PHONE_MANAGER = "pref_phone_settings_manager";

    /** Preference key containing the CallManager setting*/
	public static final String PREF_CALL_MANAGER = "pref_call_manager";
    
    /** Preference key containing the BatteryArtifact setting*/
	public static final String PREF_BATTERY_MANAGER = "pref_battery_manager";
    
    /** Preference key containing the ContactsManager setting*/
	public static final String PREF_CONTACTS_MANAGER = "pref_contacts_manager";

    /** Preference key containing the CalendarManager setting*/
	public static final String PREF_CALENDAR_MANAGER = "pref_calendar_manager";

    /** Preference key containing the SMSManager setting*/
	public static final String PREF_SMS_MANAGER = "pref_sms_manager";

    /* Default values for preferences */
    
    /** Default value for {@link PrefStore#PREF_AUTO_START}. */    
    public static final String DEF_AUTO_START = "No";

    /** Default value for {@link PrefStore#PREF_GPS_MANAGER}. */
    public static final boolean DEF_GPS_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_ACCELEROMETER_MANAGER}. */
    static final boolean DEF_ACCELEROMETER_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_GEOMAGNETIC_MANAGER}. */
    static final boolean DEF_GEOMAGNETIC_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_ORIENTATION_MANAGER}. */
    static final boolean DEF_ORIENTATION_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_ALL_SENSORS_MANAGER}. */
    static final boolean DEF_ALL_SENSORS_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_CONNECTIVITY_MANAGER}. */
    static final boolean DEF_CONNECTIVITY_MANAGER = false;

    /** Default value for {@link PrefStore#PREF_PHONE_MANAGER}. */
    static final boolean DEF_PHONE_MANAGER = false;

    /** Default value for {@link PrefStore#PREF_CALL_MANAGER}. */
    static final boolean DEF_CALL_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_BATTERY_MANAGER}. */
    static final boolean DEF_BATTERY_MANAGER = false;
    
    /** Default value for {@link PrefStore#PREF_CONTACTS_MANAGER}. */
    static final boolean DEF_CONTACTS_MANAGER = false;

    /** Default value for {@link PrefStore#PREF_CALENDAR_MANAGER}. */
    static final boolean DEF_CALENDAR_MANAGER = false;

    /** Default value for {@link PrefStore#PREF_SMS_MANAGER}. */
    static final boolean DEF_SMS_MANAGER = false;
    
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    
    static boolean getAutoStart(Context ctx) {
        String str = getSharedPreferences(ctx).getString(PREF_AUTO_START,
        		DEF_AUTO_START);
        if(str.equalsIgnoreCase(DEF_AUTO_START)) return false;
        else return true;
    }
    
    static boolean getGPSManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_GPS_MANAGER,
        		DEF_GPS_MANAGER);
    }
    
    static boolean getAcceleromenterSensorManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_ACCELEROMETER_MANAGER,
        		DEF_ACCELEROMETER_MANAGER);
    }
    
    static boolean getGeomagSensorManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_GEOMAGNETIC_MANAGER,
        		DEF_GEOMAGNETIC_MANAGER);
    }

    static boolean getOrientationSensorManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_ORIENTATION_MANAGER,
        		DEF_ORIENTATION_MANAGER);
    }

    static boolean getAllSensorManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_ALL_SENSORS_MANAGER,
        		DEF_ALL_SENSORS_MANAGER);
    }
    
    static boolean getConnectivityManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_CONNECTIVITY_MANAGER,
        		DEF_CONNECTIVITY_MANAGER);
    }    

    static boolean getPhoneManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_PHONE_MANAGER,
        		DEF_PHONE_MANAGER);
    }    
    
    static boolean getCalendarManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_CALENDAR_MANAGER,
        		DEF_CALENDAR_MANAGER);
    }    

    static boolean getSMSManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_SMS_MANAGER,
        		DEF_SMS_MANAGER);
    }    

    static boolean getCallManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_CALL_MANAGER,
        		DEF_CALL_MANAGER);
    }    

    static boolean getBatteryManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_BATTERY_MANAGER,
        		DEF_BATTERY_MANAGER);
    }    

    static boolean getContactsManager(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_CONTACTS_MANAGER,
        		DEF_CONTACTS_MANAGER);
    }    

    static void getBatteryManager(Context ctx, boolean value) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_BATTERY_MANAGER, value);
        editor.commit();
    }
}