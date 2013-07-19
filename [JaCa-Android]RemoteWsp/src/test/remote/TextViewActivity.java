package test.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * 
 * @author mguidi
 *
 */
public class TextViewActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.text_view);
	    
	    TextView txtView = (TextView) findViewById(R.id.txtView);
	    txtView.setMovementMethod(new ScrollingMovementMethod());
	    
	    Intent intent = getIntent();
	    String fileUrl = intent.getStringExtra("fileUrl");
	    
	    try {
	    	URL url = new URL(fileUrl);
	    	StringBuilder contents = new StringBuilder();
	    	BufferedReader input =  new BufferedReader(new InputStreamReader(url.openStream()));
	        try {
	          String line = null; 
	          while (( line = input.readLine()) != null){
	            contents.append(line);
	            contents.append(System.getProperty("line.separator"));
	          }
	        }
	        finally {
	          input.close();
	        }
			String agentSource = new String(contents.toString());
			txtView.setText(agentSource);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
