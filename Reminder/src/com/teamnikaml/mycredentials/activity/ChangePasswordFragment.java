package com.teamnikaml.mycredentials.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.appmodel.AppConstants;



public class ChangePasswordFragment extends Fragment {

	private EditText oldPassword, newPassword, confirmPassword;
	private Button update;
	private SharedPreferences pref;
	private Editor editor;
	private String password;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootview = inflater.inflate(R.layout.activity_change_password, container, false);
		init(rootview);
		return rootview;
	}

	

	private void init(View rootview) {
		// TODO Auto-generated method stub
		pref = getActivity().getSharedPreferences(
				AppConstants.SHARED_PREFERENCE_NAME, 0);
		password = pref.getString("password", "");

		oldPassword = (EditText) rootview.findViewById(R.id.old_password);
		newPassword = (EditText) rootview.findViewById(R.id.new_password);
		confirmPassword = (EditText) rootview.findViewById(R.id.confirm_password);
		update = (Button) rootview.findViewById(R.id.updateButton);

		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (password.equals(oldPassword.getText().toString())) {
					if (newPassword.getText().toString()
							.equals(confirmPassword.getText().toString())) {
						writeSharedPreference();
						Toast.makeText(getActivity(),
								"Password Updated Sucessfully",
								Toast.LENGTH_LONG).show();
						
					} else
						Toast.makeText(
								getActivity(),
								"new password and confirm password didn't match",
								Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(getActivity(),
							"old password is wrong", Toast.LENGTH_LONG).show();
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