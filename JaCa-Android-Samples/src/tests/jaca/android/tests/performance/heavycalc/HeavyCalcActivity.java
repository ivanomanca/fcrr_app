package jaca.android.tests.performance.heavycalc;

import jaca.android.dev.JaCaActivity;
import jaca.android.samples.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * 
 * @author mguidi
 *
 */
public class HeavyCalcActivity extends JaCaActivity {
	
	HeavyCalcActivity act;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.heavy_calc);

	    ((Button)findViewById(R.id.btnSumAndroid)).setOnClickListener(new InnerClickListener(this));
	    createArtifact("heavycalc-art", HeavyCalcArtifact.class.getCanonicalName());
    }
	
	private class InnerClickListener implements View.OnClickListener{
		
		private Activity act;
		public InnerClickListener(Activity act) {
			super();
			this.act = act;
		}

		
		public void onClick(View v) {
			TextView view = (TextView)act.getParent().findViewById(R.id.txtView);
			
			long millis = System.currentTimeMillis(); 
			int tmp = 0;
			for (int i=0;i<100000000;i++){
				tmp += i;
			}
			millis = System.currentTimeMillis() - millis;
			if(view!=null)
				view.append("[Android sum:] " + millis +" elapsed\n");
		}
	}
}