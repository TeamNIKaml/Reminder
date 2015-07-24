package com.teamnikaml.mycredentials.reminder.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.reminder.adaptor.ReminderListAdaptor;
import com.teamnikaml.mycredentials.reminder.database.ReminderHelper;
import com.teamnikaml.mycredentials.reminder.model.ReminderDataSource;

public class Reminder extends Fragment {

	private ReminderListAdaptor listAdapter;
	private ListView listView;
	private String[] nameList;
	private String[] noteList;
	private String[] dateList;

	private Button addReminderButton, refreshButton;
	private LayoutInflater li;
	private View reminderView;

	private ReminderDataSource reminderDataSource = ReminderDataSource
			.getReminderDataSource();
	private ReminderHelper reminderHelper = new ReminderHelper();
	private List<ReminderDataSource> reminderList = new ArrayList<ReminderDataSource>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		li = inflater;

		reminderView = inflater.inflate(R.layout.reminder_frag, container,
				false);
		init(reminderView);
		setListner();

		return reminderView;
	}

	private void setListner() {
		// TODO Auto-generated method stub

		addReminderButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddReminderDialog dialog = new AddReminderDialog(li);
				dialog.show(getChildFragmentManager(), "Add Reminder");

				resetListAdaptor();
				reminderHelper.setHandler(myHandler);
			}

		});

		refreshButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				reminderHelper.select(null, null, null, null);
				resetListAdaptor();

			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listview, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				

				reminderDataSource.setName(nameList[pos]);
				reminderDataSource.setDate(dateList[pos]);
				reminderDataSource.setNote(noteList[pos]);

				EditReminderDialog dialog = new EditReminderDialog(li);
				dialog.show(getFragmentManager(), "Edit");

			}
		});
	}

	public void resetListAdaptor() {

		if (listAdapter == null)
			listAdapter = new ReminderListAdaptor(getActivity()
					.getApplicationContext());

		setDialogData();
		listAdapter.setDate(dateList);
		listAdapter.setNote(noteList);
		listAdapter.setReminderName(nameList);

		listAdapter.notifyDataSetChanged();

	}

	private void init(View v) {
		reminderDataSource.setContext(getActivity().getApplicationContext());
		reminderHelper.setReminder(this);
		reminderHelper.setHandler(myHandler);
		reminderHelper.select(null, null, null, null);

		listView = (ListView) v.findViewById(R.id.ReminderList);
		addReminderButton = (Button) v.findViewById(R.id.addReminderButton);
		refreshButton = (Button) v.findViewById(R.id.refreshButton);

		resetListAdaptor();

		listView.setAdapter(listAdapter);

	}

	private void setDialogData() {
		// TODO Auto-generated method stub

		reminderList = reminderDataSource.getReminderList();

		int size = reminderList.size();

		int i = 0;

		nameList = new String[size];
		dateList = new String[size];
		noteList = new String[size];
		for (ReminderDataSource reminder : reminderList) {

			nameList[i] = reminder.getName();
			noteList[i] = reminder.getNote();
			dateList[i] = reminder.getDate();
			i++;

		}

	}

	private final Handler myHandler = new Handler() {
	    public void handleMessage(Message msg) {
			resetListAdaptor();
	    }
	};
	
}
