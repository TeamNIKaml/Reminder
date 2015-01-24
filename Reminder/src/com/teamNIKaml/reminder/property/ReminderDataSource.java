package com.teamNIKaml.reminder.property;

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
	private String whereClause;
	private String[] whereArgs;
	private String sortOrder;
	private String[] projection;
	private ReminderDataSource dataSource;
	private static ReminderDataSource reminderDataSource;
	private List<ReminderDataSource> reminderList = new ArrayList<ReminderDataSource>();

	public static ReminderDataSource getPasswordDataSource() {

		if (reminderDataSource == null)
			reminderDataSource = new ReminderDataSource();
		return reminderDataSource;
	}

	private ReminderDataSource() {

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

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public String[] getWhereArgs() {
		return whereArgs;
	}

	public void setWhereArgs(String[] whereArgs) {
		this.whereArgs = whereArgs;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String[] getProjection() {
		return projection;
	}

	public void setProjection(String[] projection) {
		this.projection = projection;
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
		return values;
	}

	public ReminderDataSource cursorToReminderDataSource(Cursor cursor) {

		dataSource = new ReminderDataSource();

		dataSource.setName(cursor.getString(1));
		dataSource.setNote(cursor.getString(2));
		dataSource.setDate(cursor.getString(1));
		return dataSource;

	}

}
