package jaca.android.tests.performance.reactivity;

import jaca.android.dev.SensorManagerArtifact;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;


public class TestAccelerometerArtifact extends SensorManagerArtifact{

	@OPERATION public void init() throws Exception{
		super.init(SensorManager.SENSOR_DELAY_NORMAL, Sensor.TYPE_ACCELEROMETER);
		linkOnSensorChangedEventToOp(Sensor.TYPE_ACCELEROMETER, SensorManagerArtifact.OP_ON_SENSOR_CHANGED);
	}

	@Override	
	@INTERNAL_OPERATION public void onSensorChanged(SensorEvent event) {
		
		switch(event.sensor.getType()){
			
			case Sensor.TYPE_ACCELEROMETER:
				signal("accelerometer_update", System.currentTimeMillis());
			break;
		}   
	}

	@Override
	@INTERNAL_OPERATION public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
}