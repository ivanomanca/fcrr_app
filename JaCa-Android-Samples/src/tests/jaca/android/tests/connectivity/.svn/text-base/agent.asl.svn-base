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
	<- 	focusWhenAvailable("connectivity-manager")[wsp_id(WspID)];
		println("[TESTING WIFI]")[artifact_id(LocalConsoleID)];
		!test_wifi;
		.wait(10000);
		println("[TESTING AIRPLANE]")[artifact_id(LocalConsoleID)];
		!test_airplane;
		.wait(10000);
		println("[DISABLE ALL]")[artifact_id(LocalConsoleID)];
		!disable_all.
		
+!test_wifi : true
	<- ?art_id(console,LocalConsoleID);
	   println("enabling wifi")[artifact_id(LocalConsoleID)];
	   enableWiFi;
	   println("wifi enabled")[artifact_id(LocalConsoleID)];
	   .wait(15000);
	   println("disabling wifi")[artifact_id(LocalConsoleID)];
	   disableWiFi;
	   println("wifi disabled")[artifact_id(LocalConsoleID)].
	   

+!test_airplane : true
	<- ?art_id(console,LocalConsoleID);
	   println("enabling airplane mode")[artifact_id(LocalConsoleID)];
	   enableAirplaneMode;
	   println("airplane enabled")[artifact_id(LocalConsoleID)];
	   .wait(10000);
	   println("disabling airplane mode")[artifact_id(LocalConsoleID)];
	   disableAirplaneMode;
	   println("airplane disabled")[artifact_id(LocalConsoleID)].

+!disable_all : true
	<- ?art_id(console,LocalConsoleID);
		println("disabling all")[artifact_id(LocalConsoleID)];
	   enableAirplaneSpecific("cell,bluetooth,wifi");
	   println("all disabled")[artifact_id(LocalConsoleID)].
	
+airplane_mode_status(Value) : art_id(console,LocalConsoleID)
	<- println("[STATUS AIRPLANE:]", Value)[artifact_id(LocalConsoleID)].
	
+wifi_status(Value) :art_id(console,LocalConsoleID)
	<- println("[STATUS WIFI:]: ", Value)[artifact_id(LocalConsoleID)].