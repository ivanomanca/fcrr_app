package jaca.android.tools;

import jaca.android.dev.BroadcastReceiverArtifact;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.DateUtils;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

/**
 * Artifact for managing the built-in google calendar. It works only with Android version 2.2. 
 * In more recent version there are limitations provided by Google in the access of the Calendar API.
 * 
 * <p><strong> #### Observable Properties ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 * 			{@code event(calendar_id, Description, StartTime, EndTime).}
 *  		<ul>
 *  			<li>	
 *  				{@code calendar_id} = An int value indicating the calendar from which the
 *  							  		  event comes from
 *				</li>
 *				<li>
 *					{@code Description} = A {@code String} that describes the event 
 *				</li>
 *				<li>
 *					{@code Start/EndTime} = {@code Date} objects storing the start/end date of the event 
 *				</li>
 *			</ul>
 *		</li>
 *	</ul>
 * </p>
 *
 * <p><strong> #### Observable Events ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 * 			{@code event_started(id, title)}
 *  		<ul>
 *  			<li>{@code id} = An int containing the id of the event</li>	
 *  			<li>{@code title} = A String containing the title of the event</li>
 *  		</ul>
 * 		</li>
 * 		<li>
 * 			{@code event_ended(id, title)}
 *  		<ul>
 *  			<li>{@code id} = An int containing the id of the event</li>	
 *  			<li>{@code title} = A String containing the title of the event</li>
 *  		</ul>
 * 		</li>
 * 	</ul>
 * </p>	
 * @author asanti
 *
 */
public class CalendarManager extends BroadcastReceiverArtifact {

	/**
	 * Name of the observable properties user to display the events in the calendar.
	 */
	public static final String EVENT = "event";

	/**
	 * Default initialisation: the interested timespan is one week. 
	 */
	public void init(){
		init(DateUtils.WEEK_IN_MILLIS);
	}

	/**
	 * Artifact default name
	 */
	public static final String ARTIFACT_DEF_NAME =  "calendar-manager";
	
	public void init(long timeSpan){
		super.init();
		//Listen for event reminder intent
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.EVENT_REMINDER");
		//The operation responsible to manage the event reminder
		linkBroadcastReceiverToOp(filter, "onEventReminder");

		ArrayList<AppInfo> apps = fetchCalendar(timeSpan);
		long now = new Date().getTime();		

		for(AppInfo app : apps){
			defineObsProperty(EVENT, app.getId(), app.getTitle(), app.getStart(), app.getEnd());

			if(app.getStart().getTime() - now> 0){
				execInternalOp("scheduleStartEvent", app.getId(), app.getTitle(), app.getStart().getTime() - now);
			}
			execInternalOp("scheduleEndEvent", app.getId(), app.getTitle(), app.getEnd().getTime() - now);
		}
	}

	@OPERATION public void getOngoingEvents(OpFeedbackParam<ArrayList<AppInfo>> result){
		ArrayList<AppInfo> res = fetchCalendar(DateUtils.MINUTE_IN_MILLIS);
		if(res.size()==0) result.set(null);
		else result.set(res);
	}

	@INTERNAL_OPERATION public void scheduleStartEvent(String id, String title, long delay){
		await_time(delay);
		signal("event_started", id, title);
	}

	@INTERNAL_OPERATION public void scheduleEndEvent(String id, String title, long delay){
		await_time(delay);
		signal("event_ended", id, title);
	}

	@INTERNAL_OPERATION public void onEventRemainder(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		System.out.println("Inizio evento beccato");

	}

	/**
	 * Utility method used for fetching calendar events
	 * @param timeSpan
	 * @return
	 */
	private ArrayList<AppInfo> fetchCalendar(long timeSpan){
		ArrayList<AppInfo> returnValue = new ArrayList<AppInfo>();
		ContentResolver contentResolver = getApplicationContext().getContentResolver();

		// Fetch a list of all calendars synced with the device, their display names and whether the
		// user has them selected for display.
		Cursor cursor = contentResolver.query(Uri.parse("content://calendar/calendars"),
				(new String[] { "_id", "displayName", "selected" }), null, null, null);

		// For a full list of available columns see http://tinyurl.com/yfbg76w 	

		HashSet<String> calendarIds = new HashSet<String>();

		while (cursor.moveToNext()) {

			final String _id = cursor.getString(0);
			final String displayName = cursor.getString(1);
			final Boolean selected = !cursor.getString(2).equals("0");

			//System.out.println("Id: " + _id + " Display Name: " + displayName + " Selected: " + selected);
			calendarIds.add(_id);
		}

		// For each calendar, display all the events from now to the end of next week.		
		for (String id : calendarIds) {
			Uri.Builder builder = Uri.parse("content://calendar/instances/when").buildUpon();
			long now = new Date().getTime();
			ContentUris.appendId(builder, now);
			ContentUris.appendId(builder, now + timeSpan);
			Cursor eventCursor = contentResolver.query(builder.build(),
					new String[] { "title", "begin", "end", "allDay"}, "Calendars._id=" + id,
					null, "startDay ASC, startMinute ASC"); 
			// For a full list of available columns see http://tinyurl.com/yfbg76w

			while (eventCursor.moveToNext()) {
				final String title = eventCursor.getString(0);
				final Date start = new Date(eventCursor.getLong(1));
				final Date end = new Date(eventCursor.getLong(2));
				final Boolean allDay = !eventCursor.getString(3).equals("0");

				/*System.out.println("Title: " + title + " Begin: " + begin + " End: " + end +
					" All Day: " + allDay);*/
				returnValue.add(new AppInfo(id, title, start, end));				
			}
		}
		return returnValue;
	}
	
	/**
	 * Internal class used for storing event related information
	 * @author asanti
	 *
	 */
	private class AppInfo{

		private String id;
		private String title;
		private Date start;
		private Date end;

		public AppInfo(String id, String title, Date start, Date end) {
			super();
			this.id = id;
			this.title = title;
			this.start = start;
			this.end = end;
		}

		public String getId() {
			return id;
		}
		public String getTitle() {
			return title;
		}
		public Date getStart() {
			return start;
		}
		public Date getEnd() {
			return end;
		}
	}
}