package ctf;

import cartago.*;

public class MedicalPack extends Artifact {
	
	private double energyLevel;
	
	void init(double x, double y, double z, double enLevel, double observabilityRadius){
		setupPosition(new P3d(x,y,z),observabilityRadius);
		defineObsProperty("pos",x,y,z);
		defineObsProperty("shape","medical_pack");
		// defineObsProperty("energyLevel",ammoLevel);
		energyLevel = enLevel;
		log("pos "+x+" "+y);
	}
	
	@OPERATION void consume(OpFeedbackParam<Double> healthLevel){
		try {
			healthLevel.set(energyLevel);
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
