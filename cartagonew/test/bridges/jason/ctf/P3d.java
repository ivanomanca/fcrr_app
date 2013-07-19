package ctf;

import cartago.*;

/**
 *
 * 3-dimensional point
 * objects are completely state-less
 *
 */
public class P3d  extends AbstractWorkspacePoint implements java.io.Serializable  {

    public double x,y,z;

    public P3d(double x,double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistanceFrom(P3d p){
    	double dx = p.x - x;
    	double dy = p.y - y;
    	double dz = p.z - z;
        return (double)Math.sqrt(dx*dx+dy*dy+dz*dz);
    }
    
    public P3d sum(V3d v){
        return new P3d(x+v.x,y+v.y,z+v.z);
    }

    public V3d sub(P3d v){
        return new V3d(x-v.x,y-v.y,z-v.z);
    }

    public String toString(){
        return "("+x+","+y+","+z+")";
    }

}
