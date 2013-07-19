!init.

+!init 
	<- 	makeArtifact("notification","jaca.android.tools.NotificationManager");
		showNotification("jaca.android.samples.main:drawable/notification", "Notification title", "Notification test description", "jaca.android.tests.notification.Viewer", false, Id).