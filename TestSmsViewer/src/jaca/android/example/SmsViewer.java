package jaca.android.example;

import jaca.android.dev.JaCaActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SmsViewer extends JaCaActivity {

    private Handler mHandler;
    private ArrayAdapter mConversationArrayAdapter;
    private ListView mConversationView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_viewer);
	    mConversationArrayAdapter = new ArrayAdapter(this, R.layout.message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);
        mHandler = new Handler();
        runJaCaService(R.string.mas2j);
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