!init.

+!init 
	<- 	lookupArtifact("console",LocalConsoleID);
		focusWhenAvailable("remote-gui");
		lookupArtifact("remote-gui",GUIArtID);
		makeArtifact("config", "jaca.android.tests.remotewsp.common.Config", ["lipermiconfig"], ConfID);
		//Link the RemoteGui with the Config in order to be able to update the remote address inserted
		linkArtifacts(GUIArtID, "out-1", ConfID);
		+art_id(console, LocalConsoleID);
		!join_remote_wsp.
	
+!join_remote_wsp : art_id(console, LocalConsoleID)
	<- 	getAddress(Address);
		//Address not setted yet, we start the activity for obtaining the address
		if (Address == "") {
			startExplicitActivityForResult("jaca.android.tests.remotewsp.common.AddressActivity", 0);
		//Address setted, we try to join the remote wsp
		} else {
			joinRemoteWorkspace("default", Address, "lipermi", RemoteWspId); 
			+remote_wsp(RemoteWspId);
			println("Join remote done!")[artifact_id(LocalConsoleID)];
			!work_in_remote_wsp;
		}.

//Failure in joining the remote wsp: we ask again for the address
-!join_remote_wsp : true
	<-  startExplicitActivityForResult("jaca.android.tests.remotewsp.common.AddressActivity", 0).

//Signal received by the RemoteGUI: the user has inserted the remote address, we can re-instantiate the join_remote_wsp plan 
+address_setted  :  true
	<- !join_remote_wsp.
	
+!work_in_remote_wsp : art_id(console, LocalConsoleID)
	<-  lookupArtifact("myCalc", CalcId)[wsp_id(RemoteWspId)];
		lookupArtifact("console", RemoteConsoleID)[wsp_id(RemoteWspId)];
		println("Remote calculator artifact found")[artifact_id(LocalConsoleID)];
		println("Using the calculator, the next prints can be seen on the remote app as well")[artifact_id(LocalConsoleID)];
	   	sum(2,3,X);
	   	println("[remote-agent:]The sum between 2 and 3 is ", X)[artifact_id(RemoteConsoleID)];
	   	println("The sum between 2 and 3 is ", X)[artifact_id(LocalConsoleID)];
	   	sub(1,2,Y);
	   	println("[remote-agent:]The sub between 1 and 2 is ", Y)[artifact_id(RemoteConsoleID)];
	   	println("The sub between 1 and 2 is ", Y)[artifact_id(LocalConsoleID)];
	   	division(6,3,Z);
	   	println("[remote-agent:]The div between 6 and 3 is ", Z)[artifact_id(RemoteConsoleID)];
	   	println("The div between 6 and 3 is ", Z)[artifact_id(LocalConsoleID)].