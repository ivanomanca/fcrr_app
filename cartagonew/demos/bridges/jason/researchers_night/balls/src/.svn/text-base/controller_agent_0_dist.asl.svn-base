//events_wsp("events","137.204.73.240:20000").
events_wsp("default","137.204.71.165").

!setup.

+!setup
 <- createWorkspace("balls");
    joinWorkspace("balls",WspId);
    makeArtifact("ballScreen","balls.BallScreenA",["Ball Screen",false],Id);
    +screen(Id);
    println("ballScreen ready.");
    !locateRemoteWsp;
    !locateController;
    .println("ready.");
    cartago.set_current_wsp(WspId);
    println("ready.").

+!locateRemoteWsp
 <- ?events_wsp(Name,WspAddr);
    joinRemoteWorkspace(Name,WspAddr,Id);
    println("Event WSP joined.").
 
-!locateRemoteWsp
 <- println("Locating events wsp..");
    .wait(2000);
    !locateRemoteWsp.
 
+!locateController
 <- lookupArtifact("BallSceneState",C);
    focus(C);
    println("Controller artifact found.").
    
-!locateController
 <- println("Locating controller artifact..");
    .wait(2000);
    !locateController.
/*
@freeze [atomic]    
+freeze
  <- freeze.
     
@go [atomic]
+go
  <- start.
*/

@freeze [atomic]    
+presenter_control("off")
  <- ?screen(Id);
     freeze [artifact_id(Id)].
     
@go [atomic]
+presenter_control("on")
  <- ?screen(Id);
     start [artifact_id(Id)].
     