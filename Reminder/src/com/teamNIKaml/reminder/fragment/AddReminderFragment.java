package com.teamNIKaml.reminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.dbcomponents.IDBHelper;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class AddReminderFragment extends Fragment {

	private LayoutInflater li;
	private ReminderDataSource dataSource = ReminderDataSource
			.getReminderDataSource();
	private EditText reminderName, reminderDate;
	private EditText reminderNote;
	private IDBHelper dbHelper = new ReminderHelper();
	private Button saveButton;

	public AddReminderFragment(LayoutInflater li) {
		this.li = li;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		li = inflater;

		View reminderView = inflater.inflate(R.layout.reminder_frag,
				container, false);
	//	init(reminderView);

		return reminderView;
	}

	private void init(View v) {
		// TODO Auto-generated method stub
		dataSource.setContext(getActivity().getApplicationContext());
		reminderNote = (EditText) v.findViewById(R.id.reminderNote);
		reminderDate = (EditText) v.findViewById(R.id.reminderDate);
		reminderName = (EditText) v.findViewById(R.id.reminderTitle);
		saveButton = (Button) v.findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}
