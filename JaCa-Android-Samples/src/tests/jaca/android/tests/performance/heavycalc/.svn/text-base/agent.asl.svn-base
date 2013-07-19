count(0).

!init.

+!init 
	<- 	lookupArtifact("workspace",Id);
		focus(Id);
		println("init done").

+doSum_click : true 
	<- cartago.invoke_obj("java.lang.System", currentTimeMillis, StartTime);
	   computeSum;
	   cartago.invoke_obj("java.lang.System", currentTimeMillis, EndTime);
	   println("[JaCa sum:] ", EndTime-StartTime, " millis elapsed").
	   
	   
+artifact("heavycalc-art", _, Id)
	<-  //println("heavycalc-art created"); 
		focus(Id).