package cartago.infrastructure.android;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * JaCaService connection used for binding at ICartagoNodeRemote
 * 
 * @author mguidi
 *
 */
class JoinWspConnection implements ServiceConnection {

	private ICartagoNodeRemote mCartagoNodeRemote;
	private ReentrantLock mLock;
	private Condition mCondition;
	private boolean mConnected;
	
	public JoinWspConnection() {
		mLock = new ReentrantLock();
		mCondition = mLock.newCondition();
		mConnected = false;
	}
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		mLock.lock();
		mCartagoNodeRemote = ICartagoNodeRemote.Stub.asInterface(service);
		mConnected = true;
		mCondition.signalAll();
		mLock.unlock();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		mCartagoNodeRemote = null;
	}
	
	/**
	 * Wait for 4 seconds for bind to remote service
	 */
	public void await(int waitSeconds) {
		try {
			mLock.lock();
			if(!mConnected) {
				mCondition.await(waitSeconds,TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mLock.unlock();
		}
	}
	
	/**
	 * Get CartagoNodeRemote
	 * @return CartagoNodeRemote
	 */
	public ICartagoNodeRemote getCartagoNodeRemote() {
		return mCartagoNodeRemote;
	}
	
}