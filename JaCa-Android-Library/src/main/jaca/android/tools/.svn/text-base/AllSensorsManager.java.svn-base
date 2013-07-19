package jaca.android.tools;

import jaca.android.dev.SensorManagerArtifact;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

/**
 * Artifact for managing the three most common Android sensors in a 
 * whole: accelerometer sensor, geomagnetic sensor, orientation sensor
 * @author asanti
 *
  * <p> #### Observable Properties #####
 *  <ul>
 *  	<li>
 *  	{@code sensor_accelerometer(X,Y,Z)}. 
 *  		{@code X,Y,Z} = double values containing the current acceleration force
 *  				for the X,Y,Z axis 
 * 		</li>
 *  	<li>
 *  	{@code sensor_geomag(X,Y,Z)}. 
 *  		{@code Z,Y,Z} = double values containing the current geomagnetic force
 *  				for the X,Y,Z axis 
 * 		</li>
 *  	<li>
 *  	{@code sensor_orientation(Azimuth,Pitch,Roll)}.
 *  		<ul>
 *  			<li>{@code Azimuth} = double value containing the current azimuth value. Range [0,360[ (360 excluded)</li>
 *  			<li>{@code Pitch} = double value containing the current pitch value. Range [-180,180]</li>
 *  			<li>{@code Roll} = double value containing the current roll value. Range [-90,90]</li>
 *  		</ul> 
 *  		Azimuth 
 * 		</li>
 *  </ul>
 * </p>
 */
public class AllSensorsManager extends SensorManagerArtifact{

	private float[] mAcc = new float[3];
	private float[] mGeoMags = new float[3];
	private float[] mRotationM = new float[16];
	private float[] mRemappedRotationM = new float[16];
	private boolean sensorReady = false;

	/**
	 * Artifact default name
	 */
	public static final String ARTIFACT_DEF_NAME =  "all-sensors-manager";
	
	/**
	 * Initialisation with the sensor delay provided in input (one of {@link SensorManager}.SENSOR_DELAY_*) 
	 * @param delay
	 * @throws Exception
	 */
	@OPERATION public void init(Integer delay) throws Exception{
		
		super.init(delay, Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_MAGNETIC_FIELD, Sensor.TYPE_ORIENTATION);
		
		defineObsProperty(SensorManagerArtifact.SENSOR_ACCELEROMETER, 0,0,0);
		defineObsProperty(SensorManagerArtifact.SENSOR_GEOMAG, 0,0,0);
		defineObsProperty(SensorManagerArtifact.SENSOR_ORIENTATION, 0,0,0);
		
		linkOnSensorChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnSensorChangedEventToOp(Sensor.TYPE_MAGNETIC_FIELD, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnSensorChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_MAGNETIC_FIELD, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	/**
	 * Initialisation with the default sensor delay
	 * @throws Exception
	 */
	@OPERATION public void init() throws Exception{
		
		super.init(SensorManager.SENSOR_DELAY_NORMAL, Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_MAGNETIC_FIELD, Sensor.TYPE_ORIENTATION);

		defineObsProperty(SensorManagerArtifact.SENSOR_ACCELEROMETER, 0,0,0);
		defineObsProperty(SensorManagerArtifact.SENSOR_GEOMAG, 0,0,0);
		defineObsProperty(SensorManagerArtifact.SENSOR_ORIENTATION, 0,0,0);
		
		linkOnSensorChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnSensorChangedEventToOp(Sensor.TYPE_MAGNETIC_FIELD, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnSensorChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_MAGNETIC_FIELD, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ORIENTATION, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	@Override	
	@INTERNAL_OPERATION public void onSensorChanged(SensorEvent event) {

		switch(event.sensor.getType()){

			case Sensor.TYPE_ACCELEROMETER:
				getObsProperty(SensorManagerArtifact.SENSOR_ACCELEROMETER).updateValues(event.values[0], event.values[1], event.values[2]);
				mAcc = event.values.clone();
				sensorReady = true;
			break;
	
			case Sensor.TYPE_MAGNETIC_FIELD:
				getObsProperty(SensorManagerArtifact.SENSOR_GEOMAG).updateValues(event.values[0], event.values[1], event.values[2]);
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
			
			getObsProperty(SensorManagerArtifact.SENSOR_ORIENTATION).updateValues(azimuth, pitch, roll);
		}
	}

	@Override
	@INTERNAL_OPERATION public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
}