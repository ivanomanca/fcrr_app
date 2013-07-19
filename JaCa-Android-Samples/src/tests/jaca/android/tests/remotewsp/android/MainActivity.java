package jaca.android.tests.remotewsp.android;

import jaca.android.samples.main.BaseMainActivity;
import jaca.android.samples.main.R;
import jaca.android.samples.main.TextViewActivity;
import jaca.android.tests.remotewsp.common.IMainActivity;
import jaca.android.tests.remotewsp.common.RemoteGUI;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


/**
 * 
 * @author mguidi
 *
 */
public class MainActivity extends BaseMainActivity implements IMainActivity{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, TextViewActivity.class).putExtra("fileUrl", MainActivity.class.getResource("agent.asl").toString());

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("agent").setIndicator("Agent").setContent(intent);
	    tabHost.addTab(spec);
	    
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, TextViewActivity.class).putExtra("fileUrl", MainActivity.class.getResource("project.mas2j").toString());

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("mas2j").setIndicator("Mas2j").setContent(intent);
	    tabHost.addTab(spec);
	    
	    runJaCaService(R.string.test_remotewsp_android_mas2j);
	    
	    createArtifact("remote-gui", RemoteGUI.class.getCanonicalName());
	}
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	stopJaCaService();
    }
}