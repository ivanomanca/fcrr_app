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
	<- 	focusWhenAvailable("phone-settings-manager")[wsp_id(WspID)];
		println("Ready.")[artifact_id(LocalConsoleID)];
		!test_ringer.

+!test_ringer
	<-	setRingerMode("silent");
		setRingerMode("normal");
		setRingerMode("vibrate").

+ringer_mode(Value) : art_id(console,LocalConsoleID)
	<- println("Ring mode value ", Value)[artifact_id(LocalConsoleID)].