package com.teamNIKaml.reminder.fragment;

import java.util.ArrayList;
import java.util.List;

import com.teamNIKaml.reminder.property.PasswordDataSource;
import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.IDBHelper;
import com.teamNIKaml.reminder.dbcomponents.PasswordHelper;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract.Intents.Insert;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



public class AddPasswordDialog extends DialogFragment {
	
	private PasswordDataSource dataSource = PasswordDataSource.getPasswordDataSource();
	private EditText accountName,username,catagory,password;	
	private IDBHelper dbHelper = new PasswordHelper();
	private Button saveButton;
  
	
	private LayoutInflater li;
	
	public AddPasswordDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		
		
  	View v =li.inflate(R.layout.activity_add_password, null);	   ; 	
   	 
   	 initDialog(v);
   	setDialogData();
   	
   	final Dialog dlg = new AlertDialog.Builder(getActivity()).setView(v)
			
			.create();
   	
   	saveButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setDialogData();
			Log.e("onclick", "doInBackground");
			dataSource.setContext(getActivity().getApplicationContext());
			dbHelper.insert();
			dlg.dismiss();
			
		}
	});
		
		return dlg;
	}

	private void initDialog(View v) {
		// TODO Auto-generated method stub
		accountName =(EditText) v.findViewById(R.id.accountName);
		username =(EditText)v.findViewById(R.id.username);
		catagory =(EditText)v.findViewById(R.id.catagory);
		password = (EditText)v.findViewById(R.id.password); 
		saveButton =(Button)v.findViewById(R.id.saveButton);
		
		  
	}
	
	
	 private void setDialogData() {
			// TODO Auto-generated method stub
	    	dataSource.setAccountName(accountName.getText().toString());
	    	dataSource.setUsername(username.getText().toString());
	    	dataSource.setCatagory(catagory.getText().toString());
	    	dataSource.setPassword(password.getText().toString());
			
		}
	
		
	
}
