package com.teamnikaml.mycredentials.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.activity.NotificationReceiverActivity;
import com.teamnikaml.mycredentials.appmodel.AppConstants;
import com.teamnikaml.mycredentials.dbcomponents.DBHelper;

public class MyService extends IntentService {

	public MyService() {
		super("myIntentService");
	}

	private List<Integer> date = new ArrayList<Integer>();

	private List<String> nameList = new ArrayList<String>();
	private List<String> noteList = new ArrayList<String>();
	private List<String> dateList = new ArrayList<String>();

	private int numMessages = 0;

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		/*
		 * do all the work here do not have to create a new thread this function
		 * work in different thread like asyntask-doinginbackgroud
		 */


		DBHelper dbHelper = new DBHelper(getApplicationContext(), 1,
				AppConstants.DB_NAME_REMINDER, AppConstants.REMINDER_DB_QUERY);
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		Cursor cursor = database.query(AppConstants.REMINDER_TABLE_NAME, null,
				null, null, null, null, null);
		nameList.clear();
		noteList.clear();
		dateList.clear();

		if (cursor.moveToFirst()) {

			do {
				nameList.add(cursor.getString(1));
				noteList.add(cursor.getString(3));
				dateList.add(cursor.getString(2));

			} while (cursor.moveToNext());

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
						Toast.LENGTH_SHORT).show();
				date.add(i);

			}
			i++;
		}

		if (date.size() > 0)
			setNotification();

	}

	private void setNotification() {

		final NotificationManager mNotificationManager = (NotificationManager) getApplicationContext()
				.getSystemService(Context.NOTIFICATION_SERVICE);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				getApplicationContext());

		mBuilder.setContentTitle("New Message");
		mBuilder.setContentText("You've received new message.");
		mBuilder.setTicker("New Message Alert!");
		mBuilder.setSmallIcon(R.drawable.ic_app_launcher);

		/* Increase notification number every time a new notification arrives */
		mBuilder.setNumber(++numMessages);

		/* Creates an explicit intent for an Activity in your app */

		PendingIntent resultPendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, new Intent(getApplicationContext(),
						NotificationReceiverActivity.class), 0);

		mBuilder.setContentIntent(resultPendingIntent);

		/* notificationID allows you to update the notification later on. */
		mNotificationManager.notify(100, mBuilder.build());
	}

}
