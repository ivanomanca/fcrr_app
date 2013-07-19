/*------------------------------ init ------------------------------*/
!init.

+!init : true
	<-  println("[MiddlewareManager]Starting init.");
		println("[MiddlewareManager]Before creating MiddlewareServices wsp");
		createWorkspace("MiddlewareServices");
		println("[MiddlewareManager]MiddlewareServices created");
		joinWorkspace("MiddlewareServices", WspID);
		println("[MiddlewareManager]MiddlewareServices joined");
		+attempts(0);
		/* We focus the workspace artifact for detect, in the case of a 
		background launch when the GUI of the middleware is displayed*/  
		lookupArtifact("workspace", WspArtID)[wsp_id(WspID)];
		focus(WspArtID);
		println("[MiddlewareManager]WSP CREATED AND FOCUSE!");
		makeArtifact("middleware-pref-manager", "jaca.android.middlewaremanager.MiddlewarePreferenceManagerArtifact", [], PrefArtID);
		println("[MiddlewareManager]middleware preference manager created");
		focus(PrefArtID);
		+id_art(pref,PrefArtID);
		getLaunchTypeBackgroundPreference(Value);
		if(Value==false){
			println("[MiddlewareManager]Launch with GUI");
			+gui_present(true);
			!lookup_and_link_gui;			
		}
		else{
			println("[MiddlewareManager]Background launch");
			+gui_present(false);
		};
		//!check_gui_presence;
		!manage_middleware_instance.

+!check_gui_presence : attempts(N) & N < 5 & id_art(pref,PrefArt) 
	<-	println("[MiddlewareManager]CHEKING GUI");
		-+attempts(N+1);
		lookupArtifact("middleware-manager-gui",GUIID);
		+id_art(gui,GUIID);
		focus(GUIID);
		+gui_present(true);
		linkArtifacts(GUIID,"out-port",PrefArt);
		println("[MiddlewareManager]GUI present and linking done");
		setLaunchTypeBackgroundPreference(false).

+!check_gui_presence : attempts(N) & N >= 5
	<-	+gui_present(false);
		println("[MiddlewareManager]GUI not present");
		setLaunchTypeBackgroundPreference(true).
	
-!check_gui_presence
	<-	.wait(50);
		?attempts(N);
		println("[MiddlewareManager]LOOKUP FAILURE PRESA", N);
		!check_gui_presence.

/*------------------------------ middleware management ------------------------------*/

+!manage_middleware_instance : pref_auto_start(true) | gui_present(false)
	<-  println("[MiddlewareManager]Auto-start preference setted or background launch, initialising JaCa-Services workspace");
		showMiddlewareIconInStatusBar;
	  	!start_middleware.

+!manage_middleware_instance
	<- println("[MiddlewareManager]No auto-start, we simply show the middleware icon");
	   showMiddlewareIconInStatusBar.

/*------------------------------ middleware start/stop management ------------------------------*/

/* Button event */
+start_middleware
	<-	!start_middleware.

/* start_middleware pro-active goals with gui present*/	
+!start_middleware : gui_present(true)
	<-	println("[MiddlewareManager]Start middleware request with GUI");
		disableButton("start");
		disableButton("stop");
		setPreferenceEditable(false);
		updateStatusIcon("jaca.android.middlewaremanager:drawable/ic_working");
		updateStatusTxt("jaca.android.middlewaremanager:string/lbl_status_creating");
		!create_jaca_service_artifacts;
		enableButton("stop");
		updateStatusTxt("jaca.android.middlewaremanager:string/lbl_status_created");
		updateStatusIcon("jaca.android.middlewaremanager:drawable/ic_done").
				
/* start_middleware pro-active goals with gui not present*/	
+!start_middleware : gui_present(false)
	<-	println("[MiddlewareManager]Start middleware request without GUI");
		!create_jaca_service_artifacts.

-!start_middleware : gui_present(true)
	<-	println("[MiddlewareManager]Error in create_jaca_service_artifacts, stopping middleware with GUI");
		notifyStartupError;
		updateStatusIcon("jaca.android.middlewaremanager:drawable/ic_error");
		updateStatusTxt("jaca.android.middlewaremanager:string/lbl_status_error");
		!destroy_jaca_service_artifacts;
		enableButton("start");
		disableButton("stop");
		setPreferenceEditable(true).

