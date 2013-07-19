!start.

+!start
	<-	!init;
		!do_job.
//##########################################################################
// INIT PLAN
//##########################################################################
+!init
	<-  println("[fcrr-sim:] Running.");
		// Console
		lookupArtifact("console", LocalConsoleID);
		+art_id(console, LocalConsoleID);
		println("[fcrr-sim:] Init done!.").

//##########################################################################
// DOING MY JOB...
//##########################################################################		
+!do_job
	<-	println("[fcrr-sim:] Waiting for tripGUI...")[artifact_id(LocalConsoleID)];
		focusWhenAvailable("dtripinfogui");
		println("[fcrr-sim:] *dtripinfogui* focussed.")[artifact_id(LocalConsoleID)];
		println("[fcrr-sim:] Waiting for start trip...")[artifact_id(LocalConsoleID)].

//##########################################################################
// REACTIVE PLANS
//##########################################################################
+start(TripID) : art_id(console,LocalConsoleID)
	<-	println("[fcrr-sim:] Start trip button pressed (event perceived!)")[artifact_id(LocalConsoleID)];
		focusWhenAvailable("gps-simulator");
		lookupArtifact("gps-simulator", GpsSimID);
		+tripstatus("incorso");
		!go.

+stop(TripID) : art_id(console,LocalConsoleID)
	<-	println("[fcrr-sim:] Stop trip button pressed (event perceived!)")[artifact_id(LocalConsoleID)];
		-tripstatus("incorso").

+!go : tripstatus("incorso")
	<- 	move;
		println("[fcrr-sim:] New gps position created")[artifact_id(LocalConsoleID)];
		.wait(5000);
		!go.  
		
		
		