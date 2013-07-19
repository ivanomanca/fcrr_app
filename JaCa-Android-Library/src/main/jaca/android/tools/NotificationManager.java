package jaca.android.tools;

import jaca.android.dev.JaCaArtifact;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import cartago.CartagoException;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

/**
 * Artifac usable to notify the user of events that happen. 
 * This is how you tell the user that something has happened in the background. 
 * 
 * @author mguidi
 *
 */
public class NotificationManager extends JaCaArtifact {
	
	private android.app.NotificationManager mNotificationManager;
	private int mID;
	
	public static String ARTIFACT_DEF_NAME =  "notification-manager";
	
	void init() {
		mID = 0;
		mNotificationManager = (android.app.NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	/**
	 * Artifact operation that shows a notification on the status bar
	 * 
	 * @param icon fully qualified resource name of the form "package:type/entry" (ex. "jaca.android.example:drawable/icon")
	 * @param contentTitle notification title
	 * @param contentText notification content
	 * @param activity class name of the activity to launch when the user clicks the expanded notification
	 * @param persistent a boolean indicating if the notifciation has to be persistent or not   
	 * @param id an identifier for this notification unique within your application.
	 * @throws CartagoException activity class not found exception
	 */
	@OPERATION public void showNotification(String icon, String contentTitle, String contentText, String activity, boolean persistent, OpFeedbackParam<Integer> id) throws CartagoException {
		int iconId= getApplicationContext().getResources().getIdentifier(icon, null, null);
		_showNotification(iconId, contentTitle, contentText, activity, persistent, id);
	}
	
	/**
	 * Artifact operation that shows notification on the status bar
	 * 
	 * @param icon resource identifier
	 * @param contentTitle notification title
	 * @param contentText notification content
	 * @param activity class name of the activity to launch when the user clicks the expanded notification   
	 * @param id an identifier for this notification unique within your application.
	 * @param persistent a boolean indicating if the notifciation has to be persistent or not
	 * @throws CartagoException activity class not found exception
	 */
	@OPERATION public void showNotification(int icon, String contentTitle, String contentText, String activity, boolean persistent, OpFeedbackParam<Integer> id) throws CartagoException {
		_showNotification(icon, contentTitle, contentText, activity, persistent, id);
	}
	
	/**
	 * Private utility method used for generating the notifications
	 * @param icon
	 * @param contentTitle
	 * @param contentText
	 * @param activity
	 * @param persistent
	 * @param id
	 * @throws CartagoException
	 */
	private void _showNotification(int icon, String contentTitle, String contentText, String activity, boolean persistent, OpFeedbackParam<Integer> id) throws CartagoException {
		Notification notification = new Notification();

		if(persistent){
			notification.flags |= Notification.FLAG_ONGOING_EVENT;
			notification.flags |= Notification.FLAG_NO_CLEAR;
		}
		
		notification.tickerText = contentTitle;
		notification.when = System.currentTimeMillis();
		notification.icon =  icon;
		try {
			Intent intent = new Intent(getApplicationContext(), Class.forName(activity));
			intent.putExtra("contentTitle", contentTitle);
			intent.putExtra("contentText", contentText);
			PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
			notification.setLatestEventInfo(getApplicationContext(), contentTitle, contentText, contentIntent);
			mNotificationManager.notify(mID, notification);
			id.set(mID++);
			
		} catch (ClassNotFoundException e) {
			throw new CartagoException(e.getMessage());
		}
	}
	
	/**
	 * Artifact operation that cancel a previously shown notification
	 * 
	 * @param id identifier of the notification that has to be canceled
	 */
	@OPERATION public void cancelNotification(int id) {
		mNotificationManager.cancel(id);
	}
	
	/**
	 * Artifac operation that cancel all previously shown notifications
	 */
	@OPERATION public void cancelAllNotification() {
		mNotificationManager.cancelAll();
	}
}