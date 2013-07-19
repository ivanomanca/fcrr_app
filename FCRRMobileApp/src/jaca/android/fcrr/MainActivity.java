package jaca.android.fcrr;

import jaca.android.dev.JaCaActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends JaCaActivity {

	private TextView titleText;
	private Button driverButton;
	private Button riderButton;
	private Handler mHandler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		runJaCaService(R.string.fcrr_mas2j);
		
		mHandler = new Handler();
		
		createArtifact("main-gui", MainGUI.class.getCanonicalName());
	}
	
	
	public void setupView() {
		mHandler.post(new Runnable() {
			public void run() {
				titleText = (TextView) findViewById(R.id.init_trip);
				driverButton = (Button) findViewById(R.id.driver_button);
				riderButton = (Button) findViewById(R.id.rider_button);
				
				titleText.setText(R.string.init_trip);
				driverButton.setText(R.string.driver_button);
				riderButton.setText(R.string.rider_button);
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
		stopJaCaService();
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
}