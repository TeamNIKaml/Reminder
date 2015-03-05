package com.teamNIKaml.reminder.service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

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

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 7); // Everyday 7 AM
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

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
