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
	<- 	println("Inizio sms.")[artifact_id(LocalConsoleID)];
		focusWhenAvailable("sms-manager")[wsp_id(WspID)];
		println("Ready.")[artifact_id(LocalConsoleID)];
		!test_sens_sms.
		
+!test_sens_sms
	<-	send("0123456789", "Hello World!").
	
+sms_received(Source, Message) : art_id(console,LocalConsoleID)
	<-	println(Source, " has sent this message to you ", Message)[artifact_id(LocalConsoleID)].

+sms_status_sent(Destination, Part) : art_id(console,LocalConsoleID)
	<- println("SMS sent, destination:", Destination," part: ",Part)[artifact_id(LocalConsoleID)].
	
+sms_status_error("generic", ErrorCode, Destination, Part) : art_id(console,LocalConsoleID)
	<- println("error: generic, destination:", Destination," part: ",Part)[artifact_id(LocalConsoleID)].
	
+sms_status_error("no_service", _, Destination, Part) : art_id(console,LocalConsoleID)
	<-	println("error: no service, destination:", Destination," part: ",Part)[artifact_id(LocalConsoleID)].
	
+sms_status_error("null_pdu", _, Destination, Part) : art_id(console,LocalConsoleID)
	<-	println("error: null pdu, destination:", Destination," part: ",Part)[artifact_id(LocalConsoleID)].
	
+sms_status_error("radio_off", _, Destination, Part) : art_id(console,LocalConsoleID)
	<-	println("error: radio off, destination:", Destination," part: ",Part)[artifact_id(LocalConsoleID)].