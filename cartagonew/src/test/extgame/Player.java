package extgame;

import cartago.*;
import cartago.util.agent.*;
import jason.infra.centralised.RunCentralisedMAS;

public class Player  {

	private CartagoBasicContext context;
	private String name;
	private IGameEngine gameEngine;
	private ArtifactId bridge;
	private String bridgeName;
	
	public Player(String name, IGameEngine game, String bridgeName){
		this.name = name;
		this.gameEngine = game;
		this.bridgeName = bridgeName;
	}
	
	public void init(){

		try {
			
			/* first time? */
			if (!CartagoService.isNodeActive()){
		        
		        CartagoService.startNode();
				log("CArtAgO node setup.");
				
				while (!CartagoService.isNodeActive()){
					Thread.sleep(100);
				}

				log("Spawning the MAS");

				//System.out.println(">> "+new java.io.File(".").getAbsolutePath());
				new Thread(){
					public void run(){
						RunCentralisedMAS runner = new RunCentralisedMAS();
				        runner.init(new String[]{"src/test/extgame/mas.mas2j"});
					}
				}.start();

			}
			
			context = new CartagoBasicContext(name);
			bridge = context.makeArtifact(bridgeName, "extgame.PlayerBridge", new Object[]{ gameEngine });

			log("Bridge artifact created: "+bridge);
			
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void play(){
		log("playing...");
		try {
			context.doAction(bridge, new Op("triggerNewMove"));
		} catch (Exception ex){
			ex.printStackTrace();
		}
		log("move done.");
	}

	private void log(String msg){
		System.out.println("[Player-"+name+"] "+msg);
	}
	
}
