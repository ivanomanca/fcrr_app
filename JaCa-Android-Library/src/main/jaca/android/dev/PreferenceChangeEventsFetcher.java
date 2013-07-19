package jaca.android.dev;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
/**
 * Class used for managing the dispatch of PreferenceChange events
 * to CArtAgO Artifacts
 *
 * @author asanti
 *
 */
public class PreferenceChangeEventsFetcher extends BaseEventFetcher implements OnPreferenceChangeListener{
	
	public static final String ON_PREFERENCE_CHANGE = "onPreferenceChange";
	
	public PreferenceChangeEventsFetcher(){
		super();
	}
	
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		putEvent(new EventOpInfo(preference, ON_PREFERENCE_CHANGE, preference, newValue));
		return true;
	}
}