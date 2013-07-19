package jaca.android.fcrr;

import jaca.android.dev.JaCaListActivity;
import jaca.android.fcrr.util.JsonParam;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author Ivano Manca (ivano.manca@studio.unibo.it)
 *
 */
public class DTripListActivity extends JaCaListActivity {

	private Handler mHandler;
	private SimpleAdapter mAdapter;
	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.driver_triplist);
		mListView = getListView();
		mHandler = new Handler();
		
		// Creo l'artefatto per la GUI
		createArtifact("dtriplistgui", DTripListGUI.class.getCanonicalName());
	}
	
	public void fillListView(final ArrayList<HashMap<String, String>> data){
		mHandler.post(new Runnable() {
			public void run() {
				// name, surname, from, to, availableseats, totalseats, date
				String[] from = {JsonParam.Trip.date_dep, JsonParam.Trip.from, JsonParam.Trip.to, JsonParam.Trip.taken_seats, JsonParam.Trip.total_seats};
				int[] to = {R.id.date_dep, R.id.from, R.id.to, R.id.taken_seats, R.id.total_seats};

				//costruzione dell'adapter
				mAdapter = new SimpleAdapter(getApplicationContext(), data, R.layout.d_trip_list_row, from, to);

				// setto l'adapter
				mListView.setAdapter(mAdapter);
			}
		});	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see jaca.android.dev.JaCaActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jaca.android.dev.JaCaActivity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jaca.android.dev.JaCaActivity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jaca.android.dev.JaCaActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jaca.android.dev.JaCaActivity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jaca.android.dev.JaCaActivity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
