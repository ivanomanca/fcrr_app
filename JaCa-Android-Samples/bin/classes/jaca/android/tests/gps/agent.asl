!start.

+!start
	<-	!init;
		!do_job.

/*Initialisation plan for joining the MiddlewareServices workspace*/
+!init 
	<-  lookupArtifact("console", LocalConsoleID);
		+art_id(console,LocalConsoleID);
		+join_attempts(0);
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
+!do_job : wsp_id(jaca_services, WspID) & art_id(console,LocalConsoleID)
	<- 	focusWhenAvailable("gps-manager")[wsp_id(WspID)];
		startMonitoring[wsp_id(WspID)];
		println("Ready.")[artifact_id(LocalConsoleID)].
		
+latitude(Latitude) : art_id(console,LocalConsoleID)
	<-	println("Latitude: ", Latitude)[artifact_id(LocalConsoleID)].
  
+longitude(Longitude) : art_id(console,LocalConsoleID)
	<- 	println("Longitude: ", Longitude)[artifact_id(LocalConsoleID)].
	
+altitude(Altitude) : art_id(console,LocalConsoleID)
	<- 	println("altitude: ", Altitude)[artifact_id(LocalConsoleID)].
	
+accurancy(Accuracy) : art_id(console,LocalConsoleID)
	<-	println("accurancy: ", Accuracy)[artifact_id(LocalConsoleID)].
	
+bearing(Bearing) : art_id(console,LocalConsoleID)
	<-	println("bearing: ", Bearing)[artifact_id(LocalConsoleID)].
	
+speed(Speed) : art_id(console,LocalConsoleID)
	<-	println("speed: ", Speed)[artifact_id(LocalConsoleID)].
	
+time(Time) : art_id(console,LocalConsoleID)
	<-	println("time: ", Time)[artifact_id(LocalConsoleID)].
	


+onLocationChange(Provider) : art_id(console,LocalConsoleID)
	<- println("onLocationChange: ", Provider)[artifact_id(LocalConsoleID)].
  	 
+onProviderEnabled(Provider) : art_id(console,LocalConsoleID)
	<- println("onProviderEnabled: ", Provider)[artifact_id(LocalConsoleID)].
	
+onProviderDisabled(Provider) : art_id(console,LocalConsoleID)
	<- println("onProviderDisabled: ", Provider)[artifact_id(LocalConsoleID)].