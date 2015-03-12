package com.teamNIKaml.reminder.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.IDBHelper;
import com.teamNIKaml.reminder.dbcomponents.PasswordHelper;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.PasswordDataSource;
import com.teamNIKaml.reminder.property.XOREncryption;

@SuppressLint("InflateParams")
public class EditPasswordDialog extends DialogFragment {

	private PasswordDataSource dataSource = PasswordDataSource
			.getPasswordDataSource();
	private EditText accountName, username, password;
	private Spinner catagory;
	private IDBHelper dbHelper = new PasswordHelper();
	private Button editButton, deleteButton;
	private String whereClause;
	private String[] whereArgs;

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public String[] getWhereArgs() {
		return whereArgs;
	}

	public void setWhereArgs(String[] whereArgs) {
		this.whereArgs = whereArgs;
	}

	private LayoutInflater li;

	public EditPasswordDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View v = li.inflate(R.layout.activity_editpassword, null);

		initDialog(v);
	

		final Dialog dlg = new AlertDialog.Builder(getActivity()).setView(v)

		.create();

		editButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setDialogData() ;
				dataSource.setContext(getActivity().getApplicationContext());
				dbHelper.update(whereClause, whereArgs);
				

				dlg.dismiss();

			}
		});
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				setDialogData();
				Log.e("onclick", "doInBackground");
				dbHelper.delete(whereClause, whereArgs);
				

				dlg.dismiss();

			}
		});

		return dlg;
	}

	private void initDialog(View v) {
		// TODO Auto-generated method stub
		accountName = (EditText) v.findViewById(R.id.accountName);
		username = (EditText) v.findViewById(R.id.username);
		catagory = (Spinner) v.findViewById(R.id.catagorySpinner);
		password = (EditText) v.findViewById(R.id.password);
		editButton = (Button) v.findViewById(R.id.editButton);
		deleteButton = (Button) v.findViewById(R.id.deleteButton);
		dataSource.setContext(getActivity().getApplicationContext());
		String args[] = new String[4];
		
		String name, pass;

		String key = XOREncryption.encryptKey(dataSource.getAccountName() + dataSource.getCatagory()+Constants.KEY_OPTIMIZER);

		name = XOREncryption.encrypt( dataSource.getUsername(), key);
		pass = XOREncryption.encrypt(dataSource.getPassword(), key);
		
		
		args[0] = dataSource.getCatagory();
		args[1] = dataSource.getAccountName();
		args[2] =name;
		args[3] =pass ;

		setWhereClause("catagory=? AND  accountName=? "
				+ "AND  username=? AND  password=? ");
		setWhereArgs(args);

		accountName.setText(dataSource.getAccountName());
		username.setText(dataSource.getUsername());
		password.setText(dataSource.getPassword());
		int i = 0;
		for (i = 0; i < Constants.CATAGORY_SPINNER_ARRAY.length; i++) {
			if (dataSource.getCatagory().equalsIgnoreCase(
					Constants.CATAGORY_SPINNER_ARRAY[i]))
				break;
		}
		catagory.setSelection(i);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayAdapter adapter = new ArrayAdapter(getActivity()
				.getApplicationContext(), R.layout.spinner_item,
				Constants.CATAGORY_SPINNER_ARRAY);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		catagory.setAdapter(adapter);

	}

	private void setDialogData() {
		// TODO Auto-generated method stub
		dataSource.setContext(getActivity().getApplicationContext());
		dataSource.setAccountName(accountName.getText().toString());
		dataSource.setUsername(username.getText().toString());
		dataSource.setCatagory(catagory.getSelectedItem().toString());
		dataSource.setPassword(password.getText().toString());

	}

}
