package jaca.android.tests.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import jaca.android.R;
import jaca.android.dev.GUIArtifact;
import jaca.android.dev.JaCaActivity;

/**
 * 
 * @author mguidi
 *
 */
public class HelloWorldGUIArtifact extends GUIArtifact{

	protected void init(JaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		linkOnReStartEventToOp("onReStart");
		linkOnStartEventToOp("onStart");
		linkOnResumeEventToOp("onResume");
		linkOnPauseEventToOp("onPause");
		linkOnStopEventToOp("onStop");
		linkOnDestroyEventToOp("onDestroy");
		
		Button btnInc = (Button) activity.findViewById(R.id.btnPrint);
		linkOnClickEventToOp(btnInc, "onClick");
		
	}
	
	@OPERATION void print() {
		System.out.println("Hello World from: "+getId().getName());
	}
	
	@INTERNAL_OPERATION void onClick(View view) {
    	signal(getId().getName());
    }
	
	@INTERNAL_OPERATION void onReStart() {
    	signal("onReStart");
    }
    
    @INTERNAL_OPERATION void onStart() {
    	signal("onStart");
    }
    
    @INTERNAL_OPERATION void onResume() {
    	signal("onResume");
    }
    
    @INTERNAL_OPERATION void onPause() {
    	signal("onPause");
    }
    
    @INTERNAL_OPERATION void onStop() {
    	signal("onStop");
    }
    
    @INTERNAL_OPERATION void onDestroy() {
    	signal("onDestroy");
    }
}