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
	private List<String> name; // header names
	// child data in format of header name, child name
	private HashMap<String, List<String>> note;
	private HashMap<String, List<String>> date;

	public ReminderAdaptor(Context context, List<String> projectname,
			HashMap<String, List<String>> status,
			HashMap<String, List<String>> date) {
		this._context = context;
		this.name = projectname;
		this.note = status;
		this.date = date;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.note.get(this.name.get(groupPosition)).get(childPosititon);
	}

	public Object getStatus(int groupPosition, int childPosititon) {
		return this.note.get(this.name.get(groupPosition)).get(childPosititon);
	}

	public Object getDate(int groupPosition, int childPosititon) {
		return this.date.get(this.name.get(groupPosition)).get(childPosititon);
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
			convertView = infalInflater.inflate(R.layout.list_item_reminder,
					parent,false);
		}

		TextView noteText = (TextView) convertView
				.findViewById(R.id.reminder_note);
		TextView dateText = (TextView) convertView
				.findViewById(R.id.reminder_date);

		dateText.setText(getStatus(groupPosition, childPosition).toString());
		noteText.setText(getDate(groupPosition, childPosition).toString());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.note.get(this.name.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.name.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.name.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headername = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_reminder,
					parent,false);
		}

		TextView nameTextView = (TextView) convertView
				.findViewById(R.id.reminder_header);
		nameTextView.setTypeface(null, Typeface.BOLD);
		nameTextView.setText(headername);

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
