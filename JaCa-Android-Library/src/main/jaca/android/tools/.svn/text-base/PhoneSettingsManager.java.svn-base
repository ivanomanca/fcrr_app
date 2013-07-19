package jaca.android.tools;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import jaca.android.dev.BroadcastReceiverArtifact;

/**
 * Artifact that provides access to volume and ringer mode control <br/>
 * 
 * <p><strong> #### Observable Properties ##### </strong></p>
 * <p>
 *  <ul>
 *  	<li>
 *  	{@code ringer_mode(Mode). Mode = {"normal", "silent", "vibrate"}}
 *  	</li>
 *  </ul> 
 * </p>
 * @author mguidi
 *
 */
public class PhoneSettingsManager extends BroadcastReceiverArtifact {
	
	/**
	 * Observable propert that store the current status of
	 * the device ringer mode
	 */
	public static final String RINGER_MODE = "ringer_mode";
	
	/**
	 * Value for the {@link PhoneSettingsManager#RINGER_MODE}
	 * observable property indicating a ringer set to normal 
	 */
	public static final String RINGER_MODE_NORMAL = "normal";
	
	/**
	 * Value for the {@link PhoneSettingsManager#RINGER_MODE}
	 * observable property indicating a ringer set to silent 
	 */
	public static final String RINGER_MODE_SILENT = "silent";
	
	/**
	 * Value for the {@link PhoneSettingsManager#RINGER_MODE}
	 * observable property indicating a ringer set to vibrate 
	 */
	public static final String RINGER_MODE_VIBRATE = "vibrate";
	
	/**
	 * Artifact default name
	 */
	public static final String ARTIFACT_DEF_NAME = "phone-settings-manager";
	
	private AudioManager mAudioManager;
	
	/**
	 * Deafault init
	 */
	protected void init() {
		init(false);
	}
	/**
	 * Initilisation with the opportunity to specify if abort further
	 * boradcast for the received intents
	 * @param abortBroadcast
	 */
	protected void init(boolean abortBroadcast) {
		super.init();
		
		mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
		int ringerMode = mAudioManager.getRingerMode();
		
		switch(ringerMode) {
		case AudioManager.RINGER_MODE_NORMAL:
			defineObsProperty(RINGER_MODE, RINGER_MODE_NORMAL);
			break;
		case AudioManager.RINGER_MODE_SILENT:
			defineObsProperty(RINGER_MODE, RINGER_MODE_SILENT);
			break;
		case AudioManager.RINGER_MODE_VIBRATE:
			defineObsProperty(RINGER_MODE, RINGER_MODE_VIBRATE);
			break;
		}
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
		linkBroadcastReceiverToOp(filter, "onRingerModeChange", abortBroadcast);
		
		/*
		int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		defineObsProperty("stream_system_volume", volume);
		volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		defineObsProperty("volume", "stream_music", volume);
		volume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
		defineObsProperty("volume", "stream_ring", volume);
		*/
	}
	
	protected void dispose() {
		super.dispose();
		unlinkBroadcastReceiverToOp("onRingerModeChange");
	}
	
	
	@INTERNAL_OPERATION void onRingerModeChange(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		int ringerMode = intent.getIntExtra(AudioManager.EXTRA_RINGER_MODE, -1);
		
		switch(ringerMode) {
		case AudioManager.RINGER_MODE_NORMAL:
			getObsProperty(RINGER_MODE).updateValue(RINGER_MODE_NORMAL);
			break;
		case AudioManager.RINGER_MODE_SILENT:
			getObsProperty(RINGER_MODE).updateValue(RINGER_MODE_SILENT);
			break;
		case AudioManager.RINGER_MODE_VIBRATE:
			getObsProperty(RINGER_MODE).updateValue(RINGER_MODE_VIBRATE);
			break;
		}
	}
	
	
	/**
	 * Artifac operation that sets the ringer mode. Silent mode will mute the volume and will not vibrate. Vibrate mode will mute the volume and vibrate. Normal mode will be audible and may vibrate according to user settings.
	 * @param ringerMode The ringer mode, one of "normal", "silent", or "vibrate".
	 */
	@OPERATION void setRingerMode(String ringerMode) {
		if (ringerMode.equals(RINGER_MODE_NORMAL))
			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		else if (ringerMode.equals(RINGER_MODE_SILENT))
			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		else if (ringerMode.equals(RINGER_MODE_VIBRATE))
			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		else
			failed("invalid ringer mode value");
	}
	
	
	/*
	 * @OPERATION Sets the speakerphone on or off
	 * @param on set true to turn on speakerphone; false to turn it off 
	 */
	/*
	@OPERATION void setSpeakerphoneOn(boolean on) {
		mAudioManager.setSpeakerphoneOn(on);
	}
	*/
	
	/*
	@OPERATION void increaseVolume() {
		mAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, 0);
		int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		getObsProperty("stream_system_volume").updateValue(volume);
	}
	
	@OPERATION void decreaseVolume() {
		mAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, 0);
		int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		getObsProperty("stream_system_volume").updateValue(volume);
	}
	*/
}