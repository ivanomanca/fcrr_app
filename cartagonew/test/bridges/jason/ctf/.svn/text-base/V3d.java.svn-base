package ctf;

/**
 *
 * 3-dimensional vector
 * objects are completely state-less
 *
 */
public class V3d implements java.io.Serializable {

    public double x,y,z;

    public V3d(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public V3d(P3d from, P3d to){
        this.x = to.x - from.x;
        this.y = to.y - from.y;
        this.z = to.z - from.z;
    }

    public V3d sum(V3d v){
        return new V3d(x+v.x,y+v.y,z+v.z);
    }

    public double abs(){
        return (double)Math.sqrt(x*x+y*y+z*z);
    }

    public V3d getNormalized(){
        double module=(double)Math.sqrt(x*x+y*y+z*z);
        return new V3d(x/module,y/module,z/module);
    }

    public V3d mul(double fact){
        return new V3d(x*fact,y*fact,z*fact);
    }

    public String toString(){
        return "("+x+","+y+","+z+")";
    }
}
