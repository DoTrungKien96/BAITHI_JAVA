package com.example.appbaothuc_thi;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class Music extends Service {
	MediaPlayer mediaPlayer;
	int id;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("Toi Trong Music","Xin Chao");
		
		String nhankey = intent.getExtras().getString("extra");
		Log.e(" Music Nhan Key ",nhankey);
		
		if (nhankey.equals("on")){
			id = 1;
		}else if (nhankey.equals("off")){
			id = 0;
		}
		
		if (id==1){
			mediaPlayer = MediaPlayer.create(this, R.raw.nhacchuong);
			mediaPlayer.start();
			id = 0;
		}else if (id == 0){
			mediaPlayer.stop();
			mediaPlayer.reset();
		}
		

		return START_NOT_STICKY;
	}
}
