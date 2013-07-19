package jaca.android.fcrr;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import cartago.ARTIFACT_INFO;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OUTPORT;
import cartago.ObsProperty;
import cartago.OpFeedbackParam;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import jaca.android.dev.IJaCaActivity;
import jaca.android.dev.ListActivityArtifact;
import jaca.android.fcrr.util.JsonParam;

@ARTIFACT_INFO(
		outports = {
			@OUTPORT(name = "out-1")
		}
) public class DTripListGUI extends ListActivityArtifact {

	private DTripListActivity activityRef;
	private OpFeedbackParam<ArrayList<HashMap<String, String>>> list;
	
	@Override
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		// ricavo l'attività associata alle risorse
		activityRef = (DTripListActivity)activity;
		// definisco una proprietà osservabile (visualizzazione GUI)
		defineObsProperty("gui_state", "not_displayed");
		// tap on listItem
		linkOnListItemClickEventToOp("onListItemClick");	
	}
	
	@OPERATION void updateView(ArrayList<HashMap<String, String>> list){
		// riempio la lista con il metodo dell'activity
	    activityRef.fillListView(list);
	}
	
	@INTERNAL_OPERATION void onListItemClick(ListView l, View v, int position, long id) {
		//log("position = " + position + "; id = " + id + ";");
		//log("ViewID = "+v.getId());
		//log("ItemSelected = "+l.getItemIdAtPosition(position));
		//log("AdapterObjectClass = "+l.getAdapter().getClass());
		
		SimpleAdapter adapter = (SimpleAdapter)l.getAdapter();
		if (adapter.getItem(position) instanceof HashMap<?, ?>) {
			HashMap<?, ?> hm = (HashMap<?, ?>) adapter.getItem(position);
			// incapsulo in un oggetto json
			
			JSONObject joTripID = new JSONObject();
			try {
				joTripID.put(JsonParam.Trip.id, hm.get(JsonParam.Trip.id));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			// signal to agent
			signal("trip_info_gui", joTripID);
		}else failed("unable-to-load-view");
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
