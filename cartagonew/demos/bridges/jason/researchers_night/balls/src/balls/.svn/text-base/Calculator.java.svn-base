package balls;

import cartago.*;
import balls.util.*;

public class Calculator extends Artifact {

	@OPERATION void computeAngle(double xc, double yc, double xp, double yp, double velx, double vely, OpFeedbackParam<Double> angle){
		V2d v0 = new V2d(xc-xp,yc-yp).getNormalized();
		double sp = v0.x*velx + v0.y*vely;
		angle.set(Math.acos(sp));
	}
}
