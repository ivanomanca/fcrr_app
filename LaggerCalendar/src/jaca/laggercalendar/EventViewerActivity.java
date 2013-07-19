package jaca.laggercalendar;

import jaca.android.dev.JaCaActivity;
import jaca.laggercalendar.artifacts.EventManagerArtifact;
import jaca.laggercalendar.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class EventViewerActivity extends JaCaActivity {
	
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
   	    createArtifact("event-mngr", EventManagerArtifact.class.getCanonicalName());
     }
    
    public void append(final Date startDate, final Date endDate,final String name){
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				
				SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");				
				mEventsArrayAdapter.add(ft.format(startDate)+"-"+ft.format(endDate)+" "+name);
			}
		});
	}
}