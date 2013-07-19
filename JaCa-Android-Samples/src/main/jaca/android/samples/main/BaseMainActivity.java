package jaca.android.samples.main;

import jaca.android.dev.JaCaTabActivity;
import jaca.android.samples.main.R;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;


/**
 * Standard base JaCaTabActivity each test must extend
 * @author mguidi
 *
 */
public class BaseMainActivity extends JaCaTabActivity {

	private TextView mTxtView;
	private OutputStreamAdapter mAdapter;
    private final Handler mHandler = new Handler();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_view);
	    
	    mTxtView = (TextView) findViewById(R.id.txtView);
	    mTxtView.setMovementMethod(new ScrollingMovementMethod());
	    mAdapter = new OutputStreamAdapter(this);
	    mAdapter.setAsDefaultOut();
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mAdapter.restoreOriginalOut();
	}
    
    public void append(String s) {
		mHandler.post(new MyRunnable(s));
	}
	    
    class MyRunnable implements Runnable {

    	private String mString;
    	
    	public MyRunnable(String s) {
    		mString = s;
    	}
    	
		
		public void run() {
			mTxtView.append(mString);
			mTxtView.scrollTo(0, mTxtView.getLineHeight()*mTxtView.getLineCount()-mTxtView.getHeight());
		}
    }
}
