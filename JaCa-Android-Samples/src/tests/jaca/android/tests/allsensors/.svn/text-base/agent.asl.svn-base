!start.

+!start
	<-	!init;
		!do_job.

/*Initialisation plan for joining the MiddlewareServices workspace*/
+!init 
	<-  +join_attempts(0);
		lookupArtifact("console", LocalConsoleID);
		focusWhenAvailable("sensor-art");
		lookupArtifact("sensor-art", GUIID);
		+art_id(console,LocalConsoleID);
		+art_id(gui,GUIID);
		!try_join.

+!try_join : art_id(console,LocalConsoleID) & join_attempts(N) & N < 3
	<-	-+join_attempts(N+1);
		joinRemoteWorkspace("MiddlewareServices", "jaca.android.jacaservice", WspID);
		+wsp_id(jaca_services,WspID);
		println("Join to MiddlewareServices wsp done.")[artifact_id(LocalConsoleID)].

-!try_join : join_attempts(N) & N >= 3 & art_id(console,LocalConsoleID)
	<-	println("Failure, cannot join MiddlewareServices wsp.")[artifact_id(LocalConsoleID)].

-!try_join : art_id(console,LocalConsoleID)
	<-	println("Failure, trying join MiddlewareServices again in one second.")[artifact_id(LocalConsoleID)];
		.wait(1000);
		!!try_join.
		
/************************ Main agent plan *************************/		
+!do_job : art_id(console,LocalConsoleID) & wsp_id(jaca_services,WspID)
	<-	focusWhenAvailable("all-sensors-manager");
		startMonitoring[wsp_id(WspID)];
		println("Ready.")[artifact_id(LocalConsoleID)].

/********* Plans that handle reactions to sensors' events *********/		
+sensor_accelerometer(X,Y,Z) : art_id(gui,GUIID)
	<-  updateAccSensorsInfo(X, Y, Z)[artifact_id(GUIID)].

+sensor_orientation(X,Y,Z) : art_id(gui,GUIID)
	<- 	updateOrientationSensorsInfo(X, Y, Z)[artifact_id(GUIID)].	

+sensor_geomag(X,Y,Z) : art_id(gui,GUIID)
	<- 	updateGeomagSensorsInfo(X, Y, Z)[artifact_id(GUIID)].