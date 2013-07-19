package jaca.android.tools;

import jaca.android.dev.SensorManagerArtifact;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

/**
 * Artifact for managing an Android orientation sensor
 * @author asanti
 *
  * <p> #### Observable Properties #####
 *  <ul>
 *  	<li>
 *  	{@code orientation_azimuth(Value)}. 
 *  		{@code Value} = double value containing the current azimuth value. Range [0,360[ (360 excluded)
 * 		</li>
 *  	<li>
 *  	{@code orientation_pitch(Value)}. 
 *  		{@code Value} = double value containing the current pitch value. Range [-180,180]
 *  	</li>
 *  	<li>
 *  	{@code orientation_roll(Value)}. 
 *  		{@code Value} = double value containing the current roll value. Range [-90,90]
 *  	</li>
 *  </ul>
 * </p>
 */
public class OrientationSensorManager extends SensorManagerArtifact{

	private float[] mAcc = new float[3];
	private float[] mGeoMags = new float[3];
	private float[] mRotationM = new float[16];
	private float[] mRemappedRotationM = new float[16];
	private boolean sensorReady;
	
	/**
	 * Name of the observable property storing the current azimuth value
	 */
	public static final String AZIMUT = "orientation_azimuth";
	
	/**
	 * Name of the observable property storing the current pitch value
	 */
	public static final String PITCH = "orientation_pitch";
	
	/**
	 * Name of the observable property storing the current roll value
	 */
	public static final String ROLL = "orientation_roll";

	/**
	 * Initialisation with the sensor delay provided in input (one of {@link SensorManager}.SENSOR_DELAY_*)
	 * @param delay
	 * @throws Exception
	 */
	@OPERATION public void init(Integer delay) throws Exception{
		
		super.init(delay, Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_MAGNETIC_FIELD);
		sensorReady = false;
		
		defineObsProperty(AZIMUT, 0);
		defineObsProperty(PITCH, 0);
		defineObsProperty(ROLL, 0);
		
		linkOnSensorChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	/**
	 * Initialisation with the default sensor delay
	 * @throws Exception
	 */
	@OPERATION public void init() throws Exception{
		
		super.init(SensorManager.SENSOR_DELAY_NORMAL, Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_MAGNETIC_FIELD);
		sensorReady = false;

		defineObsProperty(AZIMUT, 0);
		defineObsProperty(PITCH, 0);
		defineObsProperty(ROLL, 0);
		
		linkOnSensorChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	@Override
	@INTERNAL_OPERATION public void onSensorChanged(SensorEvent event) {
		switch(event.sensor.getType()){

			case Sensor.TYPE_ACCELEROMETER:
				mAcc = event.values.clone();
				sensorReady = true;
			break;
	
			case Sensor.TYPE_MAGNETIC_FIELD:
				mGeoMags = event.values.clone();
				sensorReady = true;
			break;
		}   

		//Orientation management
		if (mGeoMags != null && mAcc != null && sensorReady) {

			sensorReady = false;

			float[] Rm = new float[16];
			float[] I = new float[16];

			SensorManager.getRotationMatrix(Rm, I, mAcc, mGeoMags);
			float[] actual_orientation = new float[3];

			//Conversion in degrees
			SensorManager.remapCoordinateSystem(mRotationM,SensorManager.AXIS_X,SensorManager.AXIS_Z,mRemappedRotationM);
			SensorManager.getOrientation(Rm, actual_orientation);
			double azimuth = (360*actual_orientation[0]) / (2*Math.PI);
			double pitch = (360*actual_orientation[1]) / (2*Math.PI);
			double roll = (360*actual_orientation[2]) / (2*Math.PI);

			getObsProperty(AZIMUT).updateValues(azimuth);
			getObsProperty(PITCH).updateValues(pitch);
			getObsProperty(ROLL).updateValues(roll);
		}
	}

	@Override
	@INTERNAL_OPERATION public void onAccuracyChanged(Sensor arg0, int arg1) {
	}	
}