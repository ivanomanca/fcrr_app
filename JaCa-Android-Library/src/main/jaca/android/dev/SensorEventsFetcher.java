package jaca.android.dev;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Class used for managing the dispatch of Sensor events
 * to CArtAgO Artifacts
 * @author asanti
 *
 */
public class SensorEventsFetcher extends BaseEventFetcher implements SensorEventListener{

	private SensorManager mSensorMgr;
	private Integer[] mSensorsTypes;
	private int mDelay;
	
	/**
	 * Name of the listener called by Android each time 
	 * a sensor changes its value/s 
	 */
	public static final String ON_SENSOR_CHANGED = "onSensorChanged";
	
	/**
	 * Name of the listener called by Android each time 
	 * a sensor changes its accuracy (i.e. update frequence) 
	 */
	public static final String ON_ACCURACY_CHANGED = "onAccuracyChanged";

	/**
	 * Create a SensorEventsFetcher with the parameters provided in input. This 
	 * class is used as a bridge for providing Android sensor events to artifacts
	 * @param mSensorMgr
	 * @param delay One of the {@code SensorManager.SENSOR_DELAY_*;}
	 * @param sensorsTypes The list of interested sensors 
	 * @throws Exception exception thrown if there is no support for the sensor specified in input
	 */
	public SensorEventsFetcher(SensorManager sensorMgr, Integer delay, Integer...sensorsTypes)  throws Exception{
		if(delay==SensorManager.SENSOR_DELAY_UI || delay==SensorManager.SENSOR_DELAY_NORMAL
				|| delay==SensorManager.SENSOR_DELAY_GAME || delay==SensorManager.SENSOR_DELAY_FASTEST){

			mSensorMgr = sensorMgr;
			//Sensors search
			for(Integer sensorType : sensorsTypes){
				Sensor sensor = mSensorMgr.getDefaultSensor(sensorType);
				if(sensor==null) new ExceptionInInitializerError("Sensor not supported");
			}
			mSensorsTypes = sensorsTypes;
		}
		else throw new ExceptionInInitializerError("Invalid delay specified");
	}
	
	public void startMonitoring(){
		try{
			for(Integer sensorType : mSensorsTypes){
				Sensor sensor = mSensorMgr.getDefaultSensor(sensorType);
				boolean supported = mSensorMgr.registerListener(this, sensor, mDelay);
				if (!supported) {
					this.stopMonitoring();
					break;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void stopMonitoring(){
		try{
			mSensorMgr.unregisterListener(this);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		putEvent(new EventOpInfo(arg0.getType(), ON_ACCURACY_CHANGED, arg0, arg1));
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch(event.sensor.getType()){

		case Sensor.TYPE_ACCELEROMETER:
			putEvent(new EventOpInfo(Sensor.TYPE_ACCELEROMETER, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_MAGNETIC_FIELD:
			putEvent(new EventOpInfo(Sensor.TYPE_MAGNETIC_FIELD, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_LIGHT:
			putEvent(new EventOpInfo(Sensor.TYPE_LIGHT, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_GYROSCOPE:
			putEvent(new EventOpInfo(Sensor.TYPE_GYROSCOPE, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_ORIENTATION:
			putEvent(new EventOpInfo(Sensor.TYPE_ORIENTATION, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_PRESSURE:
			putEvent(new EventOpInfo(Sensor.TYPE_PRESSURE, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_PROXIMITY:
			putEvent(new EventOpInfo(Sensor.TYPE_PROXIMITY, ON_SENSOR_CHANGED, event));
			break;

		case Sensor.TYPE_TEMPERATURE:
			putEvent(new EventOpInfo(Sensor.TYPE_TEMPERATURE, ON_SENSOR_CHANGED, event));
			break;
		}   
	}
}