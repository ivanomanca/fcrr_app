screen("screen2","137.204.71.168:22000").
screen("screen1","137.204.73.240:21000").

//screen("screen1","137.204.73.240:21000").
//screen("screen2","137.204.73.240:22000").

link("screen1","screen2").

!configure.

+!configure
  <- // !setupLocalEnv;
     !locate_screens;
     !configure_links;
     !create_balls.
     
+!setupLocalEnv
  <- createWorkspace("events");
     joinWorkspace("events",WspId);
     makeArtifact("BallSceneState","balls.FakeBallSceneStateA").

          
+!locate_screens
  <- for ( screen(Name,Where) ) {
       !locateRemoteBallWsp(Where,WspId);
       !locateBallScreen(Id);
       +screen_id(Name,Id,WspId);
     }.     
     
+!configure_links
  <- println("configuring links...");
     for ( link(ScreenLeft,ScreenRight) ) {
       println("configuring ",ScreenLeft," ",ScreenRight);
       ?screen(ScreenLeft,AddressLeft);
       ?screen(ScreenRight,AddressRight);
       ?screen_id(ScreenLeft,IdLeft,WspId1);
       ?screen_id(ScreenRight,IdRight,WspId2);
       getNodeId(NodeId2)[wsp_id(WspId2)];
       getNodeId(NodeId1)[wsp_id(WspId1)];
       enableLinkingWithNode(NodeId2, "default", AddressRight)[wsp_id(WspId1)];
       enableLinkingWithNode(NodeId1, "default", AddressLeft)[wsp_id(WspId2)];
       linkArtifacts(IdLeft,"right",IdRight)[wsp_id(WspId1)];		
       linkArtifacts(IdRight,"left",IdLeft)[wsp_id(WspId2)];		
       println("Artifact ",IdLeft," ",IdRight," linked.");	
     }.     

+!create_balls
  <- ?screen_id("screen1",Id,_);
     !genBalls(80,Id).
     
+!genBalls(0,_).
+!genBalls(N,Screen)
  <- .concat("ball-",N,Name);
     createNewBall(Name,0.5,0,math.random*6.28,math.random)[artifact_id(Screen)];
     !genBalls(N-1,Screen).

+!locateRemoteBallWsp(Where,WspId)
 <- println("Locating balls at ",Where);
    joinRemoteWorkspace("balls",Where,WspId);
    println("Ball wsp joined.").
 
-!locateRemoteBallWsp(Where,WspId)
 <- println("Locating balls wsp at ",Where," failed. Retrying.");
    .wait(2000);
    !locateRemoteBallWsp(Where,WspId).
     
+!locateBallScreen(Id)
 <- lookupArtifact("ballScreen",Id);
    println("ballScreen artifact found.").
    
-!locateBallScreen(Id)
 <- println("Locating ball screen artifact..");
    .wait(2000);
    !locateBallScreen(Id).
     