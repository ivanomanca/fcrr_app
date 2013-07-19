!start.

+!start
	<-	!init;
		!do_job.
//##########################################################################
// INIT PLAN
//##########################################################################
+!init
	<-  println("[fcrr-carpooler-ag:] Running.");
		// aggiungo l'utente loggato nei belief
		+userID(2);
		
		// Console
		lookupArtifact("console", LocalConsoleID);
		+art_id(console, LocalConsoleID);
		
		// GPS
		//makeArtifact("gps-manager", "jaca.android.tools.GPSManager",[] , GPSID);
		//+art_id(gpsmanager, GPSID);
		//focus(GPSID);
		
		// GPS Simulator
		makeArtifact("gps-simulator", "jaca.android.fcrr.simulator.GpsSimulatorArtifact",[] , GPSsimID);
		+art_id(gpssimulator, GPSsimID);
		focus(GPSsimID);
		println("[fcrr-carpooler-ag:] *gps-simulator* focussed.")[artifact_id(LocalConsoleID)];
		
		
		// HTTP
		makeArtifact("http-manager", "jaca.android.fcrr.HttpManagerArtifact", [], HttpID);
		+art_id(httpmanager, HttpID);
		focus(HttpID);
		
		// FCRR SERVICE
		makeArtifact("fcrrservice", "jaca.android.fcrr.FCRRServiceArtifact", [], FSrvcID);
		+art_id(fcrrservice, FSrvcID);
		focus(FSrvcID);
		
		// LINKING fcrrservice -> httpmanager
		linkArtifacts(FSrvcID, "out-1", HttpID);
		println("[fcrr-carpooler-ag:] fcrrservice -> http-manager linked!");
		
		// MAIN GUI
		focusWhenAvailable("main-gui");
		println("[fcrr-carpooler-ag:] *main-gui* focussed.");
		lookupArtifact("main-gui", MainGUIID);
		updateView [artifact_id(MainGUIID)];
		println("[fcrr-carpooler-ag:] Init done!.").

//##########################################################################
// DOING MY JOB
//##########################################################################		
+!do_job
	<-	println("[fcrr-carpooler-ag:] Doing my job.").

//##########################################################################
// REACTIVE PLANS
//##########################################################################
+driver_button_click : 	art_id(console, LocalConsoleID) & 
						art_id(httpmanager, HttpID) &
						art_id(fcrrservice, FSrvcID)
	<-	println("[fcrr-carpooler-ag:] Driver button pressed (event perceived!).")[artifact_id(LocalConsoleID)];
		
		startExplicitActivity("jaca.android.fcrr.DTripListActivity");
		focusWhenAvailable("dtriplistgui");
		println("[fcrr-carpooler-ag:] *dtriplistgui* focussed.")[artifact_id(LocalConsoleID)];
		lookupArtifact("dtriplistgui", DtlgID);
		
		?userID(UserID);
		retrieveTripList(UserID, TripList) [artifact_id(FSrvcID)];
		updateView(TripList) [artifact_id(DtlgID)].

+rider_button_click : art_id(console,LocalConsoleID) & art_id(httpmanager, HttpID)
	<-	println("[fcrr-carpooler-ag:] Rider button pressed (event perceived!)")[artifact_id(LocalConsoleID)];
		startExplicitActivity("jaca.android.fcrr.RiderTripSearch");
		focusWhenAvailable("ridertripsearch-gui");
		println("[fcrr-carpooler-ag:] *ridertripsearch-gui* focussed.")[artifact_id(LocalConsoleID)].

