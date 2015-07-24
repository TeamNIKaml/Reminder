package com.teamnikaml.mycredentials.password.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.appmodel.AppConstants;
import com.teamnikaml.mycredentials.dbcomponents.IDBHelper;
import com.teamnikaml.mycredentials.password.database.PasswordHelper;
import com.teamnikaml.mycredentials.password.model.PasswordDataSource;

@SuppressLint("InflateParams")
public class AddPasswordDialog extends DialogFragment {

	private PasswordDataSource dataSource = PasswordDataSource
			.getPasswordDataSource();
	private EditText accountName, username, password;
	private Spinner catagory;
	private IDBHelper dbHelper = new PasswordHelper();
	private Button saveButton;

	private LayoutInflater li;

	public AddPasswordDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View v = li.inflate(R.layout.activity_add_password, null);

		initDialog(v);
		setDialogData();

		final Dialog dlg = new AlertDialog.Builder(getActivity()).setView(v)

		.create();

		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setDialogData();

				dataSource.setContext(getActivity().getApplicationContext());
				dbHelper.insert();
				dbHelper.select(null, null, null, null);
				dlg.dismiss();

			}
		});
		
		
		dlg.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationrotateScale;
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		Window window = dlg.getWindow();
		lp.copyFrom(window.getAttributes());
		//This makes the dialog take up the full width
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);

		return dlg;
	}

	private void initDialog(View v) {
		// TODO Auto-generated method stub
		accountName = (EditText) v.findViewById(R.id.accountName);
		username = (EditText) v.findViewById(R.id.username);
		catagory = (Spinner) v.findViewById(R.id.catagorySpinner);
		password = (EditText) v.findViewById(R.id.password);
		saveButton = (Button) v.findViewById(R.id.saveButton);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayAdapter adapter = new ArrayAdapter(getActivity()
				.getApplicationContext(), R.layout.spinner_item,
				AppConstants.CATAGORY_SPINNER_ARRAY);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		catagory.setAdapter(adapter);

	}

	private void setDialogData() {
		// TODO Auto-generated method stub
		dataSource.setAccountName(accountName.getText().toString());
		dataSource.setUsername(username.getText().toString());
		dataSource.setCatagory(catagory.getSelectedItem().toString());
		dataSource.setPassword(password.getText().toString());

	}

}
