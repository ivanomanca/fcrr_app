package jaca.android.middlewaremanager;

import jaca.android.JaCaService;
import android.content.Intent;
import android.os.IBinder;

/**
 * Specialisation of the classical {@link JaCaService} used
 * for the middleware.
 * This service wraps a shared workspace, accessible by external application
 * at the address jaca.android.jacaservices. The workspace
 * contains the JaCa-Android artifacts the user has decided 
 * to include in the shared workspace   
 * @author asanti
 *
 */
public class JaCaMiddlewareService extends JaCaService {

	public enum LaunchType {BACKGROUND, WITH_GUI};
	private LaunchType launchType; 
	public static final String SERVICE_START_ACTION = "jaca.android.jacaservice";
	public static final String MIDDLEWARE_WSP_NAME = "MiddlewareServices";
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	
	// This is the old onStart method that will be called on the pre-2.0
	// platform.  On 2.0 or later we override onStartCommand() so this
	// method will not be called.
	@Override
	public void onStart(Intent intent, int startId) {
		handleOnStart(intent);
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		handleOnStart(intent);
		return super.onStartCommand(intent, flags, startId);
	}

	private void handleOnStart (Intent intent) {
		//Middleware started in background on boot
		if(intent.getAction()!=null && intent.getAction().equals(SERVICE_START_ACTION)){
			launchType = LaunchType.BACKGROUND;
			MiddlewarePreferenceManager.setLaunchTypeBackgroundPreference(this, true);
		}
		//Middleware started from the MiddlewareMaangerActivity
		else{ 
			launchType = LaunchType.WITH_GUI;
			MiddlewarePreferenceManager.setLaunchTypeBackgroundPreference(this, false);
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		/* 
		 * For hanling particular crahs situation of the middleware. 
		 * After that crashes could happen that launchType is null, so we
		 * restore it. 
		 */
		
		/*if(launchType==null && arg0.get){
			launchType = MiddlewarePreferenceManager.getLaunchTypeBackgroundPreference(this) ? LaunchType.BACKGROUND : LaunchType.WITH_GUI;
			System.out.println("[MiddlewareManager] launchtype null, ora è " + launchType);
		}*/
		if(launchType!=LaunchType.WITH_GUI){
			launchType = LaunchType.BACKGROUND;
			MiddlewarePreferenceManager.setLaunchTypeBackgroundPreference(this, true);
		}
		arg0.putExtra(JaCaService.MAS2J, R.string.middleware_manager_mas2j);
		return super.onBind(arg0);
	}
	
	@Override
	public void onDestroy() {
		MiddlewarePreferenceManager.clearLaunchTypeBackgroundPreference(this);
		MiddlewareNotificationManager.getInstance(getApplicationContext()).removeMiddlewareIcon();
		super.onDestroy();
	}
}