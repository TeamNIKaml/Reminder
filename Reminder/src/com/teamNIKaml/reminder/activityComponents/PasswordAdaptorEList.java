package com.teamNIKaml.reminder.activityComponents;



import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.teamNIKaml.reminder.activity.R;

public class PasswordAdaptorEList extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> catagory; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> accountName;
	private HashMap<String, List<String>> username;
	private HashMap<String, List<String>> password;

	public List<String> getCatagory() {
		return catagory;
	}


	public void setCatagory(List<String> catagory) {
		notifyDataSetInvalidated();
		this.catagory = catagory;
		notifyDataSetChanged();
	}


	public HashMap<String, List<String>> getAccountName() {
		return accountName;
	}


	public void setAccountName(HashMap<String, List<String>> accountName) {
		notifyDataSetInvalidated();
		this.accountName = accountName;
		notifyDataSetChanged();
	}


	public HashMap<String, List<String>> getUsername() {
		return username;
	}


	public void setUsername(HashMap<String, List<String>> username) {
		notifyDataSetInvalidated();
		this.username = username;
		notifyDataSetChanged();
	}


	public HashMap<String, List<String>> getPassword() {
		return password;
	}


	public void setPassword(HashMap<String, List<String>> password) {
		notifyDataSetInvalidated();
		this.password = password;
		notifyDataSetChanged();
	}


	public PasswordAdaptorEList(Context context, List<String> catagory,
			HashMap<String, List<String>> accountName,HashMap<String, List<String>> username,
			HashMap<String, List<String>> password) {
		this._context = context;
		this.catagory = catagory;
		this.accountName = accountName;
		this.username = username;
		this.password = password;
	}

	
	public Object getPassword(int groupPosition, int childPosititon) {
		return this.password.get(this.catagory.get(groupPosition))
				.get(childPosititon);
	}
	
	public Object getAccountName(int groupPosition, int childPosititon) {
		return this.accountName.get(this.catagory.get(groupPosition))
				.get(childPosititon);
	} 
	
	
	public Object getUserName(int groupPosition, int childPosititon) {
		return this.username.get(this.catagory.get(groupPosition))
				.get(childPosititon);
	} 

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item_password, null);
		}

	 	TextView accountNameText = (TextView) convertView.findViewById(R.id.accountName);
		TextView userNameText = (TextView) convertView.findViewById(R.id.username);
		TextView passwordText =(TextView)convertView.findViewById(R.id.password);
		
		accountNameText.setText(getAccountName(groupPosition, childPosition).toString());
		userNameText.setText(getUserName(groupPosition, childPosition).toString());
		passwordText.setText(getPassword(groupPosition, childPosition).toString());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		
		
		return this.accountName.get(this.catagory.get(groupPosition))
				.size();
		
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.catagory.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.catagory.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_password, null);
		}

		TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}


	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

}
