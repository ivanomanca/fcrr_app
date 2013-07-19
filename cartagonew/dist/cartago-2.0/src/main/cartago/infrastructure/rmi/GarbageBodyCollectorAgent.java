package cartago.infrastructure.rmi;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import cartago.CartagoContext;

class GarbageBodyCollectorAgent extends Thread {

	private ConcurrentLinkedQueue<CartagoContextRemote> remoteCtxs;
	private boolean stopped;
	private long delay;
	private long timeout;
	
	public GarbageBodyCollectorAgent(ConcurrentLinkedQueue<CartagoContextRemote> contexts, long delay, long timeout){
		this.delay = delay;
		this.remoteCtxs = contexts;
		this.timeout = timeout;
	}
	
	public synchronized void stopActivity(){
		stopped = true;
		this.interrupt();
	}

	public synchronized boolean isStopped(){
		return stopped;
	}
			
	public void run(){
		// log("alive.");
		stopped = false;
		while (!isStopped()){
			try {
				sleep(delay);
				Iterator<CartagoContextRemote> it = remoteCtxs.iterator();
				while (it.hasNext()){
					CartagoContextRemote ctx = it.next();
					try {
						long last = ctx.getLastPing();
						if (System.currentTimeMillis()-last > timeout){
							ctx.getContext().getWSPKernel().removeGarbageBody((CartagoContext)ctx.getContext());
							it.remove();
							log("body garbaged: "+ctx.getContext().getAgentId());
						}
					} catch (Exception ex){
						ex.printStackTrace();
					}
				}
				//nfailures = 0;
			} catch (Throwable ex){
			}
		}
	}

	private void log(String msg){
		System.out.println("[GarbageBody Collector] "+msg);
	}
}
