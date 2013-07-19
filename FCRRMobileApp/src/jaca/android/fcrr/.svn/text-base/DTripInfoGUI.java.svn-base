package jaca.android.fcrr;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import cartago.ARTIFACT_INFO;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OUTPORT;
import cartago.ObsProperty;
import cartago.OpFeedbackParam;
import jaca.android.dev.IJaCaActivity;
import jaca.android.dev.ListActivityArtifact;
import jaca.android.fcrr.util.JsonParam;


@ARTIFACT_INFO(
		outports = {
			@OUTPORT(name = "out-1")
		}
) public class DTripInfoGUI extends ListActivityArtifact {

	private DTripInfoActivity activityRef;
	private OpFeedbackParam<ArrayList<HashMap<String, String>>> riders;
	private OpFeedbackParam<ArrayList<HashMap<String, String>>> infoTrip;
	private View startTripButton;
	private View deleteTripButton;
	private JSONObject joTripID;

	@Override
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		
		// ricavo l'attività associata alle risorse
		activityRef = (DTripInfoActivity)activity;
		
		// definisco una proprietà osservabile (visualizzazione GUI)
		defineObsProperty("gui_state", "not_displayed");
		
		startTripButton = activityRef.findViewById(R.id.startTrip);
		deleteTripButton = activityRef.findViewById(R.id.deleteTrip);
		
		linkOnClickEventToOp(startTripButton, "onClick");
		linkOnClickEventToOp(deleteTripButton, "onClick");
	}
	
	@OPERATION void updateView(	JSONObject tripID,
								ArrayList<HashMap<String, String>> tripInfo,
								ArrayList<HashMap<String, String>> riders){
		// salvo l'identificativo del trip
		joTripID = tripID;
		
		// riempio la lista con il metodo dell'activity
	    activityRef.fillView(riders, tripInfo);
	}

	@INTERNAL_OPERATION void onClick(View v) {
		switch (v.getId()) {
		case R.id.startTrip:
			signal("start", joTripID);
			break;
		case R.id.deleteTrip:
			signal("delete", joTripID);
		default:
			break;
		}
	}
	
	@INTERNAL_OPERATION void onStart(){
    	ObsProperty obsProp = getObsProperty("gui_state");
		obsProp.updateValue("displayed");
    }

	@INTERNAL_OPERATION void onStop(){
		ObsProperty obsProp = getObsProperty("gui_state");
		obsProp.updateValue("not_displayed");
    }	
}
