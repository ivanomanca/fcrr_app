package researchers_night.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

import jaca.android.dev.ActivityArtifact;
import jaca.android.dev.JaCaActivity;

/**
 * 
 * @author mguidi
 *
 */
public class EventActivityArtifact extends ActivityArtifact{

	private EventActivity mActivity;
	
	protected void init(JaCaActivity activity, Bundle savedInstanceState){
		super.init(activity, savedInstanceState);
		mActivity = (EventActivity) activity;
		
		linkOnActivityResultEventToOp("onActivityResult");
		linkOnClickEventToOp(mActivity.mBtnInc, "onClick");
		linkOnDestroyEventToOp("onDestroy");
		
	}
	
	/******************************************* INTERNAL OPERATION *****************************************/
	
	@INTERNAL_OPERATION void onClick(View view) {
    	signal("start");
    }
	
	@INTERNAL_OPERATION void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
			signal("forceStart");
		}
	}
	
	@INTERNAL_OPERATION void onDestroy() {
    	signal("on_destroy");
    }
	
	/************************************************ OPERATION *********************************************/
	
	@OPERATION void setParticipants(int val) {
		mActivity.setParticipants(""+val);
	}
	
	@OPERATION void setStatus(String status) {
		mActivity.setStatus(status);
	}
	
	@OPERATION void askConfirm() {
		Intent intent = new Intent(mActivity, YesNoActivity.class);
		String title = mActivity.getResources().getString(R.string.confirm_message);
		intent.putExtra("message", title);
		mActivity.startActivityForResult(intent, 0);
	}
		
	/*
	@OPERATION void askConfirm2() {	
		Handler handler = new Handler(mActivity.getMainLooper());
		handler.post(new Runnable() {
			@Override
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
				builder.setMessage("Are you sure you want to start event?")
				       .setCancelable(false)
				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                signal("forceStart");
				           }
				       })
				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}
	*/
	
}
