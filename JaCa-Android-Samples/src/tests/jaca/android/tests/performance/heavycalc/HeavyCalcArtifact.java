package jaca.android.tests.performance.heavycalc;

import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import jaca.android.samples.main.R;
import android.os.Bundle;
import android.view.View;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;


public class HeavyCalcArtifact extends GUIArtifact {
	
	private HeavyCalcActivity activity;
	@OPERATION public void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		this.activity = (HeavyCalcActivity) activity; 
		linkOnClickEventToOp(this.activity.findViewById(R.id.btnSumJaCa), "onClick");
	}
	
	@INTERNAL_OPERATION public void onClick(View view) {
		signal("doSum_click");
	}

	@OPERATION public void computeSum(){
		int tmp = 0;
		for (int i=0;i<100000000;i++){
			tmp += i;
		}
	}
}