-!start_middleware : gui_present(false)
	<-	println("[MiddlewareManager]Error in create_jaca_service_artifacts, stopping middleware without GUI");
		!destroy_jaca_service_artifacts.

/* stop_middleware button event */
+stop_middleware
	<-	println("[MiddlewareManager]Stop middleware request");
		updateStatusIcon("jaca.android.middlewaremanager:drawable/ic_working");
		updateStatusTxt("jaca.android.middlewaremanager:string/lbl_status_disposing");
		disableButton("stop");
		disableButton("start");
		!destroy_jaca_service_artifacts;
		enableButton("start");
		setPreferenceEditable(true);
		updateStatusTxt("jaca.android.middlewaremanager:string/lbl_status_waiting");
		updateStatusIcon("jaca.android.middlewaremanager:drawable/ic_idle").


/*------------------------------ artifact creation/disposal ------------------------------*/

+!create_jaca_service_artifacts : gui_present(GUIPresent)
	<-  println("[MiddlewareManager]Creating JaCa-Services artifacts");

		/*--- GPSManager --- */	
		?pref_gps_manager(ValueGPS);
		if(ValueGPS==true){
			println("[MiddlewareManager]Creating GPSManager");
			makeArtifact("gps-manager", "jaca.android.tools.GPSManager",[] , GPSID);
			+art_id(gps,GPSID);
			if(GUIPresent==true){incrementProgressBar;};
			println("GPSManager created");
		}else{
			println("[MiddlewareManager]GPSManager not selected");
		};

		/*--- AccelerometerManager --- */
		?pref_acc_manager(ValueAcc);
		if(ValueAcc==true){
			println("[MiddlewareManager]Creating AccelerometerSensorManager");
			makeArtifact("acc-sensor-manager", "jaca.android.tools.AccelerometerSensorManager",[] , AccID);
			+art_id(acc,AccID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]AccelerometerSensorManager created");			
		}else{
			println("[MiddlewareManager]AccelerometerSensorManager not selected");
		};
		
		/*--- GeomagneticSensorManager --- */
		?pref_geomag_manager(ValueGEO);
		if(ValueGEO==true){
			println("[MiddlewareManager]Creating GeomagneticSensorManager");
			makeArtifact("geomag-sensor-manager", "jaca.android.tools.GeomagneticSensorManager",[] , GeoID);
			+art_id(geo,GeoID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]GeomagneticSensorManager created");
		}
		else{
			println("[MiddlewareManager]GeomagneticSensorManager not selected");
		};
		
		/*--- OrientationSensorManager --- */
		?pref_orientation_manager(ValueOR);
		if(ValueOR==true){
			println("[MiddlewareManager]Creating OrientationSensorManager");
			makeArtifact("orientation-sensor-manager", "jaca.android.tools.OrientationSensorManager",[] , OrientationID);
			+art_id(orientation,OrientationID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]OrientationSensorManager created");
		}
		else{
			println("[MiddlewareManager]OrientationSensorManager not selected");
		};

		/*--- AllSensorsManager --- */
		?pref_all_sensors_manager(ValueAll);
		if(ValueAll==true){
			println("[MiddlewareManager]Creating AllSensorsManager");
			makeArtifact("all-sensors-manager", "jaca.android.tools.AllSensorsManager",[] , AllSensorsID);
			+art_id(all_sensors,AllSensorsID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]AllSensorsManager created");			
		}
		else{
			println("[MiddlewareManager]AllSensorsManager not selected");
		};

		/*--- CallManager --- */
		?pref_call_manager(ValueCall);
		if(ValueCall==true){
			println("[MiddlewareManager]Creating CallManager");
			makeArtifact("call-manager", "jaca.android.tools.CallManager",[] , CallID);
			+art_id(call,CallID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]CallManager created");
		}
		else{
			println("[MiddlewareManager]CallManager not selected");
		};

		/*--- ConnectivityManager --- */
		?pref_connectivity_manager(ValueConn);
		if(ValueConn==true){
			println("[MiddlewareManager]Creating ConnectivityManager");
			makeArtifact("connectivity-manager", "jaca.android.tools.ConnectivityManager",[] , ConnID);
			+art_id(conn,ConnID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]ConnectivityManager created");
		}
		else{
			println("[MiddlewareManager]ConnectivityManager not selected");
		};
		
		/*--- SMSManager --- */
		?pref_sms_manager(ValueSMS);
		if(ValueSMS==true){
			println("[MiddlewareManager]Creating SMSManager");
			makeArtifact("sms-manager", "jaca.android.tools.SMSManager",[], SmsID);
			+art_id(sms,SmsID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]SMSManager created");
		}
		else{
			println("[MiddlewareManager]SMSManager not selected");
		};

		/*--- PhoneSettingsManager --- */
		?pref_phone_settings_manager(ValuePhone);
		if(ValuePhone==true){
			println("[MiddlewareManager]Creating PhoneSettingsManager");
			makeArtifact("phone-settings-manager", "jaca.android.tools.PhoneSettingsManager",[] , PsID);
			+art_id(phone,PsID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]PhoneSettingsManager created");
		}
		else{
			println("[MiddlewareManager]PhoneSettingsManager not selected");
		};
		
		/*--- BatteryArtifact --- */
		?pref_battery_manager(ValueBatt);
		if(ValueBatt==true){
			println("[MiddlewareManager]Creating BatteryArtifact");
			makeArtifact("battery-artifact", "jaca.android.tools.BatteryArtifact",[] , BattID);
			println("[MiddlewareManager]BatteryArtifact created");
			+art_id(battery,BattID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]BatteryArtifact created");
		}
		else{
			println("[MiddlewareManager]BatteryArtifact not selected");
		};
		
		/*--- CalendarManager --- */
		?pref_calendar_manager(ValueCal);
		if(ValueCal==true){
			println("[MiddlewareManager]Creating CalendarManager");
			makeArtifact("calendar-manager", "jaca.android.tools.CalendarManager",[] , CalID);
			+art_id(cal,CalID);
			if(GUIPresent==true){incrementProgressBar;};
			println("[MiddlewareManager]CalendarManager created");
		}
		else{
			println("[MiddlewareManager]CalendarManager not selected");
		};
		
		/*--- ContactsManager --- */
		?pref_contacts_manager(ValueConctact);
		if(ValueConctact==true){
			println("[MiddlewareManager]Creating ContactsManager");
			makeArtifact("contacts-manager", "jaca.android.tools.ContactsManager",[] , ContactID);
			if(GUIPresent==true){incrementProgressBar;};
			+art_id(contacts,ContactID);
			println("[MiddlewareManager]ContactsManager created");
		}
		else{
			println("[MiddlewareManager]ContactsManager not selected");
		};		
		println("[MiddlewareManager]JaCa-Services artifact initialisation completed").


	
