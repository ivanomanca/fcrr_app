package researchers_night.presenter.config;

import researchers_night.presenter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * 
 * @author mguidi
 *
 */
public class AddressActivity extends Activity implements OnClickListener {

	private TextView mTxtAddress;
	private Button mBtnOk;
	private Button mBtnCancel;
	private CheckBox mCkbRemember;
	
	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.address_view);
		
		mTxtAddress = (TextView) findViewById(R.id.txtAddress);
		mBtnOk = (Button) findViewById(R.id.btnOk);
		mBtnCancel = (Button) findViewById(R.id.btnCancel);
		mCkbRemember = (CheckBox) findViewById(R.id.ckbRemember);
		
		mBtnOk.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view == mBtnOk) {
			String address = mTxtAddress.getText().toString();
			if (!address.equals("")) {
				boolean remember = mCkbRemember.isChecked();
				
				Intent data = new Intent();
				data.putExtra("address", address);
				data.putExtra("remember", remember);
				setResult(RESULT_OK, data);
				finish();
			}
			
		} else if (view == mBtnCancel) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
		
}
