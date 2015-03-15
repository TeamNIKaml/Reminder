package com.teamNIKaml.reminder.reminder.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ReminderDataSource {

	private String name;
	private String note;
	private String date;
	private Context context;

	private ReminderDataSource dataSource;
	private static ReminderDataSource reminderDataSource;
	private List<ReminderDataSource> reminderList = new ArrayList<ReminderDataSource>();

	public static ReminderDataSource getReminderDataSource() {

		if (reminderDataSource == null)
			reminderDataSource = new ReminderDataSource();
		return reminderDataSource;
	}

	

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ReminderDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(ReminderDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<ReminderDataSource> getReminderList() {
		return reminderList;
	}

	public void setReminderList(List<ReminderDataSource> reminderList) {
		this.reminderList = reminderList;
	}

	public ContentValues reminderToContentValues() {
		ContentValues values = new ContentValues();
		values.put("name", this.name);
		values.put("note", this.note);
		values.put("date", this.date);

		System.out.println("Content value size:" + values.size());
		return values;
	}

	public ReminderDataSource cursorToReminderDataSource(Cursor cursor) {

		dataSource = new ReminderDataSource();

		dataSource.setName(cursor.getString(1));
		dataSource.setNote(cursor.getString(3));
		dataSource.setDate(cursor.getString(2));
		return dataSource;

	}

}
