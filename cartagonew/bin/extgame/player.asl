!setup.

+!setup
  	<- 	!discoverGame(Id);
  		focus(Id);
  		println("ready.").	
  
//   
+game_state(1)[artifact_id(Id)]    
	<- println("my turn.");
	   !doMoves(Id);
	   moveDone [artifact_id(Id)].

+!doMoves(Id)
	<-	doActionX [artifact_id(Id)];
		doActionY [artifact_id(Id)].
	 
+!discoverGame(Id) 
	<- 	?mybridge(BridgeName);
		println("looking for ",BridgeName);
		lookupArtifact(BridgeName,Id).

-!discoverGame(Id)
	<- 	.wait(100);
		!discoverGame(Id).
		     
      