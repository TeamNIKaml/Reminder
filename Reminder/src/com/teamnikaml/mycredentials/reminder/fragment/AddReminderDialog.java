package com.teamnikaml.mycredentials.reminder.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.dbcomponents.IDBHelper;
import com.teamnikaml.mycredentials.reminder.database.ReminderHelper;
import com.teamnikaml.mycredentials.reminder.model.ReminderDataSource;

@SuppressLint("InflateParams")
public class AddReminderDialog extends DialogFragment {

	private ReminderDataSource dataSource = ReminderDataSource
			.getReminderDataSource();
	private EditText reminderName;
	private EditText reminderNote;
	private IDBHelper dbHelper = new ReminderHelper();
	private Button saveButton;
	private DatePicker reminderDatePicker;

	private LayoutInflater li;

	public AddReminderDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View v = li.inflate(R.layout.activity_add_reminder, null);

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
			//	dbHelper.select(null, null, null, null);

				dlg.dismiss();

				Toast.makeText(getActivity().getApplicationContext(),
						"Reminder saved", Toast.LENGTH_LONG).show();

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
		dataSource.setContext(getActivity().getApplicationContext());
		reminderNote = (EditText) v.findViewById(R.id.reminderNote);
		reminderName = (EditText) v.findViewById(R.id.reminderTitle);

		saveButton = (Button) v.findViewById(R.id.saveButton);

		reminderDatePicker = (DatePicker) v
				.findViewById(R.id.reminderDatePicker);

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

}
