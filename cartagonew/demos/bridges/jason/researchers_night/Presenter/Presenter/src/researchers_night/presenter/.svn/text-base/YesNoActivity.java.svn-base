package researchers_night.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author mguidi
 *
 */
public class YesNoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.yesno_view);
		
		String message = getIntent().getStringExtra("message");
		setTitle(message);
		
		Button btnYes = (Button) findViewById(R.id.btnYes);
		btnYes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
		
		Button btnNo = (Button) findViewById(R.id.btnNo);
		btnNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		
	}

}
