package ctf;

import cartago.*;

public class AmmoPack extends Artifact {
	
	private int ammoLevel;
	
	void init(double x, double y, double z, int amLevel, double observabilityRadius){
		setupPosition(new P3d(x,y,z),observabilityRadius);
		defineObsProperty("pos",x,y,z);
		defineObsProperty("shape","ammo_pack");
		// defineObsProperty("ammoLevel",ammoLevel);
		ammoLevel = amLevel;
		log("pos "+x+" "+y);
	}
	
	@OPERATION void consume(OpFeedbackParam<Integer> amLevel){
		try {
			amLevel.set(ammoLevel);
			dispose(this.getId());
		} catch(Exception ex){
			failed("consume");
		}
	}
	
	@OPERATION void moveTo(double x, double y, double z){
		ObsProperty prop = getObsProperty("pos");
		prop.updateValue(0, x);
		prop.updateValue(1, y);
		prop.updateValue(2, z);
		this.updatePosition(new P3d(x,y,z));
	}
}
