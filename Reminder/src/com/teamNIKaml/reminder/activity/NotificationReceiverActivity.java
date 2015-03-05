package com.teamNIKaml.reminder.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class NotificationReceiverActivity extends Activity {

	private TextView textView;
	private String message = "";

	private List<String> nameList = new ArrayList<String>();
	private List<String> noteList = new ArrayList<String>();
	private List<String> dateList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		init();
		processNotification();

		setData();

	}

	private void setData() {
		// TODO Auto-generated method stub
		textView.setText(message);
	}

	private void init() {
		// TODO Auto-generated method stub
		textView = (TextView) findViewById(R.id.textView1);
	}

	private void processNotification() {
		// TODO Auto-generated method stub

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

				message = String.valueOf(i + 1) + ". " + nameList.get(i) + "\t"
						+ noteList.get(i) + "\t" + dateList.get(i) + "\n";

			}
			i++;
		}

	}

}
