!init.

+!init : true
  <- ?current_wsp(LocalWsp,_,_); +local_wsp(LocalWsp); 
     makeArtifact("BallSceneState", "events.BallSceneState", [], IdBallSceneState);
  	 focus(IdBallSceneState);
  	 makeArtifact("EventReg","events.EventsReg",[],IdEventReg);
  	 makeArtifact("GUI","events.GUIAddParticipant", [], GUI);
  	 focus(IdEventReg);
  	 focus(GUI);
  	 addEvent("Research night Cesena", 44.141737, 12.239396, "<img src='http://dl.dropbox.com/u/1366161/research_night_cesena.png' style='max-width:600; max-height:340' />", 5);
  	 .wait(5000);
     addEvent("Research night Paris", 48.856695, 2.350988, "Research night Paris", 10).

     
     //addEvent("Research night Cesena", 48.856695, 2.350988, "Research night Cesena", 5).
     
+event(EventName) : true
  <- ?local_wsp(LocalWsp);
     println("New event created from the EventRegistry: ", EventName)[wsp_id(LocalWsp)]; 
     lookupArtifact(EventName, EventArtId)[wsp_id(LocalWsp)];
     focus(EventArtId)[wsp_id(LocalWsp)].
     //!addParticipant(EventName).
     //addParticipant("gino")[artifact_id(EventArtId)];
     //addParticipant("pino")[artifact_id(EventArtId)];
     //addParticipant("paperino")[artifact_id(EventArtId)];
     //println("3 participants added for:", EventName).

/*+!addParticipant(EventName)
	: event_status(EventName,"status_waiting")
	<- 	addParticipant("paperino")[artifact_name(EventName)];
		.wait(5000);
		!!addParticipant(EventName).

+!addParticipant(EventName)
	: not event_status(EventName,"status_waiting")
	<- true. */

+event_status(Event,"status_ready") : true
  <- ?local_wsp(LocalWsp);
     println("Event ", Event, " is ready!!!")[wsp_id(LocalWsp)].
  	 //lookupArtifact(Event,ArtID);
     //startEvent[artifact_name(Event)].
     
+event_status(Event,"status_running") : true
  <- ?local_wsp(LocalWsp);
     println("Event ", Event, " is running!!!")[wsp_id(LocalWsp)];
     joinRemoteWorkspace("default", "137.204.70.65:20102", "lipermi", RemoteWsp); +device_wsp(RemoteWsp);
     lookupArtifact("Device", Device);
     focus(Device).
     //endEvent[artifact_name(Event)];
     //restartEvent(3)[artifact_name(Event)].
     
+event_status(Event,"status_ended") : true
  <- ?local_wsp(LocalWsp);
     println("Event ", Event, " now ended")[wsp_id(LocalWsp)].
  	 /*restartEvent(10)[artifact_name(Event)];
  	 .wait(10000);
  	 !addParticipant(Event).*/


+current_participant(EventName, Value)
	: true
	<- ?local_wsp(LocalWsp);
	    println(EventName, " partecipants: ", Value)[wsp_id(LocalWsp)].
  
+ball_direction(Direction) : true
  <- ?local_wsp(LocalWsp);
     println("Direction ", Direction)[wsp_id(LocalWsp)].
 
 
//GUI EVENTS------------
   
+gui_addParticipant(EventName, ParticipantDescr) : event_status(EventName,"status_waiting") 
	<- ?local_wsp(LocalWsp);	
	   addParticipant("paperino")[artifact_name(EventName),wsp_id(LocalWsp)].

+gui_addEvent(EventName, Longitude, Latitude, EventDescription, MinParticipants) : true 
	<- ?local_wsp(LocalWsp); 
	   println("signal addEvent received")[wsp_id(LocalWsp)]; 	
	   addEvent(EventName, Longitude, Latitude, EventDescription, MinParticipants)[artifact_name("EventReg"),wsp_id(LocalWsp)].

+presenter_control(State) : true
  <- ?local_wsp(LocalWsp); 
     println("Presenter control ", State)[wsp_id(LocalWsp)].
     

+imageUrl(URL)
 : device_wsp(RemoteWsp) & event_status("Research night Cesena", "status_running")
 <-  ?local_wsp(LocalWsp);
  .concat("<img src='", URL, OutURL1);
  .concat(OutURL1, "' style='max-width:600; max-height:340' />", OutURL2);
  println(OutURL2)[wsp_id(LocalWsp)];
  updateDescription(OutURL2)[wsp_id(LocalWsp)].