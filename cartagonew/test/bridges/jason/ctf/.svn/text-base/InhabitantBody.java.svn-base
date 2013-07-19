package ctf;

import cartago.*;
import java.util.*;

public class InhabitantBody extends AgentBodyArtifact {

	private List<ArtifactId> carriedObjects;
	private int ammonition;
	private boolean wantToWalk;
	// public static enum GarmentType { REGULAR_HELMET, REDCROSS_HELMET, BULLET_HELMET}
	
	void init(double x, double y, double z, String garm, double observabilityRadius, double observingRadius){
		setupPosition(new P3d(x,y,z),observabilityRadius,observingRadius);
		defineObsProperty("pos",x,y,z);
		defineObsProperty("health",0.0);
		defineObsProperty("garment",garm);
		defineObsProperty("walking",false);
		ammonition = 0;
		wantToWalk = false;
		carriedObjects = new ArrayList<ArtifactId>();
		log("pos "+x+" "+y);
	}
		
	@OPERATION void moveTo(double x, double y, double z){
		ObsProperty prop = getObsProperty("pos");
		prop.updateValue(0, x);
		prop.updateValue(1, y);
		prop.updateValue(2, z);
		updatePosition(new P3d(x,y,z));
		execInternalOp("updateCarriedObjectPos");
	}

	@INTERNAL_OPERATION void updateCarriedObjectPos(){
		ObsProperty prop = getObsProperty("pos");
		for (ArtifactId id: carriedObjects){
			try {
				execLinkedOp(id, "moveTo", prop.doubleValue(0),prop.doubleValue(1),prop.doubleValue(2));
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	@OPERATION void stop(){
		if (wantToWalk){
			wantToWalk = false;
		}
	}

	@OPERATION void walk(double dx, double dy, double dz, double speed){
		if (!wantToWalk){
			wantToWalk = true;
			V3d vel = new V3d(dx,dy,dz);
			this.getObsProperty("walking").updateValue(true);
			execInternalOp("walking",vel,speed);
		}
	}
	
	@OPERATION void shoot(int nAmmo){
		if (nAmmo <= ammonition){
			ammonition-=nAmmo;
			/* TODO create projectiles cloud */
			log("shooting!");
		} else {
			failed("not_enough_ammo");
		}
	}
	
	@INTERNAL_OPERATION void walking(V3d vel, double speed){
		while (wantToWalk){
			ObsProperty prop = getObsProperty("pos");
			P3d currentPos = new P3d(prop.doubleValue(0), prop.doubleValue(1), prop.doubleValue(2));
			P3d newPos = currentPos.sum(vel.mul(speed));
			prop.updateValue(0, newPos.x);
			prop.updateValue(1, newPos.y);
			prop.updateValue(2, newPos.z);
			updatePosition(newPos);
			log("pos: "+newPos);
			execInternalOp("updateCarriedObjectPos");
			await_time(100);
		}
		this.getObsProperty("walking").updateValue(false);
	}

	@LINK void startCarrying(ArtifactId what){
		carriedObjects.add(what);
		defineObsProperty("is_carrying",what.getName());
		execInternalOp("updateCarriedObjectPos");
	}

	@LINK void stopCarrying(ArtifactId what){
		carriedObjects.remove(what);
		removeObsPropertyByTemplate("is_carrying",what.getName());
		execInternalOp("updateCarriedObjectPos");
	}
}
