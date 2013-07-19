!setup_env.

+!setup_env
  <- makeArtifact("ballBoard-0","balls.BallScreen",["Screen 1",false],Id0);
     createNewBall("ball-0-0",0,0,1,1)[artifact_id(Id0)];
     createNewBall("ball-0-1",0.5,0,2,0.5)[artifact_id(Id0)];
     createNewBall("ball-0-2",0,0.5,-1,1.05)[artifact_id(Id0)];
     makeArtifact("ballBoard-1","balls.BallScreen",["Screen 2",false],Id1);
     createNewBall("ball-1-0",0,0,1,0.2)[artifact_id(Id1)];
     createNewBall("ball-1-1",0.5,0,2,0.9)[artifact_id(Id1)];
     createNewBall("ball-1-2",0,0.5,-1,0.15)[artifact_id(Id1)];
     makeArtifact("ballBoard-2","balls.BallScreen",["Screen 3",false],Id2);
     createNewBall("ball-2-0",0,0,1,0.01)[artifact_id(Id2)];
     createNewBall("ball-2-1",0.5,0,2,1.25)[artifact_id(Id2)];
     createNewBall("ball-2-2",0,0.5,-1,0.05)[artifact_id(Id2)];
	   linkArtifacts(Id0,"right",Id1);		
	   linkArtifacts(Id1,"left",Id0);			
	   linkArtifacts(Id1,"right",Id2);		
	   linkArtifacts(Id2,"left",Id1);			
     //testRightLink [artifact_id(Id0)];
     //testLeftLink [artifact_id(Id1)];
     makeArtifact("controller","balls.FakeBallSceneState");
     
     start [artifact_id(Id0)];
     start [artifact_id(Id1)];
     start [artifact_id(Id2)];
     
     out("place_alloc","ball_agent_0","ballBoard-0");
     out("place_alloc","ball_agent_1","ballBoard-1");
     out("place_alloc","ball_agent_2","ballBoard-2");
     
     out("ready").     