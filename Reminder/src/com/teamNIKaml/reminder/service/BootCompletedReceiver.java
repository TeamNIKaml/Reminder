package com.teamNIKaml.reminder.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		
		Toast.makeText(context, "BootCompletedReceiver", Toast.LENGTH_SHORT).show();
		AlarmService alarmService = AlarmService.getAlarmService(context);
		alarmService.srartAlarm();

	}

}