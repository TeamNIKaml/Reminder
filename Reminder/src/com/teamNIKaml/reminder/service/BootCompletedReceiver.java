package com.teamNIKaml.reminder.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		System.out.println("bingooooooooooooooooooooooooooooo");
		AlarmService alarmService = AlarmService.getAlarmService(context);
		alarmService.srartAlarm();

	}

}