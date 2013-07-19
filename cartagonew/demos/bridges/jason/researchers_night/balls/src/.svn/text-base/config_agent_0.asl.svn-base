!setup_env.

+!setup_env
  <- makeArtifact("ballScreen1","balls.BallScreenA",["Screen 1",false],Id0);
     !genBalls(80,"ballScreen1");
     makeArtifact("ballScreen2","balls.BallScreenA",["Screen 2",false],Id1);
     makeArtifact("ballScreen3","balls.BallScreenA",["Screen 3",false],Id2);
	 linkArtifacts(Id0,"right",Id1);		
	 linkArtifacts(Id1,"left",Id0);			
	 linkArtifacts(Id1,"right",Id2);		
	 linkArtifacts(Id2,"left",Id1);			
     makeArtifact("controller","balls.FakeBallSceneStateA");
          
	 in("ready");
	 in("ready");
	 in("ready");
	 
     .send(ag1,achieve,control("ballScreen1"));    
     .send(ag2,achieve,control("ballScreen2"));    
     .send(ag3,achieve,control("ballScreen3")). 
     
+!genBalls(0,_).
+!genBalls(N,Screen)
  <- .concat("ball-",N,Name);
     createNewBall(Name,0.5,0,math.random*6.28,math.random)[artifact_id(Screen)];
     !genBalls(N-1,Screen).
