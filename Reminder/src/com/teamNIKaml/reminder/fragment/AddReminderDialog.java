package com.teamNIKaml.reminder.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.IDBHelper;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

@SuppressLint("InflateParams")
public class AddReminderDialog extends DialogFragment {

	private ReminderDataSource dataSource = ReminderDataSource
			.getReminderDataSource();
	private EditText reminderName, reminderDate;
	private EditText reminderNote;
	private IDBHelper dbHelper = new ReminderHelper();
	private Button saveButton;

	private LayoutInflater li;

	public AddReminderDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View v = li.inflate(R.layout.activity_add_reminder, null);
		;

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
				dbHelper.select(null, null, null, null);
				dlg.dismiss();

			}
		});

		return dlg;
	}

	private void initDialog(View v) {
		// TODO Auto-generated method stub
		dataSource.setContext(getActivity().getApplicationContext());
		reminderNote = (EditText) v.findViewById(R.id.reminderNote);
		reminderDate = (EditText) v.findViewById(R.id.reminderDate);
		reminderName = (EditText) v.findViewById(R.id.reminderTitle);
		saveButton = (Button) v.findViewById(R.id.saveButton);

	}

	private void setDialogData() {
		// TODO Auto-generated method stub
		dataSource.setName(reminderName.getText().toString());
		dataSource.setDate(reminderDate.getText().toString());
		dataSource.setNote(reminderNote.getText().toString());

	}

}
