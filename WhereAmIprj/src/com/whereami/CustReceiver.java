package com.whereami;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.SmsManager;
import android.util.Log;

public class CustReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Extract data included in the Intent
		String message = intent.getStringExtra("message");
		String sender = intent.getStringExtra("sender");
		Log.d("receiver", "Got message: " + message);
		Log.d("receiver", "Got sender: " + sender);
		String locationProvider = LocationManager.NETWORK_PROVIDER;
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (Const._DEBUG == true) {
			if (message.equalsIgnoreCase(Const._SECRET_WORD)) {
				sendSMS(sender,
						"http://maps.google.com/maps?z=12&t=m&q=loc:38.9419+-78.3020");

			}
		} else {
			Location lastKnownLocation = locationManager
					.getLastKnownLocation(locationProvider);
			if (lastKnownLocation != null) {
				double lat = (double) (lastKnownLocation.getLatitude());
				double lng = (double) (lastKnownLocation.getLongitude());
				Log.d("receiver", "Coords lat and long: " + lat + " " + lng);

				if (message.equalsIgnoreCase(Const._SECRET_WORD)) {
					// sendSMS(sender,
					// "http://maps.google.com/maps?z=12&t=m&q=loc:38.9419+-78.3020");
					sendSMS(sender,
							"http://maps.google.com/maps?z=12&t=m&q=loc:" + lat
									+ "+" + lng);
				}
			}
		}

	}

	private void sendSMS(String phoneNumber, String message) {

		if (message.length() > 159)
			message = message.substring(0, 159);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

}
