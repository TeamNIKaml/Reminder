package com.teamNIKaml.reminder.activityComponents;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.teamNIKaml.reminder.activity.R;

public class ReminderAdaptor extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> projectTitle; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> status;
	private HashMap<String, List<String>> date;

	public ReminderAdaptor(Context context, List<String> projectTitle,
			HashMap<String, List<String>> status,HashMap<String, List<String>> date) {
		this._context = context;
		this.projectTitle = projectTitle;
		this.status = status;
		this.date = date;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.status.get(this.projectTitle.get(groupPosition))
				.get(childPosititon);
	}
	
	public Object getStatus(int groupPosition, int childPosititon) {
		return this.status.get(this.projectTitle.get(groupPosition))
				.get(childPosititon);
	} 
	
	
	public Object getDate(int groupPosition, int childPosititon) {
		return this.date.get(this.projectTitle.get(groupPosition))
				.get(childPosititon);
	} 

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item_reminder, null);
		}

	 	TextView statusText = (TextView) convertView.findViewById(R.id.myOppertunity_status);
		TextView dateText = (TextView) convertView.findViewById(R.id.myOppertunity_date);
		
		dateText.setText(getStatus(groupPosition, childPosition).toString());
		statusText.setText(getDate(groupPosition, childPosition).toString());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.status.get(this.projectTitle.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.projectTitle.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.projectTitle.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_reminder, null);
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

}
