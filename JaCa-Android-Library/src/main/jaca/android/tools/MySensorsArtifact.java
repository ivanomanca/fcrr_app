package jaca.android.tools;

import jaca.android.dev.SensorManagerArtifact;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;

public class MySensorsArtifact extends SensorManagerArtifact {

	private static int FORCE_THRESHOLD = 480;
	private static final int TIME_THRESHOLD = 200;
	private static final int SHAKE_DURATION = 750;
	private static final String TOP_SIDE_UP = "top_side_up";
	private static final String LEFT_SIDE_UP = "left_side_up";
	private static final String RIGHT_SIDE_UP = "right_side_up";
	private static final String BOTTOM_SIDE_UP = "bottom_side_up";
	private static String ORIENTATION = "orientation";
	private float[] mAcc = new float[3];
	private float[] mGeoMags = new float[3];
	private float[] mRotationM = new float[16];
	private float[] mRemappedRotationM = new float[16];
	private boolean sensorReady = false;
	private float mLastX=-1.0f, mLastY=-1.0f, mLastZ=-1.0f;
	private long mLastShake;
	private long mLastOrientation;
	private long mLastAcc;

	@OPERATION public void init() throws Exception{
		super.init(SensorManager.SENSOR_DELAY_NORMAL,
				Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_MAGNETIC_FIELD,Sensor.TYPE_ORIENTATION);

		defineObsProperty(MySensorsArtifact.ORIENTATION, "");

		linkOnSensorChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnSensorChangedEventToOp(Sensor.TYPE_MAGNETIC_FIELD, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnSensorChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_MAGNETIC_FIELD, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	@Override
	@INTERNAL_OPERATION public void onSensorChanged(SensorEvent event) {
		long now = System.currentTimeMillis();
		long diff;
		
		switch(event.sensor.getType()){

			case Sensor.TYPE_ACCELEROMETER:
				//Log.v("mysensors", "EXECUTING ARTIFACT OPERATIONNNNNNNNNNNN:");
				diff = (now - mLastAcc);
				if (diff > TIME_THRESHOLD) {
					
					sensorReady = true;
					float speed = Math.abs(event.values[SensorManager.DATA_X] + event.values[SensorManager.DATA_Y] + event.values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ) / diff * 10000;
					//getObsProperty(MySensorsArtifact.ORIENTATION).updateValue(/*""+speed + */"diff:" + (now - arrivedTime) +" left" + leftCapacity);
					
					//Check shake force
					if (speed > FORCE_THRESHOLD) { 
						if(now - mLastShake > SHAKE_DURATION) {
							mLastShake = now;
							signal("shake_event");
							//Log.v("mysensors","SHAKEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
						}
					}
					mAcc = event.values.clone();
					sensorReady = true;
	
					mLastAcc = now;
					mLastX = event.values[SensorManager.DATA_X];
					mLastY = event.values[SensorManager.DATA_Y];
					mLastZ = event.values[SensorManager.DATA_Z];
					//((TextView)((Activity)getApplicationContext()).findViewById(R.id.txtViewer)).setText(""+speed);
					//new AlertDialog.Builder(getApplicationContext()).setPositiveButton(android.R.string.ok, null).setMessage(""+speed).show();
				}
			break;

			case Sensor.TYPE_MAGNETIC_FIELD:
				mGeoMags = event.values.clone();
				sensorReady = true;
			break;
		}   
		
		//Orientation handling
		if (mGeoMags != null && mAcc != null && sensorReady) {

			diff = now - mLastOrientation;
			if(diff > 1000){
				sensorReady = false;

				float[] Rm = new float[16];
				float[] I = new float[16];

				SensorManager.getRotationMatrix(Rm, I, mAcc, mGeoMags);
				float[] actual_orientation = new float[3];


				SensorManager.remapCoordinateSystem(mRotationM,SensorManager.AXIS_X,SensorManager.AXIS_Z,mRemappedRotationM);
				SensorManager.getOrientation(Rm, actual_orientation);
				double azimuth = (360*actual_orientation[0]) / (2*Math.PI);     // azimuth
				double pitch = (360*actual_orientation[1]) / (2*Math.PI);     // pitch
				double roll = (360*actual_orientation[2]) / (2*Math.PI);        // roll
				if(roll> 90 || roll < -90) roll %=90;

				//Log.v("mysensors","azimuth:" + azimuth + "pitch" + pitch + "roll" + roll);
				ObsProperty orientationProp = getObsProperty(MySensorsArtifact.ORIENTATION);
				

				if ((pitch < -45) && (pitch > -135)){
					if(!orientationProp.stringValue().equals(TOP_SIDE_UP))
						orientationProp.updateValue(TOP_SIDE_UP);
					//Log.v("mysensors","ORIENTATION: TOP_SIDE_UP");
				} 
				else if ((pitch > 45) && (pitch < 135) ) {
					if (!orientationProp.stringValue().equals(BOTTOM_SIDE_UP))
							orientationProp.updateValue(BOTTOM_SIDE_UP);
					//Log.v("mysensors","ORIENTATION: BOTTOM_SIDE_UP");
				}
				else if ((roll > 45)) {
					if (!orientationProp.stringValue().equals(RIGHT_SIDE_UP))
							orientationProp.updateValue(RIGHT_SIDE_UP);
					//Log.v("mysensors","ORIENTATION: RIGHT_SIDE_UP");
				}
				else if ((roll < - 45)) {
					
					 if(!orientationProp.stringValue().equals(LEFT_SIDE_UP))
						 orientationProp.updateValue(LEFT_SIDE_UP);
					//Log.v("mysensors","ORIENTATION: left_SIDE_UP");
				}
				mLastOrientation = now;
			}
		}			
	}

	@Override
	@INTERNAL_OPERATION public void onAccuracyChanged(Sensor arg0, int arg1) {}
}