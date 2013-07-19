package jaca.android.tests.notification;

import jaca.android.samples.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


/**
 * 
 * @author mguidi
 *
 */
public class Viewer extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Intent intent = getIntent();
	    
	    setContentView(R.layout.notification_viewer);
	    TextView txtViewer = (TextView) findViewById(R.id.txtViewer);
	    String contextText = intent.getStringExtra("contentText");
	    String contextTitle = intent.getStringExtra("contentTitle");
	    setTitle(contextTitle);
	    txtViewer.setText(contextText);
	}
}
