package com.teamNIKaml.reminder.activity;

import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

import android.app.Activity;
import android.os.Bundle;

public class TestActivity extends Activity {

	private ReminderHelper reminderHelper;
	private ReminderDataSource dataSource = ReminderDataSource.getReminderDataSource();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		reminderHelper = new ReminderHelper();
		dataSource.setContext(getApplicationContext());
		dataSource.setName("sads");
		dataSource.setNote("sdfsdasdad");
		dataSource.setDate("dsfsd");
		reminderHelper.insert();
		
		
	}

	
}