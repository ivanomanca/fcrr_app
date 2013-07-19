package cartago.tools.inspector;

public class Marker {

	private P2d pos;
    private V2d vel;
    
	public Marker(P2d pos){
		this.pos = pos;
		vel = new V2d(0,0);
	}
	
	public P2d getPos(){
		return pos;
	}
	
	public V2d getVel(){
		return vel;
	}
	
	public P2d updatePos(P2d p){
		pos = p;
		return pos;
	}

	public V2d updateVel(V2d v){
		vel = v;
		return vel;
	}
}
