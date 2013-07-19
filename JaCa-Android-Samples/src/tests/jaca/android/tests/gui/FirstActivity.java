package jaca.android.tests.gui;

import jaca.android.dev.JaCaActivity;
import jaca.android.samples.main.R;
import android.os.Bundle;


/**
 * 
 * @author mguidi
 *
 */
public class FirstActivity extends JaCaActivity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity);
	
	    createArtifact("first_activity", HelloWorldGUIArtifact.class.getCanonicalName());
    }

}
