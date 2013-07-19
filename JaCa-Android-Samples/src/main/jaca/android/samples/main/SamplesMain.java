package jaca.android.samples.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Main GUI of the JaCa-Android-Samples application. This GUI will
 * propose the user the possibility to choose between tests, samples and 
 * performance test. Once the user select the category of samples he wants
 * to work with the {@link SampleCategoryListViewer} is showed
 * @author mguidi
 *
 */
public class SamplesMain extends ListActivity {

	public static final String EXAMPLE_ENTRY_TITLE = "JaCa-Android Examples";
	public static final String TEST_ENTRY_TITLE = "JaCa-Android Tests";
	public static final String PERFORMANCE_TEST_ENTRY_TITLE = "JaCa-Android Performance Tests";
	/*
	 * Intent categories
	 */
	public static final String EXAMPLE_CATEGORY = "jaca.android.intent.category.EXAMPLE";		
	public static final String TEST_CATEGORY = "jaca.android.intent.category.TEST";
	public static final String PERFORMANCE_TEST_CATEGORY = "jaca.android.intent.category.PERFORMANCE_TEST";
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setListAdapter(new SimpleAdapter(this, getJaCaSamplesCategories(),
                android.R.layout.simple_list_item_1, new String[] { "title" },
                new int[] { android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
    }
    
	protected List<Map<String,?>> getJaCaSamplesCategories() {
		List<Map<String,?>> myData = new ArrayList<Map<String,?>>();
        Map<String, Object> temp = new HashMap<String, Object>();
    
        temp.put("title", TEST_ENTRY_TITLE);
        myData.add(temp);

        temp = new HashMap<String, Object>();
        temp.put("title", EXAMPLE_ENTRY_TITLE);
        myData.add(temp);
        
        temp = new HashMap<String, Object>();
        temp.put("title", PERFORMANCE_TEST_ENTRY_TITLE);
        
        myData.add(temp);
        return myData;
	}		
	

	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<?,?> map = (Map<?,?>) l.getItemAtPosition(position);
        
        if(position==0){
        	Intent intent = new Intent(this, SampleCategoryListViewer.class);
        	intent.putExtra("category", TEST_CATEGORY);
        	startActivity(intent);
        }
        else if(position==1){
        	Intent intent = new Intent(this, SampleCategoryListViewer.class);
        	intent.putExtra("category", EXAMPLE_CATEGORY);
        	startActivity(intent);
        }
        else if(position==2){
        	Intent intent = new Intent(this, SampleCategoryListViewer.class);
        	intent.putExtra("category", PERFORMANCE_TEST_CATEGORY);
        	startActivity(intent);
        }
    }
}