package jaca.android.examples.sms.simple;

import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import android.os.Bundle;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;

public class ViewerArtifact extends GUIArtifact{

	private SmsViewer mViewer;
	
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mViewer = (SmsViewer) activity;
		defineObsProperty("viewer_state", "not_displayed");
	}
	
	@OPERATION public void addSMSToList(String source, String msg){
    	mViewer.append(source, msg);
    }

    @INTERNAL_OPERATION void onStart(){
    	ObsProperty obsProp = getObsProperty("viewer_state");
		obsProp.updateValue("displayed");
    }

	@INTERNAL_OPERATION void onStop(){
		ObsProperty obsProp = getObsProperty("viewer_state");
		obsProp.updateValue("not_displayed");
    }
}