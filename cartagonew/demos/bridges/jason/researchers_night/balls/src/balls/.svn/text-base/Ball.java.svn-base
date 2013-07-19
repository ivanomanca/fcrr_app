package balls;


import java.util.*;

import balls.util.*;


public class Ball implements java.io.Serializable {
    
    private P2d pos;
    private V2d vel;
    private double speed;
    private String name;
    private boolean controlled;
    
    public Ball(String name){
         pos = new P2d(0,0);
         this.name = name;
         controlled = false;
         Random rand = new Random(System.currentTimeMillis());
         double dx = rand.nextDouble();
         vel = new V2d(dx,Math.sqrt(1-dx*dx));
         speed = rand.nextDouble()*3;
    }

    public Ball(String name, P2d pos, V2d v, double speed){
        this.pos = pos;
        this.name = name;
        vel = v;
        this.speed = speed;
    }

    public void setControlled(boolean contr){
    	controlled = contr;
    }
    
    public boolean isControlled(){
    	return controlled;
    }
    public String getName(){
    	return name;
    }
    
    public void turn(double angle){
    	vel.rotate(angle);
    }
    
    public void updatePos(long dt) {
        pos = pos.sum(vel.mul(speed*dt*0.001));
    }
    
    public void setSpeed(double v){
    	speed = v;
    }

    public void setVel(double x, double y){
    	vel.x = x;
    	vel.y = y;
    }

    public void applyConstraints(Boundary bounds) {
        if (pos.x > bounds.getX1()){
        	pos.x = bounds.getX1();
        	vel.x = -vel.x;
        } else if (pos.x < bounds.getX0()){
        	pos.x = bounds.getX0();
        	vel.x = -vel.x;
        } else if (pos.y > bounds.getY1()){
            pos.y = bounds.getY1();
            vel.y = -vel.y;
        } else if (pos.y < bounds.getY0()){
            pos.y = bounds.getY0();
            vel.y = -vel.y;
        }
    }
    
    public P2d getPos(){
        return new P2d(pos.x,pos.y);
    }
    
    public V2d getVel(){
    	return vel;
    }
    
    public double getSpeed(){
    	return speed;
    }
    
    private void log(String msg){
        System.out.println("[BALL] "+msg);
    }

}
