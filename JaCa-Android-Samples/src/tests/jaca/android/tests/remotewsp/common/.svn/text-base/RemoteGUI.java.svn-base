package jaca.android.tests.remotewsp.common;

import jaca.android.dev.ActivityEventsFetcher;
import jaca.android.dev.GUIArtifact;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cartago.ARTIFACT_INFO;
import cartago.INTERNAL_OPERATION;
import cartago.OUTPORT;
import cartago.OperationException;

@ARTIFACT_INFO(
	outports = {@OUTPORT(name = "out-1")}

	)
	
public class RemoteGUI extends GUIArtifact{

	private IMainActivity mActivity;
	
	protected void init(IMainActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mActivity = activity;
		linkOnActivityResultEventToOp(ActivityEventsFetcher.ON_ACTIVITY_RESULT);	
	}
	
	@INTERNAL_OPERATION void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && requestCode == 0) {
			String address = data.getStringExtra("address");
			boolean remember = data.getBooleanExtra("remember", false);
			try {
				execLinkedOp("out-1", "setAddressConfig", address, remember);
			} catch (OperationException e) {
				e.printStackTrace();
			}
			signal("address_setted");
		} else {
			((Activity)mActivity).finish();
		}
	}
}