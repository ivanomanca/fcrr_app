sms_received(0).
my_source("4455").
source_relevant(Source) :- my_source(S) & S == Source.

!manage_sms.

+!manage_sms 
  <-  .println("[AG:]inside main plan");
      makeArtifact("smsreceiver","jaca.android.tools.SMSManager", [true], IdReceiver);
      .println("[AG:]sms receiver done");
	  focus(IdReceiver);
	  .println("[AG:]sms receiver focused");
	  makeArtifact("notificator", "jaca.android.tools.NotificationManager", [], IdNotificator);
	  lookupArtifact("viewer",IdViewer);
	  focus(IdViewer);
	  .println("init done").

+sms_received(Source, Message) : sms_received(NumSms) & source_relevant(Source)
  <-  .println("[AG:] new sms received");
  	  -+sms_received(NumSms+1);
  	  !notify_sms(Source, Message).
	
+!notify_sms(Source, Message) : viewer_state("not_displayed") 
  <-  .println("[AG:] notification in status bar");
  	  showNotification("jaca.android:drawable/notification", Source, Message, "jaca.android.examples.sms.contextsensitive.SmsViewer", false, Id);
      addSMSToList(Source, Message). 	

+!notify_sms(Source, Message) : viewer_state("displayed")
  <-  addSMSToList(Source, Message).