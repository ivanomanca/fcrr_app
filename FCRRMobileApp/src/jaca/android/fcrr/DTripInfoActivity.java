package jaca.android.fcrr;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import jaca.android.dev.JaCaActivity;
import jaca.android.fcrr.util.JsonParam;

public class DTripInfoActivity extends JaCaActivity {

	private Handler mHandler;
	private SimpleAdapter mAdapter;
	private ListView mListView;
	
	private TextView name;
	private TextView surname;
	private TextView from;
	private TextView to;
	private TextView date_dep;
	private TextView taken_seats;
	private TextView total_seats;
	private Button start;
	private Button delete;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.d_trip_info_list);
		
		//mListView = getListView();
		mListView = (ListView)findViewById(R.id.tripInfoListView);
		mHandler = new Handler();
		
		/* pulsanti
		start = (Button) findViewById(R.id.startTrip);
		delete = (Button) findViewById(R.id.deleteTrip);
		
		// textview
		//name = (TextView) findViewById(R.id.name);
		//surname = (TextView) findViewById(R.id.surname);
		
		from = (TextView) findViewById(R.id.from);
		to = (TextView) findViewById(R.id.to);
		date_dep = (TextView) findViewById(R.id.date_dep);
		taken_seats = (TextView) findViewById(R.id.taken_seats);
		total_seats = (TextView) findViewById(R.id.totalseats);
		*/
		// Creo l'artefatto per la GUI
		createArtifact(	"dtripinfogui", DTripInfoGUI.class.getCanonicalName());
	}
	
	public void fillView(	final ArrayList<HashMap<String, String>> riders,
							final ArrayList<HashMap<String, String>> infoTrip){
		
		mHandler.post(new Runnable() {
			public void run() {
				// inserisco le info sul viaggio
				if(!infoTrip.isEmpty()){
				HashMap<String, String> tripInfo = infoTrip.get(0);
				
				// pulsanti
				start = (Button) findViewById(R.id.startTrip);
				delete = (Button) findViewById(R.id.deleteTrip);
				
				// textview
				//name = (TextView) findViewById(R.id.name);
				//surname = (TextView) findViewById(R.id.surname);
				
				from = (TextView) findViewById(R.id.from);
				to = (TextView) findViewById(R.id.to);
				date_dep = (TextView) findViewById(R.id.date_dep);
				taken_seats = (TextView) findViewById(R.id.taken_seats);
				total_seats = (TextView) findViewById(R.id.totalseats);
				
				//name.setText(tripInfo.get("name"));
				//surname.setText(tripInfo.get("surname"));
				from.setText(tripInfo.get(JsonParam.Trip.from));
				to.setText(tripInfo.get(JsonParam.Trip.to));
				date_dep.setText(tripInfo.get(JsonParam.Trip.date_dep));
				taken_seats.setText(tripInfo.get(JsonParam.Trip.taken_seats));
				total_seats.setText(tripInfo.get(JsonParam.Trip.total_seats));
				
				// inserisco le info sui riders
				String[] fr = {JsonParam.Carpooler.name, JsonParam.Carpooler.surname};
				int[] tr = {R.id.name, R.id.surname};

				//costruzione dell'adapter
				mAdapter = new SimpleAdapter(	getApplicationContext(), 
												riders, 
												R.layout.d_trip_info_list_row, fr, tr);

				// setto l'adapter
				mListView.setAdapter(mAdapter);
				}
			}
		});	
	}
}
