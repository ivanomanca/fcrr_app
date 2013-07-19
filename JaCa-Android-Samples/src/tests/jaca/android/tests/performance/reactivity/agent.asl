count(0).

!init.

+!init 
	<- 	makeArtifact("sensormanager", "jaca.android.tests.performance.reactivity.TestAccelerometerArtifact",[],Id);
		focus(Id);
		println("init done").

@myplan[atomic]
+accelerometer_update(Time) : count(N) 
	<- cartago.invoke_obj("java.lang.System", currentTimeMillis, NotificationTime);
	   println("Event [", N, "] notified with ", NotificationTime - Time, " ms delay");
	   if(N>=50){
	   		stopMonitoring;
	   		println("Test done.");
	   }
	   else{
	   	   -+count(N+1);
	   }.	   