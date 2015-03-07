package com.teamNIKaml.reminder.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
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
	
	private int numMessages =0;

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		/*
		 * do all the work here do not have to create a new thread this function
		 * work in different thread like asyntask-doinginbackgroud
		 */
		Toast.makeText(getApplicationContext(), "Receiving.......alarm",
				Toast.LENGTH_SHORT).show();
		ReminderHelper reminderhelper = new ReminderHelper();

		ReminderDataSource reminderdataSource = ReminderDataSource
				.getReminderDataSource();
		reminderdataSource.setContext(getApplicationContext());
		reminderhelper.select(null, null, null, null);

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
						Toast.LENGTH_SHORT).show();
				date.add(i);

			}
			i++;
		}

		if (date.size() > 0)
			setNotification();

	}


	
	
	
	
	
	private void setNotification()
	{
		Toast.makeText(this, "kumbidi", Toast.LENGTH_LONG).show();

		final NotificationManager mNotificationManager = (NotificationManager) getApplicationContext()
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
		  NotificationCompat.Builder  mBuilder = 
			      new NotificationCompat.Builder(getApplicationContext());	

			      mBuilder.setContentTitle("New Message");
			      mBuilder.setContentText("You've received new message.");
			      mBuilder.setTicker("New Message Alert!");
			      mBuilder.setSmallIcon(R.drawable.ic_launcher);

			      /* Increase notification number every time a new notification arrives */
			      mBuilder.setNumber(++numMessages);
			      
			      /* Creates an explicit intent for an Activity in your app */
			    

			    
			  	PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(),
						NotificationReceiverActivity.class), 0);
			        
			      mBuilder.setContentIntent(resultPendingIntent);

			     

			      /* notificationID allows you to update the notification later on. */
			      mNotificationManager.notify(100, mBuilder.build());
	}
	
	
	
}
