package jaca.android.tests.allsensors;

import jaca.android.dev.JaCaActivity;
import jaca.android.samples.main.R;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * 
 * @author mguidi
 *
 */
public class SensorsActivity extends JaCaActivity {

	private Handler mHandler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mHandler = new Handler();
	    setContentView(R.layout.sensors_layout);
	    createArtifact("sensor-art", SensorsGUIArtifact.class.getCanonicalName());
    }
	
	
	public void updateAccSensorsInfo(final double x, final double y, final double z){
		mHandler.post(new Runnable() {
			public void run() {
				TextView accXTxt = (TextView) findViewById(R.id.acc_txt_x_values);
				TextView accYTxt = (TextView) findViewById(R.id.acc_txt_y_values);
				TextView accZTxt = (TextView) findViewById(R.id.acc_txt_z_values);
				accXTxt.setText("  X: "+ x);
				accYTxt.setText("  Y: "+ y);
				accZTxt.setText("  Z: "+ z);
			}
		});
	}

	public void updateOrientationSensorsInfo(final double azimuth, final double pitch, final double roll){
		mHandler.post(new Runnable() {
			public void run() {
				TextView orientAzimuthTxt = (TextView) findViewById(R.id.orientation_txt_azimuth_values);
				TextView orientPitchTxt = (TextView) findViewById(R.id.orientation_txt_pitch_values);
				TextView orientRollTxt = (TextView) findViewById(R.id.orientation_txt_roll_values);
				orientAzimuthTxt.setText("  Azimuth: "+ azimuth);
				orientPitchTxt.setText("  Pitch: "+ pitch);
				orientRollTxt.setText("  Roll: "+ roll);
			}
		});
	}
	
	public void updateGeomagSensorsInfo(final double x, final double y, final double z){
		mHandler.post(new Runnable() {
			public void run() {
				TextView geoXTxt = (TextView) findViewById(R.id.geomag_txt_x_values);
				TextView geoYTxt = (TextView) findViewById(R.id.geomag_txt_y_values);
				TextView geoZTxt = (TextView) findViewById(R.id.geomag_txt_z_values);
				geoXTxt.setText("  X: "+ x);
				geoYTxt.setText("  Y: "+ y);
				geoZTxt.setText("  Z: "+ z);
			}
		});
	}
}