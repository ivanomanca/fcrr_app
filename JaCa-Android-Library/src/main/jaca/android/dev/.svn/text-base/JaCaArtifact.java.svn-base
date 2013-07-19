package jaca.android.dev;

import jaca.android.JaCaService;
import android.content.Context;
import cartago.Artifact;

/**
 * Base class to extend in order to realise a JaCa-Android artifact.
 * This class provides the getApplicationContext that allow to get access to
 * the standard Android application context.
 * Developers are recommended extend this class to develop their Android-based
 * artifacts. 
 *  
 * @author mguidi
 *
 */
public abstract class JaCaArtifact extends Artifact{
	
	/**
	 * Return the Android application context
	 * 
	 * @return application context
	 */
	protected Context getApplicationContext(){
		return JaCaService.getInstance().getApplicationContext();
	}
}