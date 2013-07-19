package researchers_night.presenter;

import java.util.Map;

import cartago.INTERNAL_OPERATION;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import jaca.android.dev.JaCaListActivity;
import jaca.android.dev.ListActivityArtifact;

/**
 * 
 * @author mguidi
 *
 */
public class PresentationActivityArtifact extends ListActivityArtifact {

	
	protected void init(JaCaListActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		linkOnDestroyEventToOp("onDestroy");
		linkOnListItemClickEventToOp("onListItemClick");
		
	}
	
	@INTERNAL_OPERATION void onDestroy() {
    	signal("on_destroy");
    }
	
	@INTERNAL_OPERATION void onListItemClick(ListView l, View v, int position, long id) {
		Map<?,?> map = (Map<?,?>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        signal("item_selected", intent);
	}
}
