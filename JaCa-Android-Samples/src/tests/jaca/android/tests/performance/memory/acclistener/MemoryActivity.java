package jaca.android.tests.performance.memory.acclistener;

import jaca.android.dev.JaCaActivity;
import jaca.android.samples.main.R;
import android.os.Bundle;


/**
 * 
 * @author mguidi
 *
 */
public class MemoryActivity extends JaCaActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.memory_acclistener);
	    createArtifact("memory-art", MemoryArtifact.class.getCanonicalName());
    }
}