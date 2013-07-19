/*
 * jaca.android.tests.service.counterservice is 
 * the Intent managed bythat the service we want to instantiate
 */
!init.

+!init 
	<- 	println("init");
		makeArtifact("service", "jaca.android.tools.ServiceArtifact", ["a.b","jaca.android.tests.service.ITestCounter"], ServiceArtID);
		focus(ServiceArtID);
		+art_id(service,ServiceArtID);
		focusWhenAvailable("activity");
		println("Init done, starting the service");
		//Action from the application activity
		startImplicitService("jaca.android.tests.service.counter").

		
+state(State) [artifact_name(_,"service")]
	<-	println("service state: ", State).

+service_connected : art_id(service, ServiceArtID)
	<-  println("Service connected. Starting using the service as a counter");
		inc;
		getValue(X);
		println("counter service value: ", X);
		inc;
		getValue(Y);
		println("counter service value: ", Y);
		println("stopping service");
		disposeArtifact(ServiceArtID).
	
+service_disconnected
	<- 	println("service successfully disconnected,this action will fail...");
		.wait(3000);
		!tryInc.
		
+!tryInc
	<- inc.

-!tryInc
	<- println("inc fail").