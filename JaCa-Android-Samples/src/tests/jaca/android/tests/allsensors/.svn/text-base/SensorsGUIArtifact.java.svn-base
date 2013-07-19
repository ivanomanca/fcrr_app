package jaca.android.tests.allsensors;

import jaca.android.dev.GUIArtifact;
import android.os.Bundle;
import cartago.OPERATION;


public class SensorsGUIArtifact extends GUIArtifact{
	
	private SensorsActivity mActivity;
	
	public void init(SensorsActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mActivity = activity;
	}
	
	@OPERATION public void updateAccSensorsInfo(double x, double y, double z){
		mActivity.updateAccSensorsInfo(x, y, z);
	}

	@OPERATION public void updateGeomagSensorsInfo(double x, double y ,double z){
		mActivity.updateGeomagSensorsInfo(x, y, z);
	}

	@OPERATION public void updateOrientationSensorsInfo(double x, double y, double z){
		mActivity.updateOrientationSensorsInfo(x, y, z);
	}
}