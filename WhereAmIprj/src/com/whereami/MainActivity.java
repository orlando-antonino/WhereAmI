package com.whereami;

import com.whereiam.R;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity /*implements CommInt*/{
	
	String c = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		c = getString(R.string.hello_world);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onResume() {
	  super.onResume();


	  };


	@Override
	protected void onPause() {
	  super.onPause();
	}




}
