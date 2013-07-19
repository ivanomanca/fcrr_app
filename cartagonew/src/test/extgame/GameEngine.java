package extgame;

import java.util.*;

/**
 * GameEngine framework class. There is a singleton of this class,
 * typically owning the thread of control.
 * 
 * @author aricci
 *
 */
public class GameEngine implements IGameEngine {

	private List<Player> players;
	
	public GameEngine(){
	}
	public void init(){
		
		players = new ArrayList<Player>();
		players.add(new Player("pippo",this,"env0"));
		players.add(new Player("pluto",this,"env1"));
		
		for (Player p: players){
			p.init();
		}
	}

	public void doCycle(){
		for (Player p: players){
			p.play();
		}
	}

	public void actionX(){
		System.out.println("doing action X...");
	}

	public void actionY(){
		System.out.println("doing action Y...");
	}
}
