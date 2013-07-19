package jaca.android.tools;

import jaca.android.dev.JaCaArtifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;

/**
 * Artifact that manages the device contacts
 * 
 * @author mguidi
 *
 */
public class ContactsManager extends JaCaArtifact{
	
	/**
	 * Artifact default name.
	 */
	public static final String ARTIFACT_DEF_NAME =  "contacts-manager";
	
	/**
	 * Artifact operation that look up contact display name in the phone book  
	 * 
	 * @param phoneNumber phone number
	 * @param name correspondent name to phone number 
	 */
	@OPERATION public void lookup(String phoneNumber, OpFeedbackParam<String> name){
		ContentResolver resolver = getApplicationContext().getContentResolver();
		
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
		Cursor cursor = resolver.query(uri, new String[]{PhoneLookup.DISPLAY_NAME}, null, null, null);
		 
		if(cursor.moveToFirst()){
			name.set(cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME)));
		} else {
			name.set(phoneNumber);
		}
	}
	
	
	/* 
	@OPERATION public void save(String phoneNumber, String Name){
		// TODO
	}
	*/
	
	/**
	 * Artifact operation that redirects all contacts to voice mail
	 */
	@OPERATION void sendToVoiceMailAllOn() {
		ContentResolver resolver = getApplicationContext().getContentResolver();
		ContentValues values = new ContentValues(1);
		values.put(Contacts.SEND_TO_VOICEMAIL, true);
		
		Cursor contactsCursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);
		
		while (contactsCursor.moveToNext()) {
			String contactLookUpKey = contactsCursor.getString(contactsCursor.getColumnIndex(PhoneLookup.LOOKUP_KEY));
			
			Uri lookupUri = Uri.withAppendedPath(Contacts.CONTENT_LOOKUP_URI, contactLookUpKey);
			resolver.update(lookupUri, values, null, null);
		}
		contactsCursor.close();
	}
	
	
	/**
	 * Artifact operation that removes redirection to voice mail from all contacts
	 */
	@OPERATION void sendToVoiceMailAllOff() {
		ContentResolver resolver = getApplicationContext().getContentResolver();
		ContentValues values = new ContentValues(1);
		values.put(Contacts.SEND_TO_VOICEMAIL, false);
		Cursor contactsCursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);
		
		while (contactsCursor.moveToNext()) {
			String contactLookUpKey = contactsCursor.getString(contactsCursor.getColumnIndex(PhoneLookup.LOOKUP_KEY));
			
			Uri lookupUri = Uri.withAppendedPath(Contacts.CONTENT_LOOKUP_URI, contactLookUpKey);
			resolver.update(lookupUri, values, null, null);
		}
		contactsCursor.close();
	}
	
	/**
	 * Artifact operation that redirects specified contact phone number to voice mail
	 */
	@OPERATION void sendToVoiceMailOn(String phoneNumber) {
		ContentResolver resolver = getApplicationContext().getContentResolver();
		ContentValues values = new ContentValues(1);
		values.put(Contacts.SEND_TO_VOICEMAIL, true);
	
		Uri lookupUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, phoneNumber);
		Uri uri = Contacts.lookupContact(getApplicationContext().getContentResolver(), lookupUri);
		resolver.update(uri, values, null, null);
	}
	
	
	/**
	 * Artifact operation that removes redirection to voice mail from specified contact phone number
	 */
	@OPERATION void sendToVoiceMailOff(String phoneNumber) {
		ContentResolver resolver = getApplicationContext().getContentResolver();
		ContentValues values = new ContentValues(1);
		values.put(Contacts.SEND_TO_VOICEMAIL, false);
	
		Uri lookupUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, phoneNumber);
		Uri uri = Contacts.lookupContact(getApplicationContext().getContentResolver(), lookupUri);
		resolver.update(uri, values, null, null);
	}
}