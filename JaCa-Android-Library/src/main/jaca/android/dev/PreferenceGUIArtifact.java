package jaca.android.dev;

import java.util.HashMap;

import cartago.INTERNAL_OPERATION;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Developers must extend this class to define an artifact usable
 * from agents for observe and control Android view interface that
 * also allows the user to interact with application preferences.
 * In particular, for working with application preferences,
 * the IJaCaActivity wrapped by the PreferenceGUIArtifact MUST be
 * an extension of the JaCaPreferenceActivity. The events related
 * to preferences changes are fetched by the 
 * {@link PreferenceGUIArtifact#fetchPreferenceEvents()} internal 
 * operation using a PreferenceChangeEventsFetcher
 * 
 * @author asanti
 *
 */
public class PreferenceGUIArtifact extends GUIArtifact {

	protected PreferenceChangeEventsFetcher mPrefEventFetcher;
	protected PreferenceManager mPrefMgr;
	
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		mPrefEventFetcher = new PreferenceChangeEventsFetcher();
		mPrefMgr = ((PreferenceActivity)activity).getPreferenceManager();
		execInternalOp("fetchPreferenceEvents");
    }
	
	/**
	 * Internal operation for fetching PreferenceChanges events 
	 */
	@INTERNAL_OPERATION void fetchPreferenceEvents() {
    	while (!mStopped) {
    		await(mPrefEventFetcher);
    		EventOpInfo eventOp = mPrefEventFetcher.getCurrentEventFetched();
    		if (eventOp!=null) {
    			HashMap<String,String> map = mEvToOpLinks.get(eventOp.getSource());
    			//Invocation of the operation linked to the event
    			if (map!=null) {
	    			String opName = map.get(eventOp.getMethodName());
	    			if (opName!=null) execInternalOp(opName, eventOp.getParam());
	    		}
    		} else {
    			mStopped = true;
    		}
    	}
    }

	/**
     * Link OnPreferenceChange event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void OnPreferenceChange(Preference preference, Object newValue)
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnPreferenceChangeEvToOp(String preference, String opName) {
    	Preference pref = mPrefMgr.findPreference(preference);
    	if(pref!=null){
    		HashMap<String,String> map = mEvToOpLinks.get(pref);
    		if (map==null) {
    			map = new HashMap<String,String>();
    			mEvToOpLinks.put(pref, map);
    		}
    		map.put(PreferenceChangeEventsFetcher.ON_PREFERENCE_CHANGE, opName);
    		pref.setOnPreferenceChangeListener(mPrefEventFetcher);
    	}
    }
}