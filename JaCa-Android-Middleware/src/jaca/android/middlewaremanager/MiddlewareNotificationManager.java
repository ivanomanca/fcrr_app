package jaca.android.middlewaremanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MiddlewareNotificationManager {
	
	public static MiddlewareNotificationManager instance;
	
	private static Context mCtx;
	private static NotificationManager mNotificationManager; 
	private MiddlewareNotificationManager(Context ctx){
		mCtx = ctx;
		mNotificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE); 
	}
	
	public static MiddlewareNotificationManager getInstance(Context ctx){
		if(instance==null) instance = new MiddlewareNotificationManager(ctx);
		return instance;
	}
	
	public void showMiddlewareIcon(){
        String notificationText = mCtx.getString(R.string.app_name);
        String notificationTitle = mCtx.getString(R.string.status_bar_text);

		Notification notification = new Notification();
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.flags |= Notification.FLAG_NO_CLEAR; 
		notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		notification.tickerText = notificationText;
		notification.when = System.currentTimeMillis();
		notification.icon =  R.drawable.jaca_android_logo;
		Intent notificationIntent = new Intent(mCtx, MiddlewareManagerActivity.class);

		PendingIntent contentIntent = PendingIntent.getActivity(mCtx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(mCtx, notificationText, notificationTitle, contentIntent);
		mNotificationManager.notify(R.layout.main, notification);
	}
	
	public void removeMiddlewareIcon(){
		mNotificationManager.cancel(R.layout.main);
	}
}