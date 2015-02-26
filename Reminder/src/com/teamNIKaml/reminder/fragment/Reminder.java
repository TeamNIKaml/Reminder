package com.teamNIKaml.reminder.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.activityComponents.ReminderAdaptor;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class Reminder extends Fragment {

	private ReminderAdaptor listAdapter;
	private ExpandableListView expListView;
	private List<String> name = new ArrayList<>();
	private HashMap<String, List<String>> note = new HashMap<>();
	private HashMap<String, List<String>> date = new HashMap<>();
	private Button addReminderButton;
	private LayoutInflater li;
	

	private ReminderDataSource dataSource = ReminderDataSource
			.getReminderDataSource();
	private ReminderHelper dbHelper = new ReminderHelper();
	private List<ReminderDataSource> reminderList = new ArrayList<ReminderDataSource>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		li = inflater;

		View reminderView = inflater.inflate(R.layout.reminder_frag, container,
				false);
		init(reminderView);
		setListner();

		return reminderView;
	}

	private void setListner() {
		// TODO Auto-generated method stub
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getActivity().getApplicationContext(),
						name.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getActivity().getApplicationContext(),
						name.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getActivity().getApplicationContext(),
						name.get(groupPosition)
								+ " : "
								+ note.get(name.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});

		addReminderButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddReminderDialog dialog = new AddReminderDialog(li);
				dialog.show(getChildFragmentManager(), "Add Reminder");
										
			

			}
		});
	}

	private void init(View v) {
		
		setDialogData();
		
		expListView = (ExpandableListView) v.findViewById(R.id.ReminderList);
		addReminderButton = (Button) v.findViewById(R.id.addReminderButton);
		listAdapter = new ReminderAdaptor(
				getActivity().getApplicationContext(), name, note, date);
		expListView.setAdapter(listAdapter);
		dataSource.setContext(getActivity().getApplicationContext());
		dbHelper.setReminder(this);

	}

	private void setDialogData() {
		// TODO Auto-generated method stub
		
		reminderList = dataSource.getReminderList();
		List<String> noteList;
		List<String> dateList ;
	
		for(ReminderDataSource reminder : reminderList)
		{
			 noteList = new ArrayList<String>();
				dateList = new ArrayList<String>();
			
			name.add(reminder.getName());
			noteList.add(reminder.getNote());
			dateList.add(reminder.getDate());
			
			note.put(reminder.getName(), noteList);
			date.put(reminder.getName(), dateList);
		}
		
	}

}
