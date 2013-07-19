!start.

+!start<-
	!do_link_local;
	!do_link_remote;
	?art_id(linker,L);
	dummyOp [artifact_id(L)].
	
+!do_link_local<-
	lookupArtifact("workspace",LocalWspID);
	+wsp_art_id(local,LocalWspID);
	makeArtifact("myLinkerArt", "test.LinkerArt", [], LinkerArtId);
	+art_id(linker,LinkerArtId);
	makeArtifact("linkableArt", "test.LinkableArt", [], LinkableArtID);
	.println("artifacts created");
	.println("A");
	linkArtifacts(LinkerArtId, "out", LinkableArtID)[artifact_id(LocalWspID)];
	.println("first link done").

+!do_link_remote<-
	joinRemoteWorkspace("default", "localhost:20100", RemoteWspID);
	?art_id(linker,LinkerArtId);
	?wsp_art_id(local,LocalWspID);
	.println("join remote done");
	lookupArtifact("remoteLinkableArt", RemoteLinkableArtID)[wsp_id(RemoteWspID)];
	.println("Remote artifact found ",RemoteLinkableArtID);
	.println("local wsp: ",LocalWspID);
	linkArtifacts(LinkerArtId, "out", RemoteLinkableArtID)[artifact_id(LocalWspID)];
	.println("second link done").