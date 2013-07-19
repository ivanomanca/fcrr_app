!setup.

+!setup
 <- rd("ready");
    .my_name(Name);
    rd("place_alloc",Name,Board);
    lookupArtifact(Board,Id0);
    focus(Id0);
    lookupArtifact("controller",C);
    focus(C).
    
@new_ball [atomic]  
+new_ball(Ball) [artifact_id(Id)] : not moving_ball(_,_)
  <- takeControl(Ball)[artifact_id(Id)];
     .print("Controlling ",Ball);
     +moving_ball(Ball,Id);
     !!move.

+!move : not controlled & moving_ball(Ball,Id) 
  <- .wait(100);
     turnBall(Ball,0.1)[artifact_id(Id)];
     !!move.

+!move : controlled & moving_ball(Ball,Id) 
  <- setBallSpeed(Ball,0)[artifact_id(Id)].

-!move 
  <- println("move failed.").
  
@removal_plan [atomic]
+ball_removed(Ball) : moving_ball(Ball,_)
  <- -moving_ball(Ball,_);
     .drop_intention(move);
     .print("Lost ",Ball).
    

+presenter_control("on"): moving_ball(Ball,Id)
  <- +controlled;
     getBallSpeed(Ball,Speed)[artifact_id(Id)];
     +ball_backup(Speed).

+presenter_control("off"): controlled & moving_ball(Ball,Id)
  <- println("OFF");
     -controlled;
     -ball_backup(Speed);
     setBallSpeed(Ball,Speed)[artifact_id(Id)];
     !!move.

  
//+ball(Ball,X,Y):moving_ball(Ball,_)
//  <- println("new pos: ",X," ",Y).
     
-!setup [error_msg(Msg)]
  <- println(Msg).
  
     