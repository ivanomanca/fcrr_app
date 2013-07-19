blokedNumberList(["+393494098118","12345","555","789"]).

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
	<- 	focusWhenAvailable("call-manager")[wsp_id(WspID)];
		println("Ready.")[artifact_id(LocalConsoleID)].

/************ Reactive plans for handling incoming calls************/



/*********** Plan for dropping calls from blocked number ***********/
+ringing(Source)
	: blokedNumberList(List) & .member(Source, List) & art_id(console,LocalConsoleID)
	<-  ?art_id(console,LocalConsoleID);
		println("One blocked number is calling to you, dropping off the call")[artifact_id(LocalConsoleID)];
		dropCall.

/** Plan for notifying the incoming call from not blocked number  **/
+ringing(Source) : art_id(console,LocalConsoleID) 
	<-  println("event ringing: ", Source, " is calling to you")[artifact_id(LocalConsoleID)].

+offhook : art_id(console,LocalConsoleID)
	<- println("event offhook")[artifact_id(LocalConsoleID)].
  		
+idle : art_id(console,LocalConsoleID)
	<-  println("event idle")[artifact_id(LocalConsoleID)].


+call_state("idle") : art_id(console,LocalConsoleID)
	<-  println("state idle")[artifact_id(LocalConsoleID)].
	
+call_state("offhook") : art_id(console,LocalConsoleID)
	<-  println("state offhook")[artifact_id(LocalConsoleID)].
	
+call_state("ringing") : art_id(console,LocalConsoleID) & incoming_number(Source)
	<- 	println("state ringing: ", Source, " is calling you")[artifact_id(LocalConsoleID)].