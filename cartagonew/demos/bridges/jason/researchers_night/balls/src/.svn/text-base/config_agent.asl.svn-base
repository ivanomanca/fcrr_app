screen("ballScreen1","Screen 1","default","127.0.0.1").
screen("ballScreen2","Screen 2","default","127.0.0.1").
screen("ballScreen3","Screen 3","default","127.0.0.1").

+!configure
  <- !configure_screens;
     !configure_links;
     !alloc_tasks;
     !create_balls;
     

+!configure_screens
  <- for ( screen(ScreenName, ScreenTitle, WspName, Where) ) {
       // joinRemoteWorkspace(WspName,Where,WspID);
       createWorkspace(WspName);
       joinWorkspace(WspName,WspID);
       makeArtifact(ScreenName,"balls.BallScreen",[ScreenTitle,false],Id);
       +screen_id(ScreenName,Id,WspID);
     }.

+!configure_links
  <- for ( links(ScreenLeft,ScreenRight) ) {
       ?screen_id(ScreenLeft,IdLeft,_);
       ?screen_id(ScreenRight,IdRight,_);
       linkArtifacts(IdLeft,"left",IdRight);		
       linkArtifacts(IdRight,"right",IdLeft);		
     }.     
     
+!alloc_tasks 
  <- for ( screen_id(ScreenName,Id,WspId) ) {
       in("ready")[workspace_id(WspId)];
       in("ready")[workspace_id(WspId)];
       in("ready")[workspace_id(WspId)];
       out("task","ag1","ball1")[workspace_id(WspId)]
       out("task","ag2","ball2")[workspace_id(WspId)]
       out("task","ag3","ball3")[workspace_id(WspId)]
       in("ready1")[workspace_id(WspId)];
       in("ready1")[workspace_id(WspId)];
       in("ready1")[workspace_id(WspId)];
       start [artifact_id(Id)];
     }.     

+!create_balls
  <- screen_id("ballScreen1",Id,WspID);
     createNewBall("ball1",0,0,1,0)[artifact_id(Id)];
     createNewBall("ball2",0.2,0,1,0)[artifact_id(Id)];
     createNewBall("ball3",0,0.2,1,0)[artifact_id(Id)].

  
     
+!genBalls(0,_).
+!genBalls(N,Screen)
  <- .concat("ball-",N,Name);
     createNewBall(Name,0.5,0,math.random*6.28,math.random)[artifact_id(Screen)];
     !genBalls(N-1,Screen).

+!setup_env
  <- makeArtifact("ballBoard1","balls.BallScreen",["Screen 1",false],Id0);
     //!genBalls(100,"ballBoard-0");
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

     