package com.teamNIKaml.reminder.dbcomponents;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;

import com.teamNIKaml.reminder.fragment.Reminder;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class ReminderHelper implements IDBHelper {

	private ReminderDataSource dataSource = ReminderDataSource
			.getReminderDataSource();

	private DBHelper dbHelper;
	private List<ReminderDataSource> reminList;
	private Reminder reminder;
	private static Handler myHandler;

	public void setHandler(Handler h){
		myHandler = h;
	}
	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	@Override
	public boolean onCreate() {

		dbHelper = new DBHelper(dataSource.getContext(), 1,
				Constants.DB_NAME_REMINDER, Constants.REMINDER_DB_QUERY);
		return true;

	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub

		new DBTask().execute("insert");

	}

	@Override
	public void update(String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub

		DBTask task = new DBTask();

		task.whereArgs = whereArgs;
		task.whereClause = whereClause;

		task.execute("update");
	}

	@Override
	public void delete(String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		DBTask task = new DBTask();

		task.whereArgs = whereArgs;
		task.whereClause = whereClause;

		task.execute("delete");

	}

	@Override
	public void select(String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub

		DBTask task = new DBTask();

		task.whereArgs = selectionArgs;
		task.whereClause = selection;
		task.projection = projection;
		task.sortOrder = sortOrder;

		new DBTask().execute("select");
		myHandler.sendEmptyMessage(1);
	}

	private class DBTask extends AsyncTask<String, Integer, String> {

		private String whereClause;
		private String[] whereArgs;
		private String[] projection;
		private String sortOrder;

		@Override
		protected String doInBackground(String... operation) {
			// TODO Auto-generated method stub

			if (operation[0].equalsIgnoreCase("insert")) {

				onCreate();
				SQLiteDatabase database = dbHelper.getWritableDatabase();
				database.insert(Constants.REMINDER_TABLE_NAME, null,
						dataSource.reminderToContentValues());
				database.close();

			} else if (operation[0].equalsIgnoreCase("update")) {

				onCreate();

				SQLiteDatabase database = dbHelper.getWritableDatabase();
				database.update(Constants.REMINDER_TABLE_NAME,
						dataSource.reminderToContentValues(), whereClause,
						whereArgs);

				database.close();

			} else if (operation[0].equalsIgnoreCase("delete")) {
				onCreate();

				SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
				dataBase.delete(Constants.REMINDER_TABLE_NAME, whereClause,
						whereArgs);

				dataBase.close();

			} else if (operation[0].equalsIgnoreCase("select")) {
				ReminderDataSource dataSource1;

				onCreate();

				SQLiteDatabase database = dbHelper.getReadableDatabase();
				Cursor cursor = database.query(Constants.REMINDER_TABLE_NAME,
						projection, whereClause, whereArgs, null, null,
						sortOrder);
				
				reminList = new ArrayList<ReminderDataSource>();

				if (cursor.getCount() == 0) {
					dataSource1 = new ReminderDataSource();
					dataSource1.setDate("");
					dataSource1.setName("");
					dataSource1.setNote("");
					reminList.add(dataSource1);
					dataSource.setReminderList(reminList);
					database.close();
					return operation[0];
				}

				if (cursor.moveToFirst()) {

					do {
						dataSource1 = dataSource
								.cursorToReminderDataSource(cursor);
						reminList.add(dataSource1);
					} while (cursor.moveToNext());

					dataSource.setReminderList(reminList);
					database.close();
				}
			}

			return operation[0];
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (!result.equals("select")) {
				select(null, null, null, null);
			}
		}
		
		

	}

}