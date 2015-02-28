package com.teamNIKaml.reminder.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.teamNIKaml.reminder.activityComponents.PasswordAdaptorEList;
import com.teamNIKaml.reminder.dbcomponents.PasswordHelper;
import com.teamNIKaml.reminder.dbcomponents.ReminderHelper;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.PasswordDataSource;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class Password extends Fragment {

	private PasswordAdaptorEList listAdapter;
	private ExpandableListView expListView;
	private List<String> catagory;
	private HashMap<String, List<String>> accountName;
	private HashMap<String, List<String>> username;
	private HashMap<String, List<String>> password;
	private Button addOppertunitiesButton;
	private LayoutInflater li;
	private PasswordDataSource dataSource = PasswordDataSource.getPasswordDataSource();
	private PasswordHelper dbHelper = new PasswordHelper();
	private List<PasswordDataSource> passwordList = new ArrayList<PasswordDataSource>();
	
	

	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
	

		li = inflater;

		View passwordView = inflater.inflate(R.layout.password_frag, container,
				false);
		init(passwordView);
		setListner();

		return passwordView;
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

				updateList();
				Toast.makeText(getActivity().getApplicationContext(),
						catagory.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// updateList();
				Toast.makeText(getActivity().getApplicationContext(),
						catagory.get(groupPosition) + " Collapsed",
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
						catagory.get(groupPosition)
								+ " : "
								+ accountName.get(catagory.get(groupPosition))
										.get(childPosition), Toast.LENGTH_SHORT)
						.show();

				dataSource.setAccountName(accountName.get(
						catagory.get(groupPosition)).get(childPosition));
				dataSource.setUsername(username
						.get(catagory.get(groupPosition)).get(childPosition));
				dataSource.setCatagory(catagory.get(groupPosition));
				dataSource.setPassword(password
						.get(catagory.get(groupPosition)).get(childPosition));

				EditPasswordDialog dialog = new EditPasswordDialog(li);
				dialog.show(getChildFragmentManager(), "AddPassword");

				return false;
			}
		});

		addOppertunitiesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddPasswordDialog dialog = new AddPasswordDialog(li);
				dialog.show(getChildFragmentManager(), "AddPassword");

			}
		});
	}

	private void init(View v) {
		Log.e("init", "init");
		catagory = new ArrayList<String>();
		accountName = new HashMap<String, List<String>>();
		username = new HashMap<String, List<String>>();
		password = new HashMap<String, List<String>>();

		catagory.add(Constants.CATAGORY_SPINNER_ARRAY[0]);
		catagory.add(Constants.CATAGORY_SPINNER_ARRAY[1]);
		catagory.add(Constants.CATAGORY_SPINNER_ARRAY[2]);
		catagory.add(Constants.CATAGORY_SPINNER_ARRAY[3]);
		catagory.add(Constants.CATAGORY_SPINNER_ARRAY[4]);

		expListView = (ExpandableListView) v.findViewById(R.id.passwordList);
		addOppertunitiesButton = (Button) v.findViewById(R.id.addButton);
		dataSource = PasswordDataSource.getPasswordDataSource();
		dataSource.setContext(getActivity().getApplicationContext());
		dbHelper.select(null, null, null, null);

		prepareListData();

		listAdapter = new PasswordAdaptorEList(getActivity()
				.getApplicationContext(), catagory, accountName, username,
				password);
		expListView.setAdapter(listAdapter);

		dbHelper.setPassword(this);

	}

	public void updateList() {
		prepareListData();
		listAdapter.notifyDataSetChanged();

	}

	private void prepareListData() {

		List<String> accountListSocial = new ArrayList<String>();
		List<String> accountListEmail = new ArrayList<String>();
		List<String> accountListCommerse = new ArrayList<String>();
		List<String> accountListBank = new ArrayList<String>();
		List<String> accountListother = new ArrayList<String>();

		List<String> userListSocial = new ArrayList<String>();
		List<String> userListEmail = new ArrayList<String>();
		List<String> userListCommerse = new ArrayList<String>();
		List<String> userListBank = new ArrayList<String>();
		List<String> userListOther = new ArrayList<String>();

		List<String> passListSocial = new ArrayList<String>();
		List<String> passListEmail = new ArrayList<String>();
		List<String> passListCommerse = new ArrayList<String>();
		List<String> passListBank = new ArrayList<String>();
		List<String> passListOther = new ArrayList<String>();

		passwordList = dataSource.getPasswordList();

		for (PasswordDataSource pass : passwordList) {

			if (pass.getCatagory().equalsIgnoreCase(
					Constants.CATAGORY_SPINNER_ARRAY[0])) {

				accountListSocial.add(pass.getAccountName());
				userListSocial.add(pass.getUsername());
				passListSocial.add(pass.getPassword());

			} else if (pass.getCatagory().equalsIgnoreCase(
					Constants.CATAGORY_SPINNER_ARRAY[1])) {

				accountListEmail.add(pass.getAccountName());
				userListEmail.add(pass.getUsername());
				passListEmail.add(pass.getPassword());

			} else if (pass.getCatagory().equalsIgnoreCase(
					Constants.CATAGORY_SPINNER_ARRAY[2])) {

				accountListCommerse.add(pass.getAccountName());
				userListCommerse.add(pass.getUsername());
				passListCommerse.add(pass.getPassword());
			} else if (pass.getCatagory().equalsIgnoreCase(
					Constants.CATAGORY_SPINNER_ARRAY[3])) {

				accountListBank.add(pass.getAccountName());
				userListBank.add(pass.getUsername());
				passListBank.add(pass.getPassword());
			}

			else {

				accountListother.add(pass.getAccountName());
				userListOther.add(pass.getUsername());
				passListOther.add(pass.getPassword());

			}

		}

		accountName.put(catagory.get(0), accountListSocial);
		accountName.put(catagory.get(1), accountListEmail);
		accountName.put(catagory.get(2), accountListCommerse);
		accountName.put(catagory.get(3), accountListCommerse);
		accountName.put(catagory.get(4), accountListCommerse);

		username.put(catagory.get(0), userListSocial);
		username.put(catagory.get(1), userListEmail);
		username.put(catagory.get(2), userListCommerse);
		username.put(catagory.get(3), userListCommerse);
		username.put(catagory.get(4), userListCommerse);

		password.put(catagory.get(0), passListSocial);
		password.put(catagory.get(1), passListEmail);
		password.put(catagory.get(2), passListCommerse);
		password.put(catagory.get(3), passListCommerse);
		password.put(catagory.get(4), passListCommerse);

	}

}
