package jaca.android.fcrr;

import com.google.android.maps.MapActivity;

import jaca.android.JaCaService;
import jaca.android.dev.ActivityEventsFetcher;
import jaca.android.dev.EventOpInfo;
import jaca.android.dev.IJaCaActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.MotionEvent;

import cartago.ArtifactId;
import cartago.CartagoException;
import cartago.Op;
import cartago.util.agent.CartagoBasicContext;

public class JaCaMapActivity extends MapActivity implements IJaCaActivity {

	private String mName;
	private String mTemplate;
	private ArtifactId mArtifactId;
	private ActivityEventsFetcher mActivityEventFetcher;
	private boolean mBinded;
	private Bundle mSavedInstanceState;
	private CartagoBasicContext mCtx;
	
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBinded = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			if(mName != null && mTemplate != null){
				mArtifactId = _makeArtifact();
			}
			mBinded = true;
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSavedInstanceState = savedInstanceState;
		mBinded = false;
		mActivityEventFetcher = new ActivityEventsFetcher(this);
	}

	@Override
	protected void onStart() {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_START));
		super.onStart();
	}

	@Override
	protected void onResume() {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_RESUME));
		super.onResume();
	}

	@Override
	protected void onPause() {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_PAUSE));
		super.onPause();
	}

	@Override
	protected void onRestart() {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_RESTART));
		super.onRestart();
	}

	@Override
	protected void onStop() {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_STOP));
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_DESTROY));
		if (mBinded) getApplicationContext().unbindService(mConnection);
		_disposeArtifact();
		super.onDestroy();
	}

	/**
	 * Execute the MAS identified by the mas2j provided in input
	 * inside the {@link JaCaService} dedicated to the Android application
	 *  
	 * @param mas2j mas2j file path resource string id
	 */
	public void runJaCaService(int mas2j){
		Intent service = new Intent(this, JaCaService.class);
		service.putExtra(JaCaService.MAS2J, mas2j);
		getApplicationContext().startService(service);
	}
	
	/**
	 * Stop a running {@link JaCaService}, and the MAS it wraps  
	 */
	public void stopJaCaService(){
		Intent service = new Intent(this, JaCaService.class);
		getApplicationContext().stopService(service);
	}
	
	/**
	 * Create the CArtAgo artifact (extending the base GUIArtifact)
	 * that manages this JaCaActivity: operation needed for making
	 * the link between the JaCaActivity and the artifact
	 * 
	 * IMPORTANT: This method MUST be called only after having 
	 * started the JaCaService using the {@link JaCaActivity#runJaCaService} 
	 * method.
	 * @param name Artifact name
	 * @param template Artifact template: must be an extension of 
	 * the GUIArtifact
	 */
	protected void createArtifact(String name, String template){
		mName = name;
		mTemplate = template;
		Intent service = new Intent(this, JaCaService.class);
		getApplicationContext().bindService(service, mConnection, 0);
	}
	
	@Override
	public ActivityEventsFetcher getActivityEventsFetcher(){
		return mActivityEventFetcher;
	}
	
	@Override
	public Activity getActivity() {
		return this;
	}
	
	/** Methods for handling the activity-related events */
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_ACTIVITY_RESULT, requestCode, resultCode, data));
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_OPTIONS_ITEMS_SELECTED, item));
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mActivityEventFetcher.isGestureDetectorInstalled()){
			if(!mActivityEventFetcher.getGestureDetector().onTouchEvent(event)){
				mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_TOUCH_EVENT, event));
			}
		}
		else {
			mActivityEventFetcher.putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_TOUCH_EVENT, event));
		}
		return true;
	}
	
	/**
	 * Create the specialisation of the {@link GUIArtifact} wrapping this JaCaActivity. 
	 * The method must be called only once the JaCaService has been binded (i.e. the MAS is running).
	 * @return
	 */
	private ArtifactId _makeArtifact(){
		try {
			mCtx = new CartagoBasicContext(mName);
			mArtifactId = mCtx.makeArtifact(mName, mTemplate, new Object[]{this, mSavedInstanceState});
			return mArtifactId;
		} catch (CartagoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Dispose the specialisation of the {@link GUIArtifact} wrapping this JaCaActivity.
	 */
	private void _disposeArtifact(){
		try{
			if (mCtx!=null) {
				if (mArtifactId!=null) mCtx.disposeArtifact(mArtifactId);
				Op op = new Op("quitWorkspace");
				mCtx.doAction(op);
			}
		} catch (CartagoException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
