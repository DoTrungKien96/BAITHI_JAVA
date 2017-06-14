package com.example.appbaothuc_thi;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	Button btnHenGio, btnDungLai;
	TextView txtHienThi;
	TimePicker timePicker;
	Calendar calendar;
	AlarmManager alarmManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHenGio = (Button)findViewById(R.id.btnHenGio);
        btnDungLai = (Button)findViewById(R.id.btnDungLai);
        txtHienThi = (TextView)findViewById(R.id.textView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        
        final Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
        
        btnHenGio.setOnClickListener(new View.OnClickListener() {
			
			private PendingIntent pendingIntent;

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
				calendar.set(Calendar.MINUTE,timePicker.getCurrentHour());
				
				int gio = timePicker.getCurrentHour();
				int phut = timePicker.getCurrentMinute();
				
				String string_gio = String.valueOf(gio);
				String string_phut = String.valueOf(phut);
				
				if (gio > 12){
					string_gio = String.valueOf(gio - 12);
				}
				if (phut < 10){
					string_phut = "0" + String.valueOf(phut);
				}
				intent.putExtra("extra","on");
				pendingIntent = PendingIntent.getBroadcast(
						MainActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
				alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
				
				txtHienThi.setText("Giờ bạn đặt là" + string_gio + ":" + string_phut);
			}
		});
       
        btnDungLai.setOnClickListener(new View.OnClickListener() {
			
			private PendingIntent pendingIntent;

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtHienThi.setText("Dừng lại");
				alarmManager.cancel(pendingIntent);
				intent.putExtra("extra","off");
				sendBroadcast(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
