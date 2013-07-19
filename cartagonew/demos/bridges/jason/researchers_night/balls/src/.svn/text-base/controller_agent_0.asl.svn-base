!setup.

+!setup
 <- out("ready").
 
+!control(ScreenName)
 <- lookupArtifact(ScreenName,Screen);
    +screen(Screen);
    lookupArtifact("controller",C);
    focus(C).

@freeze [atomic]    
+freeze: screen(Id)
  <- freeze [artifact_id(Id)].
     
@go [atomic]
+go: screen(Id)
  <- start [artifact_id(Id)].
    
     