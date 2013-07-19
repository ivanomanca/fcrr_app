/* Initial beliefs and rules */

page_artifact_name("MyPageArtifact").

/* Initial goals */
!init.

/* Pro-Active Plans */
+!init :
		page_artifact_name(PAName)
	<-	lookupArtifact(PAName,PAId);		
		focus(PAId);		
		?current_wsp(Id,_,_);
     	+default_wsp(Id);     	
     	!joinRemote.
     	
+!joinRemote
	<-	?remoteIp(Ip);		
		joinRemoteWorkspace("default",Ip,"lipermi",_);
		lookupArtifact("EventReg",EventRegId);
		focus(EventRegId).
		
-!joinRemote
	<- 	.println("xxx");
		.wait(1000);
		!!joinRemote.
		


/* Re-Active Plans */
+event(EventName)
	<-  ?default_wsp(WId);
		lookupArtifact(EventName,EventId);
		focus(EventId);
		?latitude(EventName,Latitude);
		?longitude(EventName,Longitude);
		?event_status(EventName,EventStatus);
		?current_participant(EventName,CurrentPartecipant);
		?description(EventName,Description);		
		addMarker(EventName,Latitude,Longitude,EventStatus,CurrentPartecipant,Description)[wsp_id(WId)];		
		changeStatusMessage("E' stato aggiunto un nuovo evento!")[wsp_id(WId)].


+event_status(EventName,EventStatus)
	<-	?default_wsp(WId);
		changeMarkerState(EventName,EventStatus)[wsp_id(WId)];
		.concat("L'evento <b>",EventName,M1);
		.concat(M1,"</b> ha ora un nuovo stato: ",M2);
		.concat(M2,EventStatus,M3);
		.concat(M3,".",M4);
		changeStatusMessage(M4)[wsp_id(WId)].

+description(EventName,Content) 
	<-	?default_wsp(WId);		
		changeMarkerContent(EventName,Content)[wsp_id(WId)];
		.concat("Nell'evento <b>",EventName,M1);
		.concat(M1,"</b> viene presentato un nuovo contenuto!",M2);		
		changeStatusMessage(M2)[wsp_id(WId)].

+current_participant(EventName,CurrentPartecipant) 
	<-	?default_wsp(WId);		
		changeCurrentPartecipants(EventName,CurrentPartecipant)[wsp_id(WId)];
		.concat("Un nuovo partecipante si è unito all'evento <b>",EventName,M1);
		.concat(M1,"</b>!",M2);		
		changeStatusMessage(M2)[wsp_id(WId)].

		