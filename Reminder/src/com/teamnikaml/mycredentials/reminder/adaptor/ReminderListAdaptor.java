package com.teamnikaml.mycredentials.reminder.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teamnikaml.mycredentials.R;

public class ReminderListAdaptor extends BaseAdapter {

	private Context context;
	private String reminderName[];
	private String note[];
	private String date[];
	private TextView reminderNameTextView, reminderNoteTextView,
			reminderDateTextView;

	public String[] getDate() {
		return date;
	}

	public void setDate(String[] date) {
		this.date = date;
		notifyDataSetChanged();
	}

	public String[] getReminderName() {

		return reminderName;

	}

	public void setReminderName(String[] reminderName) {
		this.reminderName = reminderName;
		notifyDataSetChanged();
	}

	public String[] getNote() {
		return note;

	}

	public void setNote(String[] note) {
		this.note = note;
		notifyDataSetChanged();
	}

	public ReminderListAdaptor(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return reminderName.length;
	}

	@Override
	public Object getItem(int postion) {
		// TODO Auto-generated method stub
		return reminderName[postion];
	}

	@Override
	public long getItemId(int postion) {
		// TODO Auto-generated method stub
		return reminderName[postion].hashCode();
	}

	@Override
	public View getView(int postion, View convertview, ViewGroup group) {
		// TODO Auto-generated method stub
		View v = convertview;
		if (v == null) {
			LayoutInflater li = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.reminder_list_adaptor, group, false);
		}
		reminderNameTextView = (TextView) v.findViewById(R.id.reminderName);
		reminderNoteTextView = (TextView) v.findViewById(R.id.reminder_note);
		reminderDateTextView = (TextView) v.findViewById(R.id.reminder_date);
		reminderNameTextView.setText(reminderName[postion]);
		reminderNoteTextView.setText(note[postion]);
		reminderDateTextView.setText(date[postion]);

		return v;
	}

}
