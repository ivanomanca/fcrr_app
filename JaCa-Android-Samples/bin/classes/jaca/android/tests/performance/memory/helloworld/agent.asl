!init.

+!init 
	<-	lookupArtifact("workspace",Id);
		focus(Id);
		println("init done").

+mo_helloworld(Priv,Tot) : true 
	<-	println("[Current app PRIV mem:] ", Priv, " MB");
		println("[Current app TOT mem:] ", Tot, " MB").

@my_plan[atomic]
+mo_other_apps(ProcName, Priv, Tot) : true
	<- println("[", ProcName, " PRIV mem:] ", Priv, " ", Tot, " MB");
	   println("[", ProcName, " TOT mem:] ", Priv, " MB").
	
+artifact("memory-art", _, Id)
	<-	//println("memory atifact created"); 
		focus(Id).

+artifact("acc-art", _, Id)
	<-	//println("AccelerometerSensorManager created"); 
		focus(Id).