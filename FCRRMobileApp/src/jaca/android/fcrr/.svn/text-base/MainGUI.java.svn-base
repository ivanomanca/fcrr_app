package jaca.android.fcrr;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.ObsProperty;
import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainGUI extends GUIArtifact {
	
	private Button driverButton;
	private Button riderButton;
	private MainActivity mainActivity;

	protected void init(IJaCaActivity activity, Bundle savedInstanceState){
		super.init(activity, savedInstanceState);
		
		// ricavo l'attività associata
		mainActivity = (MainActivity)activity;
		
		// definisco una proprietà osservabile (visualizzazione GUI)
		defineObsProperty("gui_state", "not_displayed");
		
		// ricavo gli elementi di view
		driverButton = (Button)mainActivity.findViewById(R.id.driver_button);
		riderButton = (Button)mainActivity.findViewById(R.id.rider_button);
		
		// pressione pulsanti - linkingOnClickToOp
		linkOnClickEventToOp(driverButton, "onClick");
		linkOnClickEventToOp(riderButton, "onClick");
	}
	
	@OPERATION void updateView(){
		// aggiorno la main gui
		mainActivity.setupView();
	}
	
	@INTERNAL_OPERATION void onClick(View v) {
		switch (v.getId()) {
		case R.id.driver_button:
			signal("driver_button_click");
			break;
		case R.id.rider_button:
			signal("rider_button_click");
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
