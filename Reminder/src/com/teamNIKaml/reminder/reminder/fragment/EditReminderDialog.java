package com.teamNIKaml.reminder.reminder.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.IDBHelper;
import com.teamNIKaml.reminder.reminder.database.ReminderHelper;
import com.teamNIKaml.reminder.reminder.model.ReminderDataSource;

public class EditReminderDialog extends DialogFragment {

	private ReminderDataSource dataSource = ReminderDataSource
			.getReminderDataSource();

	private EditText reminderName, reminderNote;
	private DatePicker reminderDatePicker;
	private IDBHelper dbHelper = new ReminderHelper();
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

	public EditReminderDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@SuppressLint("InflateParams")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View v = li.inflate(R.layout.activity_edit_reminder, null);

		initDialog(v);

		final Dialog dlg = new AlertDialog.Builder(getActivity()).setView(v)

		.create();

		editButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				dataSource.setContext(getActivity().getApplicationContext());
				setDialogData();

				dbHelper.update(whereClause, whereArgs);
				//dbHelper.select(null, null, null, null);

				dlg.dismiss();

			}
		});
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				setDialogData();
				dbHelper.delete(whereClause, whereArgs);

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

	private void setDialogData() {
		// TODO Auto-generated method stub

		dataSource.setName(reminderName.getText().toString());
		dataSource.setNote(reminderNote.getText().toString());

		String dd = String.valueOf(reminderDatePicker.getDayOfMonth());
		String mm = String.valueOf(reminderDatePicker.getMonth() + 1);
		String yyyy = String.valueOf(reminderDatePicker.getYear());

		String date = dd + "-" + mm + "-" + yyyy;

		dataSource.setDate(date);

	}

	private void initDialog(View v) {
		// TODO Auto-generated method stub
		reminderName = (EditText) v.findViewById(R.id.reminderName);
		reminderNote = (EditText) v.findViewById(R.id.reminderNote);

		reminderDatePicker = (DatePicker) v
				.findViewById(R.id.reminderDatePicker);

		editButton = (Button) v.findViewById(R.id.editReminderButton);
		deleteButton = (Button) v.findViewById(R.id.deleteReminderButton);
		dataSource.setContext(getActivity().getApplicationContext());

		reminderName.setText(dataSource.getName());
		reminderNote.setText(dataSource.getNote());

		whereClause = "name=? AND note=? AND date=?";

		whereArgs = new String[3];

		whereArgs[0] = dataSource.getName();
		whereArgs[1] = dataSource.getNote();
		whereArgs[2] = dataSource.getDate();

	}

}
