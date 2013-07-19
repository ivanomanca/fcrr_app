!setup.

+!setup
 <- .print("ready");
    .my_name(Name);
    .concat("Calc",Name,CalcName);
    makeArtifact(CalcName,"balls.Calculator",[],C);
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

+!resetMoving 
  <- ?ball_to_control(Ball,Screen);
     setBallVel(Ball,1,0)[artifact_id(Screen)]; 
     setBallSpeed(Ball,1)[artifact_id(Screen)].     

+!move : ball_to_control(Ball,Screen) & has_ball
  <- turnBall(Ball,0.3)[artifact_id(Screen)];
     .wait(400);
     turnBall(Ball,-0.3)[artifact_id(Screen)];
     .wait(400);
     !!move.

+centroid(XC,YC) : state("running") & has_ball 
  <- ?ball_to_control(Ball,Screen);
     ?ball(Ball,XP,YP,VX,VY);
     computeAngle(XC,YC,XP,YP,VX,VY,Angle);
     if (Angle > 0.5){
       .println("Angle overflow: ",Angle);
        turnBall(Ball,Angle*0.05)[artifact_id(Screen)];
     }.

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
     !resetMoving;
     !!move.

  
//+ball(Ball,X,Y):moving_ball(Ball,_)
//  <- println("new pos: ",X," ",Y).
     
-!setup [error_msg(Msg)]
  <- println(Msg).
  
     