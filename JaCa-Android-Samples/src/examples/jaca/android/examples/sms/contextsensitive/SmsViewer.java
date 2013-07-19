package jaca.android.examples.sms.contextsensitive;


import jaca.android.R;
import jaca.android.dev.JaCaActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SmsViewer extends JaCaActivity {

	private Handler mHandler;
	private ArrayAdapter<String> mConversationArrayAdapter;
	private ListView mConversationView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    mHandler =  new Handler();
	    setContentView(R.layout.sms_viewer);
	    mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);
        
	    /* Run JaCa service */
	    runJaCaService(R.string.examples_sms_context_mas2j);
	    createArtifact("viewer", ViewerArtifact.class.getCanonicalName());
	}
	
	public void append(final String source, final String msg){
		mHandler.post(new Runnable() {
			
			public void run() {
				mConversationArrayAdapter.add(source+":  " + msg);
			}
		});
	}
}