!setup_env.

+!setup_env
  <- makeArtifact("ballBoard1","balls.BallScreen",["Screen 1",false],Id0);
     //!genBalls(100,"ballBoard-0");
     //createNewBall("ball-1",0.5,0,2,0.5)[artifact_id(Id0)];
     //createNewBall("ball-2",0,0.5,-1,1.05)[artifact_id(Id0)];
     //createNewBall("ball-3",0,0.25,2,0.85)[artifact_id(Id0)];
     //createNewBall("ball-4",0,0.5,0,0.05)[artifact_id(Id0)];
     //createNewBall("ball-5",0,0.5,-1.5,1.05)[artifact_id(Id0)];
     makeArtifact("ballBoard2","balls.BallScreen",["Screen 2",false],Id1);
     makeArtifact("ballBoard3","balls.BallScreen",["Screen 3",false],Id2);
	 linkArtifacts(Id0,"right",Id1);		
	 linkArtifacts(Id1,"left",Id0);			
	 linkArtifacts(Id1,"right",Id2);		
	 linkArtifacts(Id2,"left",Id1);			
     makeArtifact("controller","balls.FakeBallSceneState");
     

     for ( .range(I,1,9) ) {
	   in("ready");
     }
     
     .send(ag11,achieve,control("ball1","ballBoard1"));    
     .send(ag12,achieve,control("ball2","ballBoard1"));    
     .send(ag13,achieve,control("ball3","ballBoard1"));    
     
     .send(ag21,achieve,control("ball1","ballBoard2"));    
     .send(ag22,achieve,control("ball2","ballBoard2"));    
     .send(ag23,achieve,control("ball3","ballBoard2"));    
     
     .send(ag31,achieve,control("ball1","ballBoard3"));    
     .send(ag32,achieve,control("ball2","ballBoard3"));    
     .send(ag33,achieve,control("ball3","ballBoard3"));

     for ( .range(I,1,9) ) {
	   in("ready1");
     }
     start [artifact_id(Id0)];
     start [artifact_id(Id1)];
     start [artifact_id(Id2)];
     
     createNewBall("ball1",0,0,1,0)[artifact_id(Id0)];
     createNewBall("ball2",0.2,0,1,0)[artifact_id(Id0)];
     createNewBall("ball3",0,0.2,1,0)[artifact_id(Id0)].
     

     
+!genBalls(0,_).
+!genBalls(N,Screen)
  <- .concat("ball-",N,Name);
     createNewBall(Name,0.5,0,math.random*6.28,math.random)[artifact_id(Screen)];
     !genBalls(N-1,Screen).
