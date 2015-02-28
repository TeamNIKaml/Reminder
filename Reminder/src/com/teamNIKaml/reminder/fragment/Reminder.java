package com.teamNIKaml.reminder.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;

import com.teamNIKaml.reminder.activity.R;
import com.teamNIKaml.reminder.activityComponents.ReminderAdaptor;
import com.teamNIKaml.reminder.activityComponents.ReminderListAdaptor;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class Reminder extends Fragment {

	private ReminderListAdaptor listAdapter;
	private ListView listView;
	private String[] nameList ;
	private String[] noteList ;
	private String[] dateList;
	private static Reminder reminder;
	
	private Button addReminderButton,refreshButton;
	private LayoutInflater li;
	private View reminderView;

	private ReminderDataSource reminderDataSource = ReminderDataSource
			.getReminderDataSource();
	private ReminderHelper reminderHelper = new ReminderHelper();
	private List<ReminderDataSource> reminderList = new ArrayList<ReminderDataSource>();
	
	
	public static Reminder getReminder()
	{
		if(reminder == null)
			reminder = new Reminder();
		return reminder;
	}

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
			public void onItemClick(AdapterView<?> listview,  View v, int pos, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), nameList[pos], Toast.LENGTH_LONG).show();
				
			}
		});
	}
	
	
	
	public void resetListAdaptor()
	{

		
setDialogData();

		if(listAdapter == null)
			listAdapter = new ReminderListAdaptor(getActivity().getApplicationContext());
			

		listAdapter.setDate(dateList);
		listAdapter.setNote(noteList);
		listAdapter.setReminderName(nameList);
		
			
		
	}
	

	private void init(View v) {
		reminderDataSource.setContext(getActivity().getApplicationContext());
		reminderHelper.setReminder(this);

		reminderHelper.select(null, null, null, null);
		
		
	
		
		listView = (ListView) v.findViewById(R.id.ReminderList);
		addReminderButton = (Button) v.findViewById(R.id.addReminderButton);
		refreshButton = (Button)v.findViewById(R.id.refreshButton);
		
		
		
		
		
		resetListAdaptor();
		
		listView.setAdapter(listAdapter);
		
		
	}

	

	private void setDialogData() {
		// TODO Auto-generated method stub
		
		
		
		reminderList = reminderDataSource.getReminderList();
		
		int size = reminderList.size();
		
		nameList = new String[size];
		dateList = new String[size];
		noteList = new String[size];
		
	int i =0;
		for(ReminderDataSource  reminder : reminderList)
		{
			
			
			nameList[i]=reminder.getName();
			noteList[i]=reminder.getNote();
			dateList[i]=reminder.getDate();
			i++;
			
		
		}
		
	}

}
