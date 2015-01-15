package com.teamNIKaml.reminder.service;

import android.app.IntentService;
import android.content.Intent;

public class MyService extends IntentService {

	public MyService() {
		super("myIntentService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		/*
		 * do all the work here 
		 * do not have to create a new thread
		 * this function work in different thread like asyntask-doinginbackgroud
		 * 
		 * */
	}
}
