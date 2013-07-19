package balls;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.*;
import balls.util.*;
import cartago.*;

@ARTIFACT_INFO(
		outports = {
				@OUTPORT(name = "left"),
				@OUTPORT(name = "right")
		}
)
public class BallScreen extends Artifact {
    
    private boolean stop;
    private BallScreenFrame frame;
    
    private Boundary bounds;
    private ArrayList<Ball> balls;
    private HashMap<String,AgentId> controllers;
    
    void init(String screenTitle, boolean fullScreen){
        stop = false;
        bounds = new Boundary(-1.0,-1.0,1.0,1.0);
        balls = new ArrayList<Ball>();
        controllers = new HashMap<String,AgentId>();

        GraphicsEnvironment env = GraphicsEnvironment.
        getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();
        frame = new BallScreenFrame(screenTitle,fullScreen,devices[0]);
        frame.setVisible(true);

        defineObsProperty("centroid",0,0);
   }
    
    @OPERATION void start(){
    	stop = false;
    	this.execInternalOp("update");
    }
    
    @OPERATION void stop(){
    	stop = true;
    }

    @OPERATION void turnBall(String name, double angle){
    	Ball b = findBall(name);
    	if (b != null){
    		b.turn(angle);
    	} else {
    		log("turnBall - WRONG BALL "+name+" REQUESTED BY "+this.getOpUserId());
    	}
    }

    @OPERATION void setBallSpeed(String name, double speed){
    	Ball b = findBall(name);
    	if (b != null){
    		b.setSpeed(speed);
    	} else {
    		log("setBallSpeed - WRONG BALL "+name+" REQUESTED BY "+this.getOpUserId());
    	}
    }
    
    @OPERATION void setBallVel(String name, double x, double y){
    	Ball b = findBall(name);
    	if (b != null){
    		b.setVel(x,y);
    	} else {
    		log("setBallVel - WRONG BALL "+name+" REQUESTED BY "+this.getOpUserId());
    	}
    }

    @OPERATION void getBallSpeed(String name, OpFeedbackParam<Double> speed){
    	Ball b = findBall(name);
    	if (b != null){
    		speed.set(b.getSpeed());
    	} else {
    		log("getBallVel - WRONG BALL "+name+" REQUESTED BY "+this.getOpUserId());
    	}
    }
    
    private Ball findBall(String name){
    	for (Ball b: balls){
    		if (b.getName().equals(name)){
    			return b; 
    		}
    	}
    	return null;
    }
    
    @INTERNAL_OPERATION void update(){
    	long prevTime;
    	long currentTime;
    	
    	currentTime = prevTime = System.currentTimeMillis();
    	long dt;
    	while (!stop) {
    		currentTime = System.currentTimeMillis();
        	dt = currentTime - prevTime;
        	prevTime = currentTime;

        	Iterator<Ball> it = balls.iterator();
        	double xc = 0;
        	double yc = 0;
        	
        	while (it.hasNext()){
        		Ball b = it.next();
        		b.updatePos(dt);
        		if (bounds.isOutOfBoundRight(b.getPos()) && isLinked("right")){
        			try {
        				execLinkedOp("right", "ballEnteredFromRight", b);
        				it.remove();
        		        signal("ball_removed",b.getName());
        	    		controllers.remove(b.getName());
        				removeObsPropertyByTemplate("ball",b.getName(),null,null,null,null);
        			} catch (Exception ex1){
        				ex1.printStackTrace();
        				log("moving ball to right failed");
        			}
        		} else if (bounds.isOutOfBoundLeft(b.getPos()) && isLinked("left")){
        			try {
        				execLinkedOp("left", "ballEnteredFromLeft", b);
        				it.remove();
        	    		controllers.remove(b.getName());
        		        signal("ball_removed",b.getName());
        				removeObsPropertyByTemplate("ball",b.getName(),null,null,null,null);
        			} catch (Exception ex1){
        				ex1.printStackTrace();
        				log("moving ball to left failed");
        			}
        		} else {
        			try {
        				b.applyConstraints(bounds);
        				ObsProperty prop = this.getObsPropertyByTemplate("ball",b.getName(),null,null,null,null);
        				prop.updateValues(b.getName(),b.getPos().x, b.getPos().y,b.getVel().x,b.getVel().y);
        				xc += b.getPos().x;
        				yc += b.getPos().y;
        			} catch (Exception ex){
        				ex.printStackTrace();
        			}
        		}
        		getObsProperty("centroid").updateValues(xc,yc);
        	}
        	
            frame.updatePosition(getPositions());
            //log("update pos");
            await_time(20);
        }
    }
    
    @OPERATION void createNewBall(String ballName, double x, double y, double angle, double speed){
    	P2d pos = new P2d(x,y);
    	V2d vel = new V2d(Math.cos(angle), Math.sin(angle));
        Ball ball = new Ball(ballName, pos, vel, speed);
        defineObsProperty("ball",ballName,pos.x,pos.y,vel.x,vel.y);
        balls.add(ball);
        signal("new_ball",ballName);
    }
    
    @OPERATION void removeBall(){
        if (balls.size()>0){
            Ball ball = (Ball)balls.get(0);
            balls.remove(ball);
       	}
    }
    
    @OPERATION void takeControl(String ball){
    	Ball b = findBall(ball);
    	if (b == null){
    		failed("not_available");
    	} else {
	    	AgentId id = controllers.get(ball);
	    	if (id == null){
	    		controllers.put(ball, this.getOpUserId());
	        	b.setControlled(true);        	
	    	} else {
	    		failed("already_controlled");
	    	}
    	}
    }
        
    @LINK void ballEnteredFromLeft(Ball b){
        Ball ball = new Ball(b.getName(),new P2d(bounds.getX0()+2,b.getPos().y),b.getVel(),b.getSpeed());
        balls.add(ball);
        defineObsProperty("ball",b.getName(),b.getPos().x,b.getPos().y,b.getVel().x,b.getVel().y);
        signal("new_ball",b.getName());
    }

    @LINK void ballEnteredFromRight(Ball b){
        Ball ball = new Ball(b.getName(),new P2d(bounds.getX1()-2,b.getPos().y),b.getVel(),b.getSpeed());
        balls.add(ball);
        defineObsProperty("ball",b.getName(),b.getPos().x,b.getPos().y,b.getVel().x,b.getVel().y);
        signal("new_ball",b.getName());
    }

    private BallInfo[] getPositions(){
        BallInfo[] array = new BallInfo[balls.size()];
        for (int i=0; i<array.length; i++){
        	Ball b = balls.get(i);
            array[i] = new BallInfo(b.getPos(),b.isControlled());
        }
        return array;
    }
    
    // test interface
    
    @OPERATION void testLeftLink(){
    	try {
    		execLinkedOp("left", "test");
    	} catch (Exception ex){
    		ex.printStackTrace();
    		failed("failed");
    	}
    }

    @OPERATION void testRightLink(){
    	try {
    		execLinkedOp("right", "test");
    	} catch (Exception ex){
    		ex.printStackTrace();
    		failed("failed");
    	}
    }
    
    @LINK void test(){
    	log("called link");
    }
    

}
