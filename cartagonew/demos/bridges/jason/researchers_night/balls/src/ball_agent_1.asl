!setup.

+!setup
 <- .print("ready");
    out("ready").
 
+!control(Ball,ScreenName)
 <- lookupArtifact(ScreenName,Screen);
    +ball_to_control(Ball,Screen);
    focus(Screen);
    lookupArtifact("controller",C);
    focus(C);
    out("ready1").
     
    
+new_ball(Ball) : ball_to_control(Ball,Screen) & not has_ball & state("running")
  <- .print("ball detected ",Ball," in ", Screen);
     +has_ball;
     !!move.

+new_ball(Ball) : ball_to_control(Ball,Screen) & not has_ball & not state("running")
  <- .print("ball detected ",Ball," in ", Screen);
     +has_ball.
     

+!move : ball_to_control(Ball,Screen) 
  <- .wait(100);
     turnBall(Ball,0.1)[artifact_id(Screen)];
     !!move.

-!move 
  <- println("move failed.").
  
@removal_plan [atomic]
+ball_removed(Ball) : ball_to_control(Ball,Screen)
  <- -has_ball;
     .drop_intention(move);
     .print("Lost ",Ball).
    
@go_on [atomic]    
+state("idle"): ball_to_control(Ball,Screen) & has_ball
  <- .println("IDLE");
     setBallSpeed(Ball,0)[artifact_id(Screen)];
     .drop_intention(move).
     
@stop [atomic]
+state("running"): ball_to_control(Ball,Screen) & has_ball
  <- .println("RUNNING");
     setBallSpeed(Ball,1)[artifact_id(Screen)];
     !!move.

  
//+ball(Ball,X,Y):moving_ball(Ball,_)
//  <- println("new pos: ",X," ",Y).
     
-!setup [error_msg(Msg)]
  <- println(Msg).
  
     