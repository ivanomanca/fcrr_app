package researchers_night.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SimpleAdapter;
import jaca.android.dev.JaCaListActivity;

/**
 * 
 * @author mguidi
 *
 */
public class EventsListActivity extends JaCaListActivity {
	
	private List<Map<String,?>> mData;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mData = new ArrayList<Map<String,?>>();
		setListAdapter(new SimpleAdapter(this, mData,
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
        
        runJaCaService(R.string.researchers_night_presenter_mas2j);
        createArtifact("EventsListActivity", EventsListActivityArtifact.class.getCanonicalName());
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000); // wait agent work
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stopJaCaService();
			}
		}).start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void addEvent(final String title) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Map<String, Object> temp = new HashMap<String, Object>();
				Intent intent = new Intent(EventsListActivity.this, EventActivity.class).putExtra("title", title);
		        
		        temp.put("title", title);
		        temp.put("intent", intent);
		        mData.add(temp);
		        EventsListActivity.this.onContentChanged();
		        
			}
		});
	}
}
