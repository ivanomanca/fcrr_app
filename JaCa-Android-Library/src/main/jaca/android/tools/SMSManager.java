package jaca.android.tools;

import jaca.android.dev.BroadcastReceiverArtifact;

import java.util.ArrayList;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;


/**
 * Artifat used for managing SMS messages (send, 
 * receive sms messages and monitoring if sms message is 
 * successfully sent, or failed).
 * <br/><br/>
 * Requires the permissions android.permission.RECEIVE_SMS and android.permission.SEND_SMS
 * <br/>
 * 
 * 
 * <p> #### Observable Events  #####
 *  <ul>
 *  	<li>
 *  	{@code sms_received(Source, Message)}.
 *  		<ul>
 *  			<li>{@code Source} = String the source who sent the message</li>
 *  			<li>{@code Message} = String the message received</li>
 *  		</ul> 
 * 		</li> 
 *  	<li>
 *  	{@code sms_status_sent(Destination, Part)}.
 *  		<ul>
 *  			<li>{@code Destination} = String value containing the destination of the SMS</li>
 *  			<li>{@code Part} = int</li>
 *  		</ul> 
 * 		</li>
 *  	<li>
 *  	{@code sms_status_error(Type, ErrorCode, Destination, Part)}.
 *  		<ul>
 *  			<li>{@code Type = {"generic", "no_service", "null_pdu", "radio_off"}}</li>
 *  			<li>{@code ErrorCode} = An int indicating the error code.</li>
 *  			<li>{@code Part} = int</li>
 *  			<li>{@code Destination} = A String representing the SMS destination</li>
 *  		</ul> 
 * 		</li>
 *  </ul>
 * </p>
 * 
 * @author mguidi
 *
 */
public class SMSManager extends BroadcastReceiverArtifact {
	
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	public static final String SENT_INTENT = "jaca.android.tools.sms.SENT_INTENTS";
	public static final String DELIVERY_INTENT = "jaca.android.tools.sms.DELIVERY_INTENT";
	
	public static final String SMS_RECEIVED = "sms_received";
	public static final String SMS_STATUS_SENT = "sms_status_sent";
	public static final String SMS_STATUS_ERROR = "sms_status_error";
	public static final String SMS_STATUS_ERROR_GENERIC = "generic";
	public static final String SMS_STATUS_ERROR_NULL_PDU = "null_pdu";
	public static final String SMS_STATUS_ERROR_RADIO_OFF = "radio_off";
	public static final String SMS_STATUS_ERROR_NO_SERVICE = "no_service";
	
	public static String ARTIFACT_DEF_NAME =  "sms-manager";
	
	/**
	 * Standard initialisation
	 */
	protected void init() {
		init(false);
	}
	
	/**
	 * Initialisation that allow to disable the propagation of the SMS
	 * broadcast received
	 * @param abortBroadcast
	 */
	protected void init(boolean abortBroadcast) {
		super.init();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SMS_RECEIVED_ACTION);
		linkBroadcastReceiverToOp(filter, "smsReceived", abortBroadcast);
		
		IntentFilter filter2 = new IntentFilter();
		filter2.addAction(SENT_INTENT);
		filter2.addAction(DELIVERY_INTENT);
		linkBroadcastReceiverToOp(filter2, "smsSended");
		
	}
	
	protected void dispose() {
		super.dispose();
		unlinkBroadcastReceiverToOp("smsReceived");
		unlinkBroadcastReceiverToOp("smsSended");
	}
	
	@INTERNAL_OPERATION void smsSended(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		if(intent.getAction().equals(SENT_INTENT)) {
			String destinationAddress = intent.getStringExtra("destination");
			int part = intent.getIntExtra("part", -1);
			
			switch(broadcastReceiver.getResultCode()){
			case Activity.RESULT_OK:
				signal(SMS_STATUS_SENT, destinationAddress, part);
				break;
			
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				int errorCode = intent.getIntExtra("errorCode", 0);
				signal(SMS_STATUS_ERROR, SMS_STATUS_ERROR_GENERIC, errorCode, destinationAddress, part);
				break;
				
			case SmsManager.RESULT_ERROR_NULL_PDU:
				signal("smsStatusError", SMS_STATUS_ERROR_NULL_PDU, 0, destinationAddress, part);
				break;
				
			case SmsManager.RESULT_ERROR_RADIO_OFF:
				signal(SMS_STATUS_ERROR, SMS_STATUS_ERROR_RADIO_OFF, 0, destinationAddress, part);
				break;
				
			case SmsManager.RESULT_ERROR_NO_SERVICE:
				signal(SMS_STATUS_ERROR, SMS_STATUS_ERROR_NO_SERVICE, 0, destinationAddress, part);
				break;
			}
			
		} else if (intent.getAction().equals(DELIVERY_INTENT)){
			//String destinationAddress = intent.getStringExtra("destination");
			//int part = intent.getIntExtra("part", -1);
			
			//System.out.println(DELIVERY_INTENT);
			
		}
	}
	
	@INTERNAL_OPERATION void smsReceived(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
		if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdusObj = (Object[]) bundle.get("pdus");
				for (int i = 0; i<pdusObj.length; i++) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					signal(SMS_RECEIVED, msg.getOriginatingAddress(), msg.getDisplayMessageBody());
				}
			}
		} 
	}
	
	/**
	 * @OPERATION Send a text based SMS.
	 * @param destinationAddress the address to send the message to
	 * @param text the body of the message to send
	 */
	@OPERATION public void send(String destinationAddress, String text) {
		SmsManager smsManager = SmsManager.getDefault();
		ArrayList<String> parts = smsManager.divideMessage(text);
		
		ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
		ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();
		
		for (int i=0; i<parts.size(); i++) {
			Intent sentIntent = new Intent(SENT_INTENT);
			sentIntent.putExtra("destination", destinationAddress);
			sentIntent.putExtra("part", i);
			PendingIntent pSentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, sentIntent, PendingIntent.FLAG_CANCEL_CURRENT);
			sentIntents.add(pSentIntent);
			
			Intent deliveryIntent = new Intent(DELIVERY_INTENT);
			deliveryIntent.putExtra("destination", destinationAddress);
			deliveryIntent.putExtra("part", i);
			PendingIntent pDeliveryIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, deliveryIntent, PendingIntent.FLAG_CANCEL_CURRENT);
			deliveryIntents.add(pDeliveryIntent);
		}
		
		smsManager.sendMultipartTextMessage(destinationAddress, null, parts, sentIntents, deliveryIntents);
	}
	
}
