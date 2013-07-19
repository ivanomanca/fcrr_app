package jaca.android.dev;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.android.internal.telephony.ITelephony;

import cartago.INTERNAL_OPERATION;

import android.content.Context;
import android.os.Handler;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

/**
 * Provides access to information about the telephony services on the device.
 * Developers can extend this class to define an artifact usable from agents for observe 
 * telephony state and receive events about telephony state changes.
 * <br/><br/>
 * Require the permission android.permission.READ_PHONE_STATE
 * 
 * @author mguidi
 *
 */
public abstract class TelephonyManagerArtifact extends JaCaArtifact{

	protected TelephonyManager mTelephonyManager;
	protected ITelephony mTelephonyService;
	private HashMap<String, String> mEvToOpLinks;
	private boolean mStopped;
	private BaseEventFetcher mEventFetcher;
	private MyPhoneStateListener mMyPhoneStateListener;
	
	/**
     * This method must be override to initialize the TelephonyManagerArtifact. 
     * First instruction of the override method must be super.init().
	 * @throws ClassNotFoundException 
     */
	protected void init(){
		mTelephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		mEvToOpLinks = new HashMap<String, String>();
		mStopped = false;
		mEventFetcher = new BaseEventFetcher();
		execInternalOp("fetchPhoneStateEvents");
		try{
			Class c = Class.forName(mTelephonyManager.getClass().getName());
			Method m = c.getDeclaredMethod("getITelephony");
			m.setAccessible(true);
			mTelephonyService = (ITelephony) m.invoke(mTelephonyManager);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Registers artifact to receive notification of changes in specified telephony states. 
	 */
	protected void register() {
		Handler handler = new Handler(getApplicationContext().getMainLooper()); // in alternativa si potrebbe usare un handlerThread differente dal main thread
		handler.post(new Runnable() {
			@Override
			public void run() {
				mMyPhoneStateListener = new MyPhoneStateListener();
				mTelephonyManager.listen(mMyPhoneStateListener, 
						PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR |
						PhoneStateListener.LISTEN_CALL_STATE |
						PhoneStateListener.LISTEN_CELL_LOCATION |
						PhoneStateListener.LISTEN_DATA_ACTIVITY |
						PhoneStateListener.LISTEN_DATA_CONNECTION_STATE |
						PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR |
						PhoneStateListener.LISTEN_SERVICE_STATE |
						PhoneStateListener.LISTEN_SIGNAL_STRENGTH);
			}
		});
	}
	
	/**
	 * Unregisters artifact to receive notification of changes in specified telephony states.
	 */
	protected void unregister() {
		Handler handler = new Handler(getApplicationContext().getMainLooper()); // in alternativa si potrebbe usare un halderThread differente dal main thread
		handler.post(new Runnable() {
			@Override
			public void run() {
				mTelephonyManager.listen(mMyPhoneStateListener, 0);
			}
		});
	}
	
	@INTERNAL_OPERATION void fetchPhoneStateEvents(){
    	while (!mStopped){
    		await(mEventFetcher);
    		EventOpInfo eventOp = mEventFetcher.getCurrentEventFetched();
    		if (eventOp!=null) {
	    		String opName = mEvToOpLinks.get(eventOp.getMethodName());
	    		if (opName!=null){
	    			execInternalOp(opName, eventOp.getParam());
	    		}
    		} else {
    			mStopped = true;
    		}
    	}
    }
	
	/**
	 * Get Android telephony manager service
	 * 
	 * @return Android telephony manager service
	 */
	protected TelephonyManager getTelephonyManager(){
		return mTelephonyManager;
	}
		
	/**
	 * Link call state changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onCallStateChanged(int state, String incomingNumber)
	 * 
	 * @param opName operation name
	 */
	protected void linkOnCallStateChangedEventToOp(String opName){
		mEvToOpLinks.put("onCallStateChanged", opName);
	}
	
	/**
	 * Link data connection state changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onDataConnectionStateChanged(int state)
	 * 
	 * @param opName operation name
	 */
	protected void linkOnDataConnectionStateChangedEventToOp(String opName){
		mEvToOpLinks.put("onDataConnectionStateChanged", opName);
	}
	
	/**
	 * Link call forwarding indicator changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onCallForwardingIndicatorChanged(boolean cfi)
	 * 
	 * @param opName operation name
	 */
	protected void linkOnCallForwardingIndicatorChangedEventToOp(String opName){
		mEvToOpLinks.put("onCallForwardingIndicatorChanged", opName);
	}
	
	/**
	 * Link cell location changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onCellLocationChanged(CellLocation location)
	 * <br/><br/>
	 * Requires either of the permissions android.permission.ACCESS_COARSE_LOCATION or android.permission.ACCESS_FINE_LOCATION
	 * 
	 * @param opName operation name
	 */
	protected void linkOnCellLocationChangedEventToOp(String opName){
		mEvToOpLinks.put("onCellLocationChanged", opName);
	}
	
	/**
	 * Link data activity event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onDataActivity(int direction)
	 * 
	 * @param opName operation name
	 */
	protected void linkOnDataActivityEventToOp(String opName){
		mEvToOpLinks.put("onDataActivity", opName);
	}
	
	/**
	 * Link message waiting indicator changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onMessageWaitingIndicatorChanged(boolean mwi)
	 * 
	 * @param opName operation name
	 */
	protected void linkonMessageWaitingIndicatorChangedEventToOp(String opName){
		mEvToOpLinks.put("onMessageWaitingIndicatorChanged", opName);
	}
	
	/**
	 * Link service state changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onServiceStateChanged(ServiceState serviceState)
	 * 
	 * @param opName operation name
	 */
	protected void linkOnServiceStateChangedEventToOp(String opName){
		mEvToOpLinks.put("onServiceStateChanged", opName);
	}
	
	/**
	 * Link signal strength changed event to a specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onSignalStrengthChanged(int asu)
	 * 
	 * @param opName operation name
	 */
	protected void linkOnSignalStrengthChangedEventToOp(String opName){
		mEvToOpLinks.put("onSignalStrengthChanged", opName);
	}
	
	
	private class MyPhoneStateListener extends PhoneStateListener {
		
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onCallStateChanged", state, incomingNumber));
		}
		
		@Override
		public void onDataConnectionStateChanged(int state) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onDataConnectionStateChanged", state));
		}
		
		@Override
		public void onCallForwardingIndicatorChanged(boolean cfi) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onCallForwardingIndicatorChanged", cfi));
		}
		
		@Override
		public void onCellLocationChanged(CellLocation location) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onCellLocationChanged", location));
		}
		
		@Override
		public void onDataActivity(int direction) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onDataActivity", direction));
		}
		
		@Override
		public void onMessageWaitingIndicatorChanged(boolean mwi) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onMessageWaitingIndicatorChanged", mwi));
		}
		
		@Override
		public void onServiceStateChanged(ServiceState serviceState) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onServiceStateChanged", serviceState));
		}
		
		@Override
		public void onSignalStrengthChanged(int asu) {
			mEventFetcher.putEvent(new EventOpInfo(MyPhoneStateListener.this, "onSignalStrengthChanged", asu));
		}

	}
}