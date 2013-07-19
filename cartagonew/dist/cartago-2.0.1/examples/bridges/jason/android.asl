!test_remote.

+!test_remote 
  <- ?current_wsp(Id,_,_);
     +default_wsp(Id);
     println("testing remote..");
     joinRemoteWorkspace("default","127.0.0.1","lipermi",WspID2);
     ?current_wsp(_,WName,_);
     println("hello there ",WName);
     !use_remote.
     
      
+!use_remote 
  <- lookupArtifact("smsreceiver",Id);
     focus(Id).

+sms_received(Source, Message)
	<-	println(Source, " has sent this message to you ", Message);
		quitWorkspace.
