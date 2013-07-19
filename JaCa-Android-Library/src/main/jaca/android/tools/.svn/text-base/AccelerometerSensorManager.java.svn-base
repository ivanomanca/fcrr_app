package jaca.android.tools;

import jaca.android.dev.SensorManagerArtifact;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

/**
 * Artifact for managing an Android accelerometer sensor
 * @author asanti
 *
  * <p> #### Observable Properties #####
 *  <ul>
 *  	<li>
 *  	{@code accelerometer_x(Value)}. 
 *  		{@code Value} = double value containing the current acceleration force
 *  				for the X axis 
 * 		</li>
 *  	<li>
 *  	{@code accelerometer_y(Value)}. 
 *  		{@code Value} = double value containing the current acceleration force
 *  				for the y axis 
 * 		</li>
 *  	<li>
 *  	{@code accelerometer_z(Value)}. 
 *  		{@code Value} = double value containing the current acceleration force
 *  				for the z axis 
 * 		</li>
 *  </ul>
 * </p>
 */
public class AccelerometerSensorManager extends SensorManagerArtifact{

	/**
	 * Name of the observable property storing the current acceleration
	 * force in the x axis 
	 */
	public static final String X_FORCE = "accelerometer_x";
	
	/**
	 * Name of the observable property storing the current acceleration
	 * force in the y axis 
	 */
	public static final String Y_FORCE = "accelerometer_y";
	
	/**
	 * Name of the observable property storing the current acceleration
	 * force in the z axis 
	 */
	public static final String Z_FORCE = "accelerometer_z";
	
	/**
	 * Artifact default name
	 */
	public static final String ARTIFACT_DEF_NAME =  "acc-sensor-manager";
	
	/**
	 * Initialisation with the default sensor delay
	 * @throws Exception
	 */
	@OPERATION public void init() throws Exception{
		super.init(SensorManager.SENSOR_DELAY_NORMAL, Sensor.TYPE_ACCELEROMETER);

		defineObsProperty(X_FORCE, 0);
		defineObsProperty(Y_FORCE, 0);
		defineObsProperty(Z_FORCE, 0);
		
		linkOnSensorChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	/**
	 * Initialisation with the sensor delay provided in input (one of {@link SensorManager}.SENSOR_DELAY_*)
	 * @param delay
	 * @throws Exception
	 */
	@OPERATION public void init(Integer delay) throws Exception{
		super.init(delay,Sensor.TYPE_ACCELEROMETER);

		defineObsProperty(X_FORCE, 0);
		defineObsProperty(Y_FORCE, 0);
		defineObsProperty(Z_FORCE, 0);
		
		linkOnSensorChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
		linkOnAccuracyChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_ACCURACY_CHANGED);
	}

	@Override
	@INTERNAL_OPERATION public void onSensorChanged(SensorEvent event) {
		switch(event.sensor.getType()){
			case Sensor.TYPE_ACCELEROMETER:
				getObsProperty(X_FORCE).updateValues(event.values[0]);
				getObsProperty(Y_FORCE).updateValues(event.values[1]);
				getObsProperty(Z_FORCE).updateValues(event.values[2]);
			break;
		}
	}

	@Override
	@INTERNAL_OPERATION public void onAccuracyChanged(Sensor arg0, int arg1) {
	}	
}