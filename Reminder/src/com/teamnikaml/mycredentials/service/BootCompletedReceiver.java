package com.teamnikaml.mycredentials.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		
		AlarmService alarmService = AlarmService.getAlarmService(context);
		alarmService.srartAlarm();

	}

}