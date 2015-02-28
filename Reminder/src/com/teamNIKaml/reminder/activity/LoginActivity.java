package com.teamNIKaml.reminder.activity;

import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.Login;
import com.teamNIKaml.reminder.property.ReminderDataSource;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
			}
		} else {
			writeSharedPreference();
		}

		startActivity(new Intent(this, PostLoginActivity.class));
	}

	private void writeSharedPreference() {
		// TODO Auto-generated method stub

		pref = getApplicationContext().getSharedPreferences(
				"localdiskchildlocator", 0);
		editor = pref.edit();
		Log.e("writeToSharedPreferences", "localdiskchildlocator");
		editor.putInt("loginstatus", 100);
		editor.putString("password", password.getText().toString());
		editor.commit();

	}

	private void getLogininfo() {
		pref = getApplicationContext().getSharedPreferences(
				"localdiskchildlocator", 0);
		logindetails.setPassword(pref.getString("password", ""));
		logindetails.setLoginStatus(pref.getInt("loginstatus", 0));
	}

	private void init() {

		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.loginButton);
		getLogininfo();
		if (logindetails.getLoginStatus() != 100) {
			login.setText("Register");
			password.setHint("Enter your new password");
		}

	}

}
