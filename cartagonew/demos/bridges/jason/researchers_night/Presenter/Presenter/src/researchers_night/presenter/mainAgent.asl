!init.

+!init : true
	<- 	?current_wsp(LocalWsp,_,_); +local_wsp(LocalWsp);
		makeArtifact("Config", "researchers_night.presenter.config.Config", [], Config);
		makeArtifact("NetworkSettings", "jaca.android.tools.NetworkSettings", [], NetworkSettings);
		focus(NetworkSettings);
		focusWhenAvailable("EventsListActivity");
		lookupArtifact("EventsListActivity", EventsListActivity);
		linkArtifacts(EventsListActivity, "out-1", Config);
		!joinEventWsp.
		

/************************************* events plans ****************************************************/
+ok
	: true
	<-	!joinEventWsp.

+cancel
	: true
	<- true.


+item_selected(Intent, EventBoardName) [artifact_name(EventsListActivity, "EventsListActivity")] 
	: true
	<- 	?events_wsp(EventsWsp);
		lookupArtifact(EventBoardName, EventBoardId)[wsp_id(EventsWsp)];
		+event_board_id(EventBoardId);
		focus(EventBoardId)[wsp_id(EventsWsp)];
		?local_wsp(LocalWsp);
		startActivity(Intent) [artifact_id(EventsListActivity)];
		focusWhenAvailable("EventActivity")[wsp_id(LocalWsp)].
		
		
+item_selected(Intent) [artifact_name(PresentationActivity, "PresentationActivity")]
	: true
	<- 	startActivity(Intent) [artifact_id(PresentationActivity)].


+start[artifact_name(EventActivity,"EventActivity")] 
	: event_status(_, "status_ready")
	<- 	startExplicitActivity("researchers_night.presenter.PresentationActivity")[artifact_id(EventActivity)];
		?local_wsp(LocalWsp);
		focusWhenAvailable("PresentationActivity")[wsp_id(LocalWsp)];
		!startEvent.

+start[artifact_name(EventActivity,"EventActivity")] 
	: event_status(_, "status_waiting")
	<- 	askConfirm[artifact_id(EventActivity)].
	
+forceStart[artifact_name(EventActivity,"EventActivity")] 
	: true
	<-	startExplicitActivity("researchers_night.presenter.PresentationActivity")[artifact_id(EventActivity)];
		?local_wsp(LocalWsp);
		focusWhenAvailable("PresentationActivity")[wsp_id(LocalWsp)];
		!startEvent.

+on_destroy[artifact_name(PresentationActivity, "PresentationActivity")] 
	: true
	<- 	!endEvent.

+on_destroy[artifact_name(EventActivity,"EventActivity")] 
	: true
	<- 	?event_board_id(EventBoardId);
		stopFocus(EventBoardId);
		-event_board_id(EventBoardId).
		
+on_destroy[artifact_name(EventsListActivity,"EventsListActivity")] 
	: events_wsp(EventsWsp)
	<- 	lookupArtifact("EventReg", EventReg);
		stopFocus(EventReg);
		quitWorkspace.

/************************************* goals plans ****************************************************/
+!joinEventWsp
	: true
	<- 	getAddress(Address);
		if ( Address == "") {
			startExplicitActivityForResult("researchers_night.presenter.config.AddressActivity", 0);
		} else {
			joinRemoteWorkspace("default", Address, "lipermi", EventsWsp); +events_wsp(EventsWsp);
			lookupArtifact("EventReg", EventReg);
			focus(EventReg);
			?local_wsp(LocalWsp);
			println("mainAgent: init completed")[wsp_id(LocalWsp)];
		}.

-!joinEventWsp
	: true
	<-	startExplicitActivityForResult("researchers_night.presenter.config.AddressActivity", 0).
		


+!startEvent 
	: not event_status(_, "status_running")
	<-	!unreachable;
		?event_board_id(EventBoardId);
		startEvent[artifact_id(EventBoardId)];
		updateDescription("<img width='600' height='340' src='http://dl.dropbox.com/u/1366161/research_night_cesena.png'>")[artifact_id(EventBoardId)].
		
+!endEvent
	: true
	<- 	!reachable;
		?event_board_id(EventBoardId);
		endEvent[artifact_id(EventBoardId)].

+!unreachable
	: true
	<- 	?local_wsp(LocalWsp);
		setAirplaneMode("cell,bluetooth")[wsp_id(LocalWsp)];
		airplaneModeOn[wsp_id(LocalWsp)].
	
+!reachable
	: true
	<- 	?local_wsp(LocalWsp);
		airplaneModeOff[wsp_id(LocalWsp)].

/************************************* observable property plans **************************************/
+event(EventName) [artifact_name(_,"EventReg")] 
	: true
	<- 	?local_wsp(LocalWsp);
		addEvent(EventName)[wsp_id(LocalWsp)].

+current_participant(_, Value) 
	: state(ActivityState) [artifact_name(_, "EventActivity")] & ActivityState \=="stopped"
	<-  ?local_wsp(LocalWsp);
		setParticipants(Value) [wsp_id(LocalWsp)].
	
+event_status(_, Status) 
	: state(ActivityState) [artifact_name(_, "EventActivity")] & ActivityState \=="stopped"
	<- 	?local_wsp(LocalWsp);
		setStatus(Status)[wsp_id(LocalWsp)].
		
+state(ActivityState) [artifact_name(_, "EventActivity")]
	: ActivityState == "running" | ActivityState=="paused"
	<- 	?local_wsp(LocalWsp);
		?current_participant(_, Partecipants);
		setParticipants(Partecipants) [wsp_id(LocalWsp)];
		?event_status(_, Status);
		setStatus(Status)[wsp_id(LocalWsp)].
	