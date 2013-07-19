!init.

+!init : true
	<- 	println("ballSceneAgent: init");
		?current_wsp(LocalWsp,_,_); +local_wsp(LocalWsp);
		makeArtifact("Config", "ball.scene.config.Config", [], Config);
		lookupArtifact("workspace", Wsp);
		focus(Wsp);
		makeArtifact("MySensorsArtifact", "jaca.android.tools.MySensorsArtifact", [], MySensorsArtifact);
		+sensor_focus(false);
		println("ballSceneAgent: init completed").

		
/************************************* events plans ****************************************************/		
+ok
	: true
	<-	!joinEventWsp.

+cancel
	: true
	<- true.

+togglePresenter(Val1)
	: presenter_control(Val2) & Val1 \== Val2
	<- !!togglePresenterControl.

+direction(Direction)
	: true
	<- !!changeDirection(Direction).
	
+sensor_commander(true)
	: sensor_focus(false)
	<- 	?local_wsp(LocalWsp);
		lookupArtifact("MySensorsArtifact", MySensorsArtifact)[wsp_id(LocalWsp)];
		focus(MySensorsArtifact)[wsp_id(LocalWsp)];
		-+sensor_focus(true).
	
+sensor_commander(false)
	: sensor_focus(true)
	<- 	?local_wsp(LocalWsp);
		lookupArtifact("MySensorsArtifact", MySensorsArtifact)[wsp_id(LocalWsp)];
		stopFocus(MySensorsArtifact)[wsp_id(LocalWsp)];
		-+sensor_focus(false).

+shake_event
	: true
	<- !!togglePresenterControl.


/************************************* observable property plans **************************************/
+artifact("BallSceneControllerActivity", _, BallSceneControllerActivity)
	: true
	<- 	focus(BallSceneControllerActivity);
		lookupArtifact("Config", Config);
		linkArtifacts(BallSceneControllerActivity, "out-1", Config);
		?sensor_focus(Selected);
		setSensor(Selected);
		!joinEventWsp.
		

-artifact("BallSceneControllerActivity", _, BallSceneControllerActivity) 
	: true
	<- 	?local_wsp(LocalWsp);
		lookupArtifact("MySensorsArtifact", MySensorsArtifact)[wsp_id(LocalWsp)];
		disposeArtifact(MySensorsArtifact)[wsp_id(LocalWsp)];
		lookupArtifact("BallSceneState", BallSceneState);
		stopFocus(BallSceneState);
		-events_wsp(EventsWsp);
		quitWorkspace;
		lookupArtifact("MySensorsArtifact", MySensorsArtifact);
		disposeArtifact(MySensorsArtifact).


+orientation(X) [artifact_name(_,"MySensorsArtifact")]
	: true
	<- 	!!changeDirection(X).
	
+presenter_control(Val)
	:true
	<- 	?local_wsp(LocalWsp);
		setTogglePresenter(Val)[wsp_id(LocalWsp)].


/************************************* goals plans ****************************************************/
+!joinEventWsp
	: true
	<- 	getAddress(Address);
		if ( Address == "") {
			startExplicitActivityForResult("ball.scene.config.AddressActivity", 0)[artifact_name("BallSceneControllerActivity")];
		} else {
			joinRemoteWorkspace("default", Address, "lipermi", EventsWsp); +events_wsp(EventsWsp);
			lookupArtifact("BallSceneState", BallSceneState);
			focus(BallSceneState);
		}.

-!joinEventWsp
	: true
	<-	startExplicitActivityForResult("ball.scene.config.AddressActivity", 0)[artifact_name("BallSceneControllerActivity")].


+!togglePresenterControl
	: events_wsp(EventsWsp)
	<- togglePresenterControl[wsp_id(EventsWSP)].

+!changeDirection(Direction)
	:  events_wsp(EventsWsp)
	<- setBallsDirection(Direction)[wsp_id(EventsWSP)].
	