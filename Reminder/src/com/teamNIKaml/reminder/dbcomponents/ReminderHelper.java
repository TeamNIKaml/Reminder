package com.teamNIKaml.reminder.dbcomponents;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.teamNIKaml.reminder.fragment.Password;
import com.teamNIKaml.reminder.fragment.Reminder;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class ReminderHelper implements IDBHelper {

    private ReminderDataSource dataSource = ReminderDataSource
	    .getReminderDataSource();
    private DBHelper dbHelper;
    private List<ReminderDataSource> reminderList = new ArrayList<ReminderDataSource>();
    private Reminder reminder;

    public Reminder getPassword() {
	return reminder;
    }

    public void setReminder(Reminder reminder) {
	this.reminder = reminder;
    }

    @Override
    public boolean onCreate() {
	Log.e("DBTask onCreate", "doInBackground");
	dbHelper = new DBHelper(dataSource.getContext(), 1, Constants.DB_NAME,
		Constants.REMINDER_DB_QUERY);
	return true;

    }

    @Override
    public void insert() {
	// TODO Auto-generated method stub
	Log.e("insert reminderDbhelper", "doInBackground");
	new DBTask().execute("insert");

    }

    @Override
    public void update(String whereClause, String[] whereArgs) {
	// TODO Auto-generated method stub
	dataSource.setWhereClause(whereClause);
	dataSource.setWhereArgs(whereArgs);
	new DBTask().execute("update");
    }

    @Override
    public void delete(String where, String[] args) {
	// TODO Auto-generated method stub
	dataSource.setWhereClause(where);
	dataSource.setWhereArgs(args);
	new DBTask().execute("delete");

    }

    @Override
    public void select(String[] projection, String selection,
	    String[] selectionArgs, String sortOrder) {
	// TODO Auto-generated method stub
	dataSource.setWhereArgs(selectionArgs);
	dataSource.setWhereClause(selection);
	new DBTask().execute("select");

    }

    private class DBTask extends AsyncTask<String, Integer, String> {

	@Override
	protected String doInBackground(String... operation) {
	    // TODO Auto-generated method stub
	    if (operation[0].equalsIgnoreCase("insert")) {
		Log.e("DBTask inset", "doInBackground");
		onCreate();
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		database.insert(Constants.REMINDER_TABLE_NAME, null,
			dataSource.reminderToContentValues());
		database.close();

	    } else if (operation[0].equalsIgnoreCase("update")) {

		onCreate();
		Log.e("DBTask update", "doInBackground");
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		database.update(Constants.REMINDER_TABLE_NAME,
			dataSource.reminderToContentValues(),
			dataSource.getWhereClause(), dataSource.getWhereArgs());
		database.close();

	    } else if (operation[0].equalsIgnoreCase("delete")) {
		onCreate();
		Log.e("DBTask delete", "doInBackground");
		SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
		dataBase.delete(Constants.REMINDER_TABLE_NAME,
			dataSource.getWhereClause(), dataSource.getWhereArgs());
		dataBase.close();

	    } else if (operation[0].equalsIgnoreCase("select")) {
		ReminderDataSource dataSource1;
		onCreate();
		Log.e("DBTask select", "doInBackground");
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		Cursor cursor = database.query(Constants.REMINDER_TABLE_NAME,
			dataSource.getProjection(),
			dataSource.getWhereClause(), dataSource.getWhereArgs(),
			null, null, dataSource.getSortOrder());

		Log.e("count do in bacgrount cursor",
			String.valueOf(cursor.getColumnCount()));
		if (cursor.moveToFirst()) {

		    do {
			dataSource1 = dataSource
				.cursorToReminderDataSource(cursor);
			reminderList.add(dataSource1);
		    } while (cursor.moveToNext());

		    dataSource.setReminderList(reminderList);

		}
	    }

	    else {
		Log.e("Invalid db task", "invalid dsfsdfasdas");
	    }

	    return null;
	}

    }

}