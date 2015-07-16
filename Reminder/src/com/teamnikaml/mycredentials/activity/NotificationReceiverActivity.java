package com.teamnikaml.mycredentials.activity;

import java.util.Calendar;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.appmodel.AppConstants;
import com.teamnikaml.mycredentials.dbcomponents.DBHelper;
import com.teamnikaml.mycredentials.reminder.adaptor.ReminderListAdaptor;

public class NotificationReceiverActivity extends Activity {

	private ListView listView;
	private ReminderListAdaptor listAdapter;

	private String[] nameList;
	private String[] noteList;
	private String[] dateList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		init();
		processNotification();

	}

	private void init() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.listView1);

		listAdapter = new ReminderListAdaptor(getApplicationContext());
	}

	private void processNotification() {
		// TODO Auto-generated method stub

		/*
		 * ReminderHelper reminderhelper = new ReminderHelper();
		 * reminderhelper.select(null, null, null, null);
		 * 
		 * ReminderDataSource reminderdataSource = ReminderDataSource
		 * .getReminderDataSource(); List<ReminderDataSource> reminderList =
		 * reminderdataSource .getReminderList();
		 * 
		 * for (ReminderDataSource reminder : reminderList) {
		 * 
		 * nameList.add(reminder.getName()); noteList.add(reminder.getNote());
		 * dateList.add(reminder.getDate());
		 * 
		 * }
		 */

		DBHelper dbHelper = new DBHelper(getApplicationContext(), 1,
				AppConstants.DB_NAME_REMINDER, AppConstants.REMINDER_DB_QUERY);
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		Cursor cursor = database.query(AppConstants.REMINDER_TABLE_NAME, null,
				null, null, null, null, null);
		int size = cursor.getCount();

		nameList = new String[size];
		noteList = new String[size];
		dateList = new String[size];

		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		month++;
		String formattedDate = day + "-" + month + "-" + year;

		int i = 0;
		if (cursor.moveToFirst()) {

			do {

				if (formattedDate.equals(cursor.getString(2))) {

					nameList[i] = (cursor.getString(1));
					noteList[i] = (cursor.getString(3));
					dateList[i++] = (cursor.getString(2));
				}

			} while (cursor.moveToNext());

		}

		listAdapter.setDate(dateList);
		listAdapter.setNote(noteList);
		listAdapter.setReminderName(nameList);

		listView.setAdapter(listAdapter);

	}

}
