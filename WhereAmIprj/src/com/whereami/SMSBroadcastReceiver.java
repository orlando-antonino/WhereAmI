package com.whereami;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSBroadcastReceiver extends BroadcastReceiver {
	
	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String receivedAction = arg1.getAction();
		if (receivedAction.equalsIgnoreCase(SMS_RECEIVED)){
			Bundle bundle = arg1.getExtras();
			if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                if (messages.length > -1) {
                    Log.i(TAG, "Message recieved: " + messages[0].getMessageBody());
                 // This method is assigned to button in the layout
                 // via the onClick property
                                 // Send an Intent with an action named "my-event". 
                 
                    Intent intent = new Intent();
                    // Add data
                    intent.putExtra("message", messages[0].getMessageBody());
                    intent.putExtra("sender", messages[0].getOriginatingAddress());
                    intent.setAction("com.whereami.mybroadc");
                    arg0.sendBroadcast(intent); 
//                    LocalBroadcastManager.getInstance(arg0).sendBroadcast(intent);
		    if(messages[0].getMessageBody().equalsIgnoreCase(Const._SECRET_WORD))
                    	abortBroadcast();
                  
                }
            }
		}

	}

}
