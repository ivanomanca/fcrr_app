package cartago.infrastructure.rmi;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeepRemoteBodyAliveManagerAgent extends Thread {
	
	private ConcurrentLinkedQueue<CartagoContextProxy> proxies;
	private boolean stopped;
	private long delay;
	
	public KeepRemoteBodyAliveManagerAgent(ConcurrentLinkedQueue<CartagoContextProxy> proxies, long delay){
		stopped = false;
		this.proxies = proxies;
		this.delay = delay;
	}
	
	public void run(){
		while (!stopped){
			try {
				sleep(delay);
				Iterator<CartagoContextProxy> it = proxies.iterator();
				while (it.hasNext()){
					CartagoContextProxy ctx = it.next();
					try {
						ctx.getContextService().ping();
					} catch (Exception ex){
						ex.printStackTrace();
						it.remove();
					}
				}
			} catch (Exception ex){
				log("Error: "+ex);
			}
		}
	}
	
	public void shutdown(){
		interrupt();
		stopped = true;
	}
	
	private void log(String msg){
		System.err.println("[KEEP-ALIVE-MANAGER] "+msg);
	}
}
