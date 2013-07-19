package jaca.android;

import jason.infra.centralised.RunCentralisedMAS;

import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import cartago.CartagoService;

/**
 * Android Service providing functioning as a container
 * for running JaCa application on Android 
 * 
 * @author asanti
 *
 */
public class JaCaService extends Service {

	public static final String MAS2J = "mas2j";
	private static JaCaService mInstance;
	private boolean mRunning;
	private Integer mas2jId;
		
	/**
	 * Get a reference to the JaCaService
	 * 
	 * @return
	 */
	public static JaCaService getInstance(){
		return mInstance;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		Log.v("JaCaService", getApplication().toString()+ "onBind");
		if(!mRunning){
			Log.v("JaCaService",getApplication().toString() + "onBind, launching MAS");
			runMAS(arg0);
		}
		return JaCaBindingRole.getInstance().getCartagoNode(arg0);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		mRunning = false;
		CartagoService.setDefaultInfrastructureLayer("android");
		Log.v("JaCaService","onCreate");
	}
	
	// This is the old onStart method that will be called on the pre-2.0
	// platform.  On 2.0 or later we override onStartCommand() so this
	// method will not be called.
	@Override
	public void onStart(Intent intent, int startId) {
		handleOnStart(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    handleOnStart(intent);
	    // We want this service to continue running until it is explicitly
	    // stopped, so return sticky.
	    return START_STICKY;
	}
	
	private void handleOnStart (Intent intent) {
		Log.v("JaCaService","onStart");
		runMAS(intent);
	}
	
	protected void runMAS(Intent intent){
		if(!mRunning){
			mas2jId = intent.getIntExtra(MAS2J, 0);
			if(mas2jId != 0){
				URL url = JaCaService.class.getResource(getResources().getString(mas2jId));
				Log.v("JaCaService","running mas2j " + url.toString());
				RunCentralisedMAS.main(new String[]{url.toString()});
				mRunning = true;
			} else{
				Log.v("JaCaService","invalid mas2j parameter");
				throw new RuntimeException("invalid mas2j parameter");
			}
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		RunCentralisedMAS.getRunner().finish();
		mInstance = null;
		mRunning = false;
		Log.v("JaCaService","onDestroy");
	}
}