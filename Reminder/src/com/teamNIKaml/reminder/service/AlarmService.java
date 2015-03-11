package com.teamNIKaml.reminder.service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmService {

	private Context context;
	private static AlarmService alarmService;
	
	
	public static AlarmService getAlarmService(Context context) {
		if (alarmService == null)
			alarmService = new AlarmService(context);
		return alarmService;
	}

	public AlarmService(Context context) {
		this.context = context;
	}

	public void srartAlarm() {
		Toast.makeText(context, "sasi", Toast.LENGTH_LONG).show();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 6); // Everyday 7 AM
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//calendar.add(Calendar. DATE, 1);
		

		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				new Intent(context, MyService.class),
				PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
				pendingIntent);

	}

}
