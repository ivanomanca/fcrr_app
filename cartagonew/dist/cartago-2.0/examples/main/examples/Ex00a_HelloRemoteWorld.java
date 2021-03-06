package examples;

import cartago.*;
import cartago.util.BasicLogger;

public class Ex00a_HelloRemoteWorld {
	
	public static void main(String[] args) throws Exception {			
		CartagoService.startNode();
		CartagoService.installInfrastructureLayer("default");
		CartagoService.startInfrastructureService("default");
		CartagoService.registerLogger("default",new BasicLogger());  
		System.out.println("CArtAgO Node Ready.");
	}

}
