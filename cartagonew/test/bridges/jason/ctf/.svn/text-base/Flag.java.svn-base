package ctf;

import cartago.*;

public class Flag extends Artifact {
	
	void init(double x, double y, double z, double observabilityRadius){
		setupPosition(new P3d(x,y,z),observabilityRadius);
		defineObsProperty("pos",x,y,z);
		defineObsProperty("free",true);
		defineObsProperty("carried_by","no_one");
		defineObsProperty("shape","flag");
		log("pos "+x+" "+y+" "+z);
	}
	
	@OPERATION void catchThis(){
		ObsProperty isFree = getObsProperty("free");
		if (isFree.booleanValue()){
			isFree.updateValue(false);
			ArtifactId body = this.getOpUserBody();
			try {
				execLinkedOp(body,"startCarrying",getId());
				getObsProperty("carried_by").updateValue(this.getOpUserId().getAgentName());
			} catch (Exception ex){
				ex.printStackTrace();
				failed("catchFlag");
			}
		} else {
			failed("not_free");
		}
	}

	@OPERATION void releaseThis(){
		getObsProperty("free").updateValue(true);
		getObsProperty("carried_by").updateValue("no_one");
		ArtifactId body = this.getOpUserBody();
		try {
			execLinkedOp(body,"stopCarrying",getId());
		} catch (Exception ex){
			ex.printStackTrace();
			failed("releaseFlag");
		}
}
	
	@LINK @OPERATION void moveTo(double x, double y, double z){
		ObsProperty prop = getObsProperty("pos");
		prop.updateValue(0, x);
		prop.updateValue(1, y);
		prop.updateValue(2, z);
		P3d pos = new P3d(x,y,z);
		this.updatePosition(pos);
		log("new pos: "+pos);
	}
}
