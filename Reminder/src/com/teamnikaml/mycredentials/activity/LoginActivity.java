package com.teamnikaml.mycredentials.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.appmodel.AppConstants;
import com.teamnikaml.mycredentials.appmodel.AppRater;
import com.teamnikaml.mycredentials.appmodel.Login;
import com.teamnikaml.mycredentials.password.fragment.Password;
import com.teamnikaml.mycredentials.reminder.database.ReminderHelper;
import com.teamnikaml.mycredentials.reminder.fragment.Reminder;
import com.teamnikaml.mycredentials.reminder.model.ReminderDataSource;
import com.teamnikaml.mycredentials.service.AlarmService;
import com.teamnikaml.navigationdrawerlib.activity.NavigationDrawerActivity;
import com.teamnikaml.navigationdrawerlib.model.Mapper;
import com.teamnikaml.navigationdrawerlib.model.NavDrawerItem;

public class LoginActivity extends Activity {

	private EditText password;
	private Button login;
	private SharedPreferences pref;
	private Editor editor;
	private Login logindetails = new Login();

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		init();
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				processLogin();
			}
		});

	}

	private void processLogin() {

		if (logindetails.getLoginStatus() == 100) {
			if (!logindetails.getPassword().equals(
					password.getText().toString())) {
				Toast.makeText(this, "Invalid PAssword", Toast.LENGTH_LONG)
						.show();
			} else {
				//startActivity(new Intent(this, PostLoginActivity.class));
				startNavigationDrawer();
			}
		} else {
			writeSharedPreference();
			//startActivity(new Intent(this, PostLoginActivity.class));
			startNavigationDrawer();
		}

	}

	private void startNavigationDrawer() {
		// TODO Auto-generated method stub
		
		
		String[] title ={"Password","Reminder"};
		int[] icon={R.drawable.ic_app_launcher,R.drawable.ic_app_launcher};
		
		Mapper mapper = Mapper.getMapper();
		
		//NavDrawerItem drawerItem = new NavDrawerItem();
		
		List<Fragment> fragmentList = new ArrayList<Fragment>();
		
		List<NavDrawerItem> slidemenuList = new ArrayList<NavDrawerItem>();
		
		
		for(int i=0;i<title.length;i++)
		{
			slidemenuList.add(new NavDrawerItem(title[i], icon[i]));
		}
		
		
		
		fragmentList.add(new Password());
		fragmentList.add(new Reminder());
		
				
		mapper.setFragmentList(fragmentList);
		mapper.setNavigationDrawerItemList(slidemenuList);
		
		
		Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);		
		startActivity(intent);
		
		
		
		
		
	}

	private void writeSharedPreference() {
		// TODO Auto-generated method stub

		pref = getApplicationContext().getSharedPreferences(
				AppConstants.SHARED_PREFERENCE_NAME, 0);
		editor = pref.edit();
		editor.putInt("loginstatus", 100);
		editor.putString("password", password.getText().toString());
		editor.commit();

	}

	private void getLogininfo() {
		pref = getApplicationContext().getSharedPreferences(
				AppConstants.SHARED_PREFERENCE_NAME, 0);
		logindetails.setPassword(pref.getString("password", ""));
		logindetails.setLoginStatus(pref.getInt("loginstatus", 0));
	}

	private void init() {

		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.loginButton);
		getLogininfo();
		if (logindetails.getLoginStatus() != 100) {
			login.setText("Register");
			ReminderHelper x = new ReminderHelper();
			ReminderDataSource rd = ReminderDataSource.getReminderDataSource();
			rd.setContext(getApplicationContext());
			x.select(null, null, null, null);
			password.setHint("Set Application Password");
			AlarmService alarmService = AlarmService
					.getAlarmService(getApplicationContext());
			alarmService.srartAlarm();
		}
		// launchMarket();
		AppRater.app_launched(this);

	}

}
