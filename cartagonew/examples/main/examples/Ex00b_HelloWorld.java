package examples;

import cartago.CartagoService;
import cartago.util.BasicLogger;

public class Ex00b_HelloWorld {
	
	public static void main(String[] args) throws Exception {		
		CartagoService.startNode();
		// CartagoService.registerLogger("default",new BasicLogger());
		new HelloAgent("Michelangelo").start(); 
	}
}
