package jaca.android.tests.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


/**
 * 
 * @author mguidi
 *
 */
public class TestCounterService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	private final MyCounter mBinder = new MyCounter();

	class MyCounter extends ITestCounter.Stub {

		int mValue=0;
		
		
		public synchronized void inc() throws RemoteException {
			mValue++;
		}

		
		public synchronized int getValue() throws RemoteException {
			return mValue;
		}
	}
}