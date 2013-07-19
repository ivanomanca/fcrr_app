package jaca.android.dev;

import java.util.HashMap;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

/**
 * Base artifact used for managing the device sensors. 
 * This can be seen as an "abstract" artifact.
 * Developers must extends this class for defining their own
 * artifacts able to manage sensors events. This class provides
 * the name of all the possible observable properties related to sensors.
 * 
 * <p> #### Observable Properties #####
 *  <ul>
 *  	<li>{@code is_monitoring(Value). Value = {true, false}}</li>
 *  </ul>
 * </p>
 * 
 *
 * @author asanti
 *
 */
public abstract class SensorManagerArtifact extends JaCaArtifact{
	
	private SensorEventsFetcher mEventFetcher;

	/**
	 * Name of the internal operation to be called for handling the event
	 * coming from the {@code onSensorChanged} listener 
	 */
	protected static final String OP_ON_SENSOR_CHANGED = "onSensorChanged";
	
	/**
	 * Name of the internal operation to be called for handling the event
	 * coming from the {@code onAccuracyChanged} listener 
	 */
	protected static final String OP_ON_ACCURACY_CHANGED = "onAccuracyChanged";

	/**
	 * Observable property indicating if the SensorManager is currently
	 * monitoring the designed sensor/s or not
	 */
	protected static final String IS_MONITORING ="is_monitoring";

	/**
	 * Name of the observable property holding the value of the accelerometer.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the accelerometer (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_ACCELEROMETER = "sensor_accelerometer";
	
	/**
	 * Name of the observable property holding the value of the geomagnetic sensor.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the geomagnetic sensor (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_GEOMAG = "sensor_geomag";
	
	/**
	 * Name of the observable property holding the value of the orientation sensor.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the orientation sensor (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_ORIENTATION = "sensor_orientation";

	/**
	 * Name of the observable property holding the value of the orientation sensor.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the orientation sensor (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_PRESSURE = "sensor_pressure";

	/**
	 * Name of the observable property holding the value of the pressure sensor.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the pressure sensor (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_TEMPERATURE = "sensor_temperature";

	/**
	 * Name of the observable property holding the value of the proximity sensor.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the proximity sensor (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_PROXIMITY = "sensor_proximity";

	/**
	 * Name of the observable property holding the value of the gyroscope sensor.
	 * This observable property is meant to be defined, whit this name, by each
	 * artifact that manage the gyroscope sensor (extending the SensorManagerArtifact)
	 */
	public static String SENSOR_GYROSCOPE = "sensor_gyroscope";
	
	/*
	 * Key: The Integer that identify the sensor that generates the event.
	 * Value: An HashMap<String,String> mapping for each kind of event
	 * (each sensor can have different kind of event onSensorChanged, onAccuracyChanged).
	 * to the appropriate listener 
	 */
	private HashMap<Integer, HashMap<String, String>> mEvToOpLinks;
	private boolean mStopped;
	
	/**
	 * 
	 * @param delay The desired sensor delay must be one of {@link SensorManager}.SENSOR_DELAY_*
	 * @param sensorsTypes The list of sensors to monitor whit this artifact (a set of {@link Sensor}.TYPE_*)
	 * @throws Exception
	 */
	@OPERATION public void init(Integer delay, Integer...sensorsTypes) throws Exception{

		SensorManager sensorManager = (SensorManager)getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager == null) {
			throw new ExceptionInInitializerError("Sensors not supported");
		}
		/*
		 * SensorManager present, sensor supported. 
		 * We now check that the device support each sensorType provided in input
		 */
		else{
			Sensor sensor;
			boolean allSensorSupported = true;
			for (int sensorType : sensorsTypes){
				sensor = sensorManager.getDefaultSensor(sensorType);
				if(sensor==null){
					allSensorSupported = false;
					break;
				}
			}
			//All sensorType supported, we can proceed with the artifact initialisation
			if(allSensorSupported){
				mEventFetcher = new SensorEventsFetcher(sensorManager, delay, sensorsTypes);
				execInternalOp("fetchSensorsEvents");
				mStopped = false;
				mEvToOpLinks = new HashMap<Integer, HashMap<String, String>>();
				defineObsProperty(IS_MONITORING, false);
			}
			//Some sensor type is not supported, exception
			else throw new ExceptionInInitializerError("Some of the sensor selected are not supported");
		}
	}
	
	@INTERNAL_OPERATION protected void dispose() {
		super.dispose();
		if(mEventFetcher!=null)mEventFetcher.stopMonitoring();
	}

	@INTERNAL_OPERATION protected void linkOnSensorChangedEventToOp(Integer sensor, String opName) {
    	linkEventToOp(sensor, SensorEventsFetcher.ON_SENSOR_CHANGED, opName);
    }

	@INTERNAL_OPERATION protected void linkOnAccuracyChangedEventToOp(Integer sensor, String opName) {
		linkEventToOp(sensor, SensorEventsFetcher.ON_ACCURACY_CHANGED, opName);
    }

	/**
	 * Operation that starts the monitoring of the sensors
	 */
	@OPERATION void startMonitoring(){
		getObsProperty(IS_MONITORING).updateValue(true);
		mEventFetcher.startMonitoring();
	}

	/**
	 * Operation that stops the monitoring of the sensors
	 */
	@OPERATION void stopMonitoring(){
		getObsProperty(IS_MONITORING).updateValue(false);
		mEventFetcher.stopMonitoring();
	}
	
	/**
	 * Internal operation used for fetching sensors events
	 */
	@INTERNAL_OPERATION protected void fetchSensorsEvents() {
		while (!mStopped) {
    	
			await(mEventFetcher);
			EventOpInfo eventOp = mEventFetcher.getCurrentEventFetched();

			if (eventOp!=null) {
    		
	    		HashMap<String,String> map = mEvToOpLinks.get(eventOp.getSource());
	    		
    			//Invocation of the operation linked to the event
    			if (map!=null) {
	    			String opName = map.get(eventOp.getMethodName());
	    			if (opName!=null) execInternalOp(opName, eventOp.getParam());
	    		}
    		} else {
    			mStopped = true;
    		}
    	}
    }

	/**
	 * Utility method for linking sensor event to operation
	 * @param source
	 * @param eventListener
	 * @param opName
	 */
	protected void linkEventToOp(Integer source, String eventListener, String opName) {
		HashMap<String,String> map = mEvToOpLinks.get(source);
		if (map==null) {
			map = new HashMap<String,String>();
			mEvToOpLinks.put(source, map);
		}
		map.put(eventListener, opName);
	}
	
	/**
	 * Return the HashMap mapping sensors events to the artifacts operations
	 * responsible of their management 
	 * @return
	 */
	public HashMap<Integer, HashMap<String, String>> getEvToOpLinks() {
		return mEvToOpLinks;
	}
	
	/**
	 * Default internal operation called (once linked by {@link SensorManagerArtifact#linkEventToOp(Integer, String, String)} 
	 * by the {@link SensorManagerArtifact#fetchSensorsEvents()} for handling the sensor
	 * event indicating changes in the sensor value
	 * @param event 
	 */
	@INTERNAL_OPERATION public abstract void onSensorChanged(SensorEvent event);
	
	/**
	 * Default internal operation called (once linked by {@link SensorManagerArtifact#linkEventToOp(Integer, String, String)} 
	 * by the {@link SensorManagerArtifact#fetchSensorsEvents()} for handling the sensor
	 * event indicating changes in the sensor accuracy
	 * @param arg0
	 * @param arg1
	 */
	@INTERNAL_OPERATION public abstract void onAccuracyChanged(Sensor arg0, int arg1);	
}