+trip_info_gui(TripID) :	art_id(console,LocalConsoleID) & 
							art_id(httpmanager, HttpID)
	<-	println("[fcrr-carpooler-ag:] Trip list item selected (event perceived!)")[artifact_id(LocalConsoleID)];
		startExplicitActivity("jaca.android.fcrr.DTripInfoActivity");
		focusWhenAvailable("dtripinfogui");
		println("[fcrr-carpooler-ag:] *dtripinfogui* focussed.")[artifact_id(LocalConsoleID)];
		lookupArtifact("dtripinfogui", DtigID);
		
		retrieveReservationList(TripID, RidersList)[artifact_id(FSrvcID)];
		retrieveTrip(TripID, TripInfo)[artifact_id(FSrvcID)];
		updateView(TripID, TripInfo, RidersList) [artifact_id(DtigID)].
		
+start(TripID) :	art_id(console,LocalConsoleID) & 
					art_id(fcrrservice, FSrvcID) &
					art_id(gpssimulator, GpsSimID)
	<-	println("[fcrr-carpooler-ag:] Start trip button pressed (event perceived!)")[artifact_id(LocalConsoleID)];
		startExplicitActivity("jaca.android.fcrr.DTripMapActivity");
		focusWhenAvailable("dtripmapgui");
		+art_id(dtripmapgui, DtmgID);
		println("[fcrr-carpooler-ag:] *dtripmapgui* focussed.")[artifact_id(LocalConsoleID)];
		+tripstatus("incorso");
		!monitor(TripID).

+!monitor(TripID) : tripstatus("incorso") & 
			art_id(console, LocalConsoleID) & 
			art_id(fcrrservice, FSrvcID) &
			art_id(gpssimulator, GpsSimID) &
			art_id(dtripmapgui, DtmgID)
	<- 	println("[fcrr-carpooler-ag:] Monitoring trip...")[artifact_id(LocalConsoleID)];
		
		// leggo pos da gps
		?userID(UserID);
		println("[fcrr-carpooler-ag:] Reading lat and lng...")[artifact_id(LocalConsoleID)];
		?latitude(Lat)[artifact_id(GpsSimID)];
		println("[fcrr-carpooler-ag:] Latitude = ",Lat)[artifact_id(LocalConsoleID)];
		?longitude(Lng)[artifact_id(GpsSimID)];
		println("[fcrr-carpooler-ag:] Longitude = ",Lng)[artifact_id(LocalConsoleID)];
		
		// update posizione driver
		println("[fcrr-carpooler-ag:] Sending position...")[artifact_id(LocalConsoleID)];
		updateCarpoolerGPS(UserID, Lat, Lng)[artifact_id(FSrvcID)];
		println("[fcrr-carpooler-ag:] Map updating...")[artifact_id(LocalConsoleID)];
		
		// recupero delle posizioni correnti
		retrieveReservationList(TripID, RidersPos)[artifact_id(FSrvcID)];
		retrieveCarpooler(UserID, CarpoolerPos)[artifact_id(FSrvcID)];
		
		// aggiornamento mappa
		updateView(CarpoolerPos, RidersPos)[artifact_id(DtmgID)];
		.wait(4000);
		!monitor(TripID).
		
+stop(TripID) : art_id(console,LocalConsoleID)
	<-	println("[fcrr-carpooler-ag:] Stop trip button pressed (event perceived!)")[artifact_id(LocalConsoleID)];
		-tripstatus("incorso").
//###########################################################################
// GPS PARAMS
//###########################################################################	
/*
+altitude(Altitude) : art_id(console,LocalConsoleID)
	<- 	println("[fcrr-carpooler-ag:] Altitude: ", Altitude)[artifact_id(LocalConsoleID)].
	
+accurancy(Accuracy) : art_id(console,LocalConsoleID)
	<-	println("[fcrr-carpooler-ag:] Accurancy: ", Accuracy)[artifact_id(LocalConsoleID)].
	
+bearing(Bearing) : art_id(console,LocalConsoleID)
	<-	println("[fcrr-carpooler-ag:] Bearing: ", Bearing)[artifact_id(LocalConsoleID)].
	
+speed(Speed) : art_id(console,LocalConsoleID)
	<-	println("[fcrr-carpooler-ag:] Speed: ", Speed)[artifact_id(LocalConsoleID)].
*/
