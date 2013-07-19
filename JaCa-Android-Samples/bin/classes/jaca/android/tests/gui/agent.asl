!init.

+!init 
	<- 	lookupArtifact("workspace", Id);
		focus(Id);
		println("init done").

/**
	from GUIArtifact
*/
+state(State) [artifact_name(_,X)]
	<- println("state  ", X, " :", State).

/**
	from specialization of GUIArtifact
*/
+onReStart [artifact_name(_,X)]
	<- println("onReStart ", X).

+onStart [artifact_name(_,X)]
	<- println("onStart ", X).

+onResume [artifact_name(_,X)]
	<- println("onResume ", X).
	
+onPause [artifact_name(_,X)]
	<- println("onPause ", X).

+onStop [artifact_name(_,X)]
	<- println("onStop ", X).
	
+onDestroy [artifact_name(_,X)]
	<- println("onDestroy ", X).
	

+artifact("first_activity", _, Id)
	<-
	println("first activity created"); 
	focus(Id).
	
+artifact("second_activity", _, Id)
	<-
	println("second activity created"); 
	focus(Id).

+artifact("third_activity", _, Id)
	<-
	println("third activity created"); 
	focus(Id).
	
+first_activity
	<- print [artifact_name("first_activity")].
	
+second_activity
	<- print [artifact_name("second_activity")].
	
+third_activity
	<- print [artifact_name("third_activity")].