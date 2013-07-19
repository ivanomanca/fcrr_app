package researchers_night.presenter;

import java.util.Map;

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
import android.widget.ListView;
import jaca.android.dev.JaCaListActivity;
import jaca.android.dev.ListActivityArtifact;

/**
 * 
 * @author mguidi
 *
 */
@ARTIFACT_INFO(
	outports = {
		@OUTPORT(name = "out-1")
	}
) public class EventsListActivityArtifact extends ListActivityArtifact {
	
	private EventsListActivity mEventsListActivity;
	
	protected void init(JaCaListActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mEventsListActivity = (EventsListActivity) activity;
		linkOnListItemClickEventToOp("onListItemClick");
		linkOnDestroyEventToOp("onDestroy");
		linkOnOptionsItemSelectedToOp("optionsItemSelected");
		linkOnActivityResultEventToOp("onActivityResult");
		
	}

	@OPERATION void addEvent(String title) {
		mEventsListActivity.addEvent(title);
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
			mEventsListActivity.finish();
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
	
	@INTERNAL_OPERATION void onListItemClick(ListView l, View v, int position, long id) {
		Map<?,?> map = (Map<?,?>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        String eventBoardName = intent.getStringExtra("title");
        signal("item_selected", intent, eventBoardName);
	}	

}
