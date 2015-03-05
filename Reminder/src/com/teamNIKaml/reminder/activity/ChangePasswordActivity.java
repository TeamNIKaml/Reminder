package com.teamNIKaml.reminder.activity;

import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity {

	private EditText oldPassword,newPassword,confirmPassword;
	private Button update;
	private SharedPreferences pref;
	private Editor editor;
	private String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		pref = getApplicationContext().getSharedPreferences(
				"localdiskchildlocator", 0);
		password = pref.getString("password", "");
		
		 oldPassword = (EditText)findViewById(R.id.old_password);
		newPassword  = (EditText)findViewById(R.id.new_password);
		 confirmPassword  = (EditText)findViewById(R.id.confirm_password);
		 update  = (Button)findViewById(R.id.updateButton);
		
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(password.equals(oldPassword.getText().toString()))
				{
					if(newPassword.getText().toString().equals(confirmPassword.getText().toString()))
					{
						writeSharedPreference();
						Toast.makeText(getApplicationContext(), "Password Updated Sucessfully", Toast.LENGTH_LONG).show();
						finish();
					}
					else
						Toast.makeText(getApplicationContext(), "new password and confirm password didn't match", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(getApplicationContext(), "old password is wrong", Toast.LENGTH_LONG).show();
			}
		});
		
	}

	private void writeSharedPreference() {
		// TODO Auto-generated method stub
		
		editor = pref.edit();
		editor.putString("password", newPassword.getText().toString());
		editor.commit();
	}

	
	
}