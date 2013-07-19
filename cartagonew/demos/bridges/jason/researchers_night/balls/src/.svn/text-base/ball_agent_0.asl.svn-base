!setup.

+!setup
 <- out("ready").
 
+!control(Ball,ScreenName)
 <- lookupArtifact(ScreenName,Screen);
    focus(Screen);
    lookupArtifact("controller",C);
    focus(C);
    +ball_to_control(Ball,Screen);
    !!check_ball_available(Ball,Screen).

@check_ball_avail  
+!check_ball_available(Ball,Screen)
  <- takeControl(Ball)[artifact_id(Screen)];
     .print("Controlling ",Ball);
     !!move.
-!check_ball_available(Ball,Screen)
  <- .print("check ball ",Ball," failed.").
 
    
@new_ball 
+new_ball(Ball) : ball_to_control(Ball,Screen) 
  <- .print("new ball detcted.");
     takeControl(Ball)[artifact_id(Screen)];
     .print("Controlling ",Ball);
     !!move.


+!move : not controlled & ball_to_control(Ball,Screen) 
  <- .wait(100);
     turnBall(Ball,0.1)[artifact_id(Screen)];
     !!move.

+!move : controlled & ball_to_control(Ball,Screen) 
  <- setBallSpeed(Ball,0)[artifact_id(Screen)].

-!move 
  <- println("move failed.").
  
@removal_plan [atomic]
+ball_removed(Ball) : ball_to_control(Ball,Screen)
  <- .drop_intention(move);
     .print("Lost ",Ball).
    
    
+presenter_control("on"): ball_to_control(Ball,Screen)
  <- +controlled;
     getBallSpeed(Ball,Speed)[artifact_id(Id)];
     +ball_backup(Speed).

+presenter_control("off"): controlled & ball_to_control(Ball,Screen)
  <- println("OFF");
     -controlled;
     -ball_backup(Speed);
     setBallSpeed(Ball,Speed)[artifact_id(Screen)];
     !!move.

  
//+ball(Ball,X,Y):moving_ball(Ball,_)
//  <- println("new pos: ",X," ",Y).
     
-!setup [error_msg(Msg)]
  <- println(Msg).
  
     