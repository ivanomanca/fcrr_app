package ball.scene;

import cartago.ARTIFACT_INFO;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.OUTPORT;
import cartago.OperationException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import jaca.android.dev.ActivityArtifact;
import jaca.android.dev.JaCaActivity;

/**
 * 
 * @author mguidi
 *
 */
@ARTIFACT_INFO(
	outports = {
		@OUTPORT(name = "out-1")
	}
) public class BallSceneControllerActivityArtifact extends ActivityArtifact {

	private BallSceneControllerActivity mActivity;
	
	protected void init(JaCaActivity activity, Bundle arg1) {
		super.init(activity, arg1);
		
		mActivity = (BallSceneControllerActivity) activity;
		
		linkOnClickEventToOp(mActivity.mBtnTogglePresenter, "onClick");
		linkOnClickEventToOp(mActivity.mBtnUp, "onClick");
		linkOnClickEventToOp(mActivity.mBtnDown, "onClick");
		linkOnClickEventToOp(mActivity.mBtnLeft, "onClick");
		linkOnClickEventToOp(mActivity.mBtnRight, "onClick");
		linkOnClickEventToOp(mActivity.mCkbSensor, "onClick");
		
		linkOnActivityResultEventToOp("onActivityResult");
		linkOnOptionsItemSelectedToOp("optionsItemSelected");
		
	}
	
	@INTERNAL_OPERATION void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && requestCode == 0) {
			String address = data.getStringExtra("address");
			boolean remember = data.getBooleanExtra("remember", false);
			try {
				execLinkedOp("out-1","setAddressConfig", address, remember);
			} catch (OperationException e) {
				e.printStackTrace();
			}
			signal("ok");
			
		} else {
			mActivity.finish();
		}
	}
	
	@INTERNAL_OPERATION void optionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.resetAddress:
			try {
				execLinkedOp("out-1","resetAddress");
			} catch (OperationException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@INTERNAL_OPERATION void onClick(View view) {
    	if (view == mActivity.mBtnTogglePresenter) {
    		signal("togglePresenter", mActivity.mBtnTogglePresenter.isChecked());
			
		} else if (view == mActivity.mBtnUp) {
			signal("direction","up");
			
		} else if (view == mActivity.mBtnDown) {
			signal("direction","down");
			
		} else if (view == mActivity.mBtnLeft) {
			signal("direction","left");
			
		} else if (view == mActivity.mBtnRight) {
			signal("direction","right");
			
		} else if (view == mActivity.mCkbSensor) {
			mActivity.setButtonControl(!mActivity.mCkbSensor.isChecked());
			signal("sensor_commander", mActivity.mCkbSensor.isChecked());
		}
    }
	
	@OPERATION void setSensor(boolean selected) {
		mActivity.setSensor(selected);
		mActivity.setButtonControl(!selected);
	}
	
	@OPERATION void setTogglePresenter(String selected) {
		if (selected.equals("on")) {
			mActivity.setTogglePresenter(true);
		} else {
			mActivity.setTogglePresenter(false);
		}
		
	}
	
}
