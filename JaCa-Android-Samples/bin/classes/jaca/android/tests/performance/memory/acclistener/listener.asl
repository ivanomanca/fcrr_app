!init.

+!init 
	<-	lookupArtifact("workspace",Id);
		makeArtifact("acc-art", "jaca.android.tools.AccelerometerSensorManager",[] , AccId);
		startMonitoring;
		focus(AccId);
		focus(Id);
		println("init done").

+accelerometer_x(X).
+accelerometer_y(Y).
+accelerometer_z(Z).

+mo_acclistener(Priv,Tot) : true 
	<-	println("[Current app PRIV mem:] ", Priv, " MB");
		println("[Current app TOT mem:] ", Tot, " MB").

@my_plan[atomic]
+mo_other_apps(ProcName, Priv, Tot) : true
	<- println("[", ProcName, " PRIV mem:] ", Priv, " ", Tot, " MB");
	   println("[", ProcName, " TOT mem:] ", Priv, " MB").

+artifact("memory-art", _, Id)
	<-	//println("memory atifact created"); 
		focus(Id).