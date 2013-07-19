package slide.show;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.Toast;
import jaca.android.dev.JaCaActivity;

/**
 * 
 * @author mguidi
 *
 */
public class SlideShowActivity extends JaCaActivity {

	protected ImageView mImgSlide;
	
	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.slide_show);
		
		mImgSlide = (ImageView) findViewById(R.id.imgSlide);
		
		createArtifact("SlideShowActivity", SlideShowActivityArtifact.class.getCanonicalName());
		runJaCaService(R.string.slide_show_mas2j);
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
	
	public void setImage(final Bitmap img) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mImgSlide.setImageBitmap(img);	
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.slide_show_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void showMessage(final String msg) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
				toast.show();
			}
		});
	}
	
			
}
