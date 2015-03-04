package com.teamNIKaml.reminder.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.teamNIKaml.reminder.activity.NotificationReceiverActivity;
import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyService extends IntentService {

	public MyService() {
		super("myIntentService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		/*
		 * do all the work here do not have to create a new thread this function
		 * work in different thread like asyntask-doinginbackgroud
		 */
		ReminderHelper reminderhelper = new ReminderHelper();
		reminderhelper.select(null, null, null, null);

		ReminderDataSource reminderdataSource = ReminderDataSource
				.getReminderDataSource();
		List<ReminderDataSource> reminderList = reminderdataSource
				.getReminderList();

		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> noteList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		
		for (ReminderDataSource reminder : reminderList) {

			nameList.add(reminder.getName());
			noteList.add(reminder.getNote());
			dateList.add(reminder.getDate());

		}
		
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		month++;
		String formattedDate = day+"-"+month+"-"+year;
		
		for (String string : dateList) {
			if (formattedDate.equals(string)) {
				Toast.makeText(getApplicationContext(), "Receiving alarm",
						Toast.LENGTH_LONG).show();
				setReminderNotification();
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void setReminderNotification() {
		// TODO Auto-generated method stub
		final NotificationManager mgr = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification note = new Notification(R.drawable.ic_launcher,
				"Android Example Status message!", System.currentTimeMillis());

		// This pending intent will open after notification click
		PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this,
				NotificationReceiverActivity.class), 0);

		note.setLatestEventInfo(this, "Android Example Notification Title",
				"This is the android example notification message", i);

		// After uncomment this line you will see number of notification arrived
		// note.number=2;
		mgr.notify(2, note);
	}
}
