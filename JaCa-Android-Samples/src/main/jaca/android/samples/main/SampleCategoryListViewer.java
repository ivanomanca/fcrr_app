	package jaca.android.samples.main;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ListActivity used for showing in the smartphone screen the set 
 * of samples for one category.
 * @author asanti
 *
 */
public class SampleCategoryListViewer extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setListAdapter(new SimpleAdapter(this, getData(getIntent().getStringExtra("category")),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
    }
    
	protected List<Map<String,?>> getData(String category) {
        List<Map<String,?>> myData = new ArrayList<Map<String,?>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory(category);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;
        
        int len = list.size();
        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;
            addItem(myData, label, activityIntent(
            		info.activityInfo.applicationInfo.packageName,
                    info.activityInfo.name));
        }
        Collections.sort(myData, sDisplayNameComparator);
        return myData;
    }
	
	private final static Comparator<Map<String,?>> sDisplayNameComparator = new Comparator<Map<String,?>>() {
        private final Collator   collator = Collator.getInstance();

        public int compare(Map<String,?> map1, Map<String,?> map2) {
            return collator.compare(map1.get("title"), map2.get("title"));
        }
    };
	
	protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }
	
	protected void addItem(List<Map<String,?>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<?,?> map = (Map<?,?>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }	
}
