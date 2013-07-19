/* Initial hard-coded belief in order to associate tagID to people*/
tagToPerson("ASDFGGHDERE1","Ezra Pound").
tagToPerson("ASDFGGHDERE2","Mario Rossi").
tagToPerson("ASDFGGHDERE3","Naomi Klein").
tagToPerson("ASDFGGHDERE4","Michael Moore").


/* Initial goals */

!start. // normal goal when the env is completely setup
//!joinRemote. // plan to be used when no SS is available

+!joinRemote: true <-
	println("before join");
	?current_wsp(WsId,_,_);
	joinRemoteWorkspace("default","localhost",WspId2);
	println("workspace joined") [wsp_id(WsId)];
	lookupArtifact("researchNightCesena",Id);
	println("Artifact found ",Id)[wsp_id(WsId)];
	addParticipant("Ezra Pound") [artifact_id(Id)];
	println("participant added")[wsp_id(WsId)].
	

/* Plans */

+!start : true <- 
	!setupTool(Id);
	?local_wsp(WsId);
	// verbose(true) [wsp_id(WsId)];
	focus(Id) [wsp_id(WsId)];
	println("[SUBSCRIBER]: focus succeded")[wsp_id(WsId)];
	
	join(Ack) [wsp_id(WsId)];
	println("[SUBSCRIBER]: Join confirmed ", Ack) [wsp_id(WsId)];
	subscribeRDF(null,"HasRead",null,"URI",SubscriptionID) [wsp_id(WsId)]; // depends on the template
	println("[SUBSCRIBER]: Subcription succeeded, ID: ",SubscriptionID) [wsp_id(WsId)];
	+subscriptionID(SubscriptionID).

+!setupTool(Id): true <- 
	// creates the artifact needed to deal with the smart-space
	makeArtifact("smart-m3","welcome.SmartM3",["137.204.70.63","10010","rfid-space"],Id); // IP of smart-M3
	makeArtifact("display","welcome.Display",[],_);
	// save the local workspace
	?current_wsp(WsId,_,_);
	+local_wsp(WsId);
	// join the remote workspace to interact with Event artifacts
	//joinRemoteWorkspace("default","localhost",WspId2); // IP of remote workspace
	joinRemoteWorkspace("default","localhost",WspId2);
	println("[SUBSCRIBER]: remote workspace joined") [wsp_id(WsId)];
	+remote_wsp(WspId2).
	
-!setupTool(Id): true <-
	?local_wsp(WsId);
	println("[SUBSCRIBER]: unable to connect to remote workspace")[wsp_id(WsId)].

// event generated whenever a subscribed tuple gets inserted into the space
+smartM3_event(SID,"update",OT,NT): subscriptionID(SID) <-
	?local_wsp(WsId);
	println("new update perceived")[wsp_id(WsId)];
	cartago.invoke_obj(OT,get(2),OTagID);
	cartago.invoke_obj(NT,get(2),NTagID);
	!tag_read(OTagID,NTagID).

+!tag_read(TagID,"none"): not tag_in(TagID) <-
	+tag_in(TagID);
	?local_wsp(WsId);
	println("--------------------------> new participant entered the room ")[wsp_id(WsId)];
	//.concat(" Benvenuto ",Person,C);
	setValue(" Benvenuto") [wsp_id(WsId)];
	?remote_wsp(RemWsp);
	lookupArtifact("Research night Cesena",Id) [wsp_id(RemWsp)];
	println("[SUBSCRIBER]: Artifact found ",Id)[wsp_id(WsId)];
	addParticipant(TagID) [artifact_id(Id)];
	println("[SUBSCRIBER]: participant added")[wsp_id(WsId)].
	
+!tag_read(TagID,"none"): tag_in(TagID) <-
	-tag_in(TagID);
	?local_wsp(WsId);
	println("--------------------------> participant left the room ")[wsp_id(WsId)];
	//.concat(" Arrivederci ",Person,C);
	setValue(" Arrivederci") [wsp_id(WsId)];
	?remote_wsp(RemWsp);
	lookupArtifact("Research night Cesena",Id) [wsp_id(RemWsp)];
	println("[SUBSCRIBER]: Artifact found ",Id)[wsp_id(WsId)];
	removeParticipant(TagID) [artifact_id(Id)];
	println("[SUBSCRIBER]: participant removed")[wsp_id(WsId)].

+!tag_read("none",TagID) <- true.
