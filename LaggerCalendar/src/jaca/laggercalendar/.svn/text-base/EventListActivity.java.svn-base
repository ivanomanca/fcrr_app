package jaca.laggercalendar;

import jaca.android.dev.JaCaActivity;
import jaca.laggercalendar.artifacts.EventManagerArtifact;

import java.util.Calendar;
import java.util.Date;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
 
public class EventListActivity extends JaCaActivity { 
	
	private Handler mHandler;
	private ArrayAdapter<String> mEventsArrayAdapter;
	private ListView mConversationView; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	mHandler =  new Handler(); 
   	    setContentView(R.layout.event_view);
   	    mEventsArrayAdapter = new ArrayAdapter<String>(this, R.layout.event);
   	    mConversationView = (ListView) findViewById(R.id.in);
   	    mConversationView.setAdapter(mEventsArrayAdapter);
         
   	    runJaCaService(R.string.laggercalendar_mas2j);
   	    createArtifact("events-mngr", EventManagerArtifact.class.getCanonicalName());
     }
    
    public void append(final Date startDate, final Date endDate,final String name){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				mEventsArrayAdapter.add(startDate.getHours()+":"+startDate.getMinutes()+"-"+endDate.getHours()+":"+endDate.getMinutes()+" "+name);
			}
		});
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu from XML resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    public void showEventReminder(final int eventID, final String description, final Date startTime){
    	mHandler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "Event " + eventID +"  " +description + " with start time " + startTime + "approaching", Toast.LENGTH_LONG).show();
			}
		});
    }
    
	public void showDelayManagementWindow(final Date estimateArrival){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				final Calendar c = Calendar.getInstance();
				int mHour = c.get(Calendar.HOUR_OF_DAY);
				int mMinute = c.get(Calendar.MINUTE);

				TimePickerDialog t = new TimePickerDialog(EventListActivity.this, mTimeSetListener, mHour, mMinute, true);
				t.setTitle("Select the new starting time for next appointment");
				t.show();
				/*
				 * TODO add here a mechanism for allowing the activity to notify the EventEditorGUI of the action performed by the user:
				 * 1) Selected a new appointment time
				 * 2) Canceled the delay notification
				 */
			}
		});
	}
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			int mHour = hourOfDay;
    	    int mMinute = minute;
    	    //TODO handle the event notification to the EventEditorGUI
    	    //getActivityEventsFetcher().putEvent(new EventOpInfo(this, ActivityEventsFetcher.ON_CLICK, mHour, mMinute));
		}
	};
}