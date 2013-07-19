package ball.scene;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ToggleButton;
import jaca.android.dev.JaCaActivity;

/**
 * 
 * @author mguidi
 *
 */
public class BallSceneControllerActivity extends JaCaActivity {
	
	protected ToggleButton mBtnTogglePresenter;
	protected Button mBtnUp;
	protected Button mBtnDown;
	protected Button mBtnLeft;
	protected Button mBtnRight;
	protected CheckBox mCkbSensor;
	
	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ball_scene_controller_view);
		
		
		mBtnTogglePresenter = (ToggleButton) findViewById(R.id.btnTogglePresenter);
		mBtnUp = (Button) findViewById(R.id.btnUp);
		mBtnDown = (Button) findViewById(R.id.btnDown);
		mBtnLeft = (Button) findViewById(R.id.btnLeft);
		mBtnRight = (Button) findViewById(R.id.btnRight);
		mCkbSensor = (CheckBox) findViewById(R.id.ckbSensor);
		
		createArtifact("BallSceneControllerActivity", BallSceneControllerActivityArtifact.class.getCanonicalName());
		runJaCaService(R.string.ball_scene_mas2j);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000); // wait agent work
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stopJaCaService();
			}
		}).start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	public void setSensor(final boolean selected) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mCkbSensor.setChecked(selected);
			}
		});
	}
	
	public void setButtonControl(final boolean enable) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mBtnUp.setEnabled(enable);
				mBtnDown.setEnabled(enable);
				mBtnLeft.setEnabled(enable);
				mBtnRight.setEnabled(enable);
			}
		});
	}
	
	public void setTogglePresenter(final boolean selected) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mBtnTogglePresenter.setChecked(selected);
			}
		});
	}
	
	
	

}
