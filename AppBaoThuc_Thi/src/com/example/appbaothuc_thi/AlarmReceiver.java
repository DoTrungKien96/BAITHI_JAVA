package com.example.appbaothuc_thi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	private Context context;
	private Intent intent;

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Log.e("Toi trong Receiver","Xin chao");
		String chuoi_string = intent.getExtras().getString("extra");
		Log.e("Ban Truyen Key",chuoi_string);
		
		Intent myIntent = new Intent(context,Music.class);
		myIntent.putExtra("extra",chuoi_string);
		context.startService(myIntent);
	}

}
