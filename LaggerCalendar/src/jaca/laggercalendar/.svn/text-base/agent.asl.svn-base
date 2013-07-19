!start.

+!start
	<-	!init;
		!do_job.

+!init
	<-  println("[cal-ag:]Hello World!!.");
		focusWhenAvailable("events-mngr");
		makeArtifact("gps-manager", "jaca.android.tools.GPSManager",[] , GPSID);
		focus(GPSID);
		makeArtifact("sms-manager", "jaca.android.tools.SMSManager",[], SmsID);
		focus(SmsID);
		makeArtifact("call-manager", "jaca.android.tools.CallManager",[] , CallID);
		focus(CallID);
		makeArtifact("addressbook", "jaca.laggercalendar.artifacts.AddressBookService",[] , AddressBookID);
		focus(AddressBookID);
		makeArtifact("directions", "jaca.laggercalendar.artifacts.DirectionsService",[] , DirectionsID);
		focus(DirectionsID);
		makeArtifact("calendar", "jaca.laggercalendar.artifacts.CalendarService",[] , CalendarID);
		focus(CalendarID);
		println("[cal-ag:]Artifacts created and focussed").
	
+!do_job : true
	<-  startMonitoring;
		println("[cal-ag:]Successfully started monitoring GPS").
		
+add_new_event_menu_click
	<-  println("[cal-ag:]Menu click perceived by the agent");
		startExplicitActivity("jaca.laggercalendar.EventEditorActivity");
		focusWhenAvailable("event-editor-gui");
		println("[cal-ag:]focusWhenAvailable done");
		lookupArtifact("event-editor-gui", EditorID);
		println("[cal-ag:]Event editor artiact created and focussed").

+new_event_btn_click(EvDescr, StartDate, EndDate, Address, Responsible)
	<-	println("[cal-ag:]new_event_btn_click received");
		addEvent(EvDescr, StartDate, EndDate, Address, Responsible);
		startExplicitActivity("jaca.laggercalendar.EventListActivity").
	
+modify_event_btn_click(EvID, EvDescr, StartDate, EndDate, Address, Responsible)
	<-  println("[cal-ag:]modify_event_btn_click");
		modifyEvent(EvID, EvDescr, StartDate, EndDate, Address, Responsible);
		startExplicitActivity("jaca.laggercalendar.EventListActivity").
	
+check_schedule_tick(EventInfo)
	<-	?latitude(Lat);
		?longitude(Long);
		println("[cal-ag:]Latitude and longitude retrieved");
		calculateTravelTime(Lat, Long, EventInfo, EstTime);
		+estTime(EstTime);
		println("[cal-ag:]Estimated time calculated", EstTime);
		!notify_event_approaching.
				
+!notify_event_approaching
	<-  !check_on_time(EventInfo, EstTime);
	    showEventReminder(EventInfo);
	    postponeEventNotification(EventInfo).

-!notify_event_approaching : estTime(EstTime)
	<-  println("[cal-ag:]FAILURE HANDLED", EstTime);
		showDelayManagementWindow(EstTime);
		println("[cal-ag:]Delay selection window shown").
	
+!check_on_time(EventInfo, EstTime, Res)
	<-  .fail.
			
+approved_delay(Delay)
	<-	!postpone_apps(Delay);
		.concat("I'm sorry, I'm ",Delay," minutes late for our appointment.", Msg);
		!notify_delays(Msg).	
		
+!postpone_apps(Delay)
	<-	.concat("[Agent-TODO] appointments delayed by ", Delay, " minutes, if needed.", Msg);
		println(Msg).
	
+!notify_delays(Message)
	<-	println("[Agent-TODO] notify delay for next appointments, if needed.").
		
+!send_notification(Contact, Message) : wifi_status(on)
	<- 	sendMail(Contact, Message).
	
+!send_notification(Contact, Message) : wifi_status(off)
	<- 	sendSMS(Contact, Message).	
	
+latitude(Lat) : longitude(Long) & time(Time) & busy(Lat,Long,Time)
	<-	+busy;
		setRingtone_volume(0);
		setVibration(on).
		
+longitude(Long) : latitude(Lat) & time(Time) & busy(Lat,Long,Time)
	<-	+busy;
		setRingtone_volume(0);
		setVibration(on).
		
+latitude(Lat) : longitude(Long) & time(Time) & not_busy(Lat,Long,Time)
	<-	-busy;
		setRingtone_volume(100);
		setVibration(on).
		
+longitude(Long) : latitude(Lat) & time(Time) & not_busy(Lat,Long,Time)
	<-	-busy;
		setRingtone_volume(100);
		setVibration(off).		
		
+incoming_call(Contact) : busy
	<-	dropCall;
		!send_notification(Contact, "Sorry, I'm currently busy.").
		
+new_sms(Contact) : busy
	<-	sendSMS(Contact, "Sorry, I'm currently busy.").