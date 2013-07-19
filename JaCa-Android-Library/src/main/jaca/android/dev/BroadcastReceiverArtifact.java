package jaca.android.dev;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cartago.INTERNAL_OPERATION;

/**
 * Base artifact for receive intents sent by {@code sendBroadcast()}.
 * Developers can extend this class to define an artifact that signal an event to 
 * agents when receive the register broadcast intent.  
 * 
 * @author mguidi
 *
 */
public abstract class BroadcastReceiverArtifact extends JaCaArtifact {
	
	private HashMap<MyReceiver,String> mReceiverToOpLinks;
	private boolean mStopped;
	private BaseEventFetcher mEventsFetcher;
	private static final String ON_RECEIVE =  "onReceive";
	
	/**
     * This method must be override to initialize the BroadcatReceiverArtifact. 
     * First instruction of the override method must be super.init().
     */
	protected void init() {
		mStopped = false;
		mReceiverToOpLinks = new HashMap<MyReceiver, String>();
		mEventsFetcher = new BaseEventFetcher();
		execInternalOp("fetchReceiverEvents");
	}
	
	@INTERNAL_OPERATION void fetchReceiverEvents() {
    	while (!mStopped) {
    		await(mEventsFetcher);
    		EventOpInfo eventOp = mEventsFetcher.getCurrentEventFetched();
    		if (eventOp!=null) {
	    		String opName = mReceiverToOpLinks.get(eventOp.getSource());
	    		if(opName!=null) execInternalOp(opName, eventOp.getParam());
    		} else {
    			mStopped = true;
    		}
    	}
    }
	
	/**
	 * Link a broadcast receiver with the specified filter to specific operation of the artifact
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onReceive(BroadcastReceiver broadcastReceiver, Context context, Intent intent)
	 * 
	 * @param filter intent filter
	 * @param opName operation name
	 */
	protected void linkBroadcastReceiverToOp(IntentFilter filter, String opName) {		
		linkBroadcastReceiverToOp(filter, opName, false);
	}
	
	/**
	 * Link a broadcast receiver with the specified filter to specific operation of the artifact
	 * Sets the flag indicating that this receiver should abort the current broadcast; 
	 * only works with broadcasts sent through Context.sendOrderedBroadcast. This will prevent 
	 * any other broadcast receivers from receiving the broadcast.
	 * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onReceive(BroadcastReceiver broadcastReceiver, Context context, Intent intent)
	 * 
	 * @param filter intent filter
	 * @param opName operation name
	 * @param abortBroadcast flag indicating that this receiver should abort the current broadcast
	 */
	protected void linkBroadcastReceiverToOp(IntentFilter filter, String opName, boolean abortBroadcast) {		
		MyReceiver myReceiver = new MyReceiver(abortBroadcast);
		mReceiverToOpLinks.put(myReceiver, opName);
		getApplicationContext().registerReceiver(myReceiver, filter);
	}

	
	/**
	 * Unlink a broadcast receiver with the specified filter to specific operation of the artifact
	 * 
	 * @param opName operation name
	 */
	protected void unlinkBroadcastReceiverToOp(String opName) {
		Set<Entry<MyReceiver, String>> set = mReceiverToOpLinks.entrySet();
		Iterator<Entry<MyReceiver, String>> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			Entry<MyReceiver, String > item = iterator.next();
			if (item.getValue().equals(opName)) {
				getApplicationContext().unregisterReceiver(item.getKey());
				mReceiverToOpLinks.remove(item.getKey());
				break;
			}
		}
	}
	
	class MyReceiver extends BroadcastReceiver {
		private boolean mAbortBroadcast;
		
		public MyReceiver(boolean abortBroadcast) {
			mAbortBroadcast = abortBroadcast;
		}
		
		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				if (mAbortBroadcast) abortBroadcast();
				mEventsFetcher.putEvent(new EventOpInfo(MyReceiver.this, ON_RECEIVE, MyReceiver.this, context, intent));
			} catch (Exception ex){}
		}
	}
}