+!destroy_jaca_service_artifacts : gui_present(GUIPresent)
	<-	println("[MiddlewareManager]Disposing jaca_service_artifacts");
		.findall(art_enty(Name,ArtID),art_id(Name,ArtID),L);
		.length(L,LS);
		println("[MiddlewareManager]Artefatti da rimuovere ", LS);
		/*for(.member(Member,L)){
			println("[MiddlewareManager]Artefatto: ", Member);
		};*/
		
		for(.member(art_enty(NameM,ArtIDM),L)){
			disposeArtifact(ArtIDM);
			-art_id(NameM,ArtIDM);
			println("[MiddlewareManager]Disposing artifact: ", NameM);
			if(GUIPresent==true){decrementProgressBar;};
		};
		println("[MiddlewareManager]JaCa-Services artifact disposal completed").

+artifact("middleware-manager-gui", _, Id) : id_art(pref,PrefArt)
	<-	println("[MiddlewareManager]GUI Artifact created");
		!lookup_and_link_gui.
	
+!lookup_and_link_gui : id_art(pref, PrefArtID)
	<-	lookupArtifact("middleware-manager-gui", GUIID);
		focus(GUIID);
		println("[MiddlewareManager]GUI Artifact found");
		linkArtifacts(GUIID,"out-port",PrefArtID);
		println("[MiddlewareManager]GUI Artifact now focussed and linked");
		-+gui_present(true).
		
-!lookup_and_link_gui
	<-	.wait(100);
		println("[MiddlewareManager]GUI Artifact not found, retrying.");
		!lookup_and_link_gui.
-!init
<- println("[MiddlewareManager]Init failed.").