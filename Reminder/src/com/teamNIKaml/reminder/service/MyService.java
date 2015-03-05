package com.teamNIKaml.reminder.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.teamNIKaml.reminder.activity.NotificationReceiverActivity;
import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class MyService extends IntentService {

	public MyService() {
		super("myIntentService");
	}

	private List<Integer> date = new ArrayList<Integer>();

	private List<String> nameList = new ArrayList<String>();
	private List<String> noteList = new ArrayList<String>();
	private List<String> dateList = new ArrayList<String>();

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
		String formattedDate = day + "-" + month + "-" + year;

		int i = 0;

		for (String string : dateList) {
			if (formattedDate.equals(string)) {
				Toast.makeText(getApplicationContext(), "Receiving alarm",
						Toast.LENGTH_LONG).show();
				date.add(i);

			}
			i++;
		}

		if (date.size() > 0)
			setReminderNotification();

	}

	@SuppressWarnings("deprecation")
	private void setReminderNotification() {
		// TODO Auto-generated method stub
		String message = "", heading = "";
		for (int i = 0; i < date.size(); i++) {
			heading += nameList.get(date.get(i)) + " ";
			message += String.valueOf(i + 1) + ": " + nameList.get(date.get(i))
					+ "\t" + dateList.get(date.get(i)) + "\n";
		}

		final NotificationManager mgr = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification note = new Notification(R.drawable.ic_launcher, heading,
				System.currentTimeMillis());

		// This pending intent will open after notification click
		PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this,
				NotificationReceiverActivity.class), 0);

		note.setLatestEventInfo(this, heading, message, i);

		// After uncomment this line you will see number of notification arrived
		// note.number=2;
		mgr.notify(1, note);
	}
}
