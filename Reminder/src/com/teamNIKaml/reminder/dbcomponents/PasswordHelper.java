package com.teamNIKaml.reminder.dbcomponents;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.teamNIKaml.reminder.fragment.Password;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.PasswordDataSource;

public class PasswordHelper implements IDBHelper {

	private PasswordDataSource dataSource = PasswordDataSource
			.getPasswordDataSource();
	private DBHelper dbHelper;
	private List<PasswordDataSource> oppertunitieslist = new ArrayList<PasswordDataSource>();
	private Password password;

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@Override
	public boolean onCreate() {
		Log.e("DBTask onCreate", "doInBackground");
		dbHelper = new DBHelper(dataSource.getContext(), 1, Constants.DB_NAME,
				Constants.PASSWORD_DB_QUERY);
		return true;

	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		Log.e("insert Passwordhelper", "doInBackground");
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
		//new DBTask().execute("select");
		
		

			onCreate();
			Log.e("DBTask select", "doInBackground");
			SQLiteDatabase database = dbHelper.getReadableDatabase();
			Cursor cursor = database.query(Constants.PASSWORD_TABLE_NAME,
					dataSource.getProjection(),
					dataSource.getWhereClause(), dataSource.getWhereArgs(),
					null, null, dataSource.getSortOrder());
			int count = cursor.getColumnCount();
			Log.e("count", String.valueOf(count));
			if (cursor.moveToFirst()) {

				do {
					oppertunitieslist.add(dataSource
							.cursorToPasswordsDataSource(cursor));
				} while (cursor.moveToNext());

			
			
			Log.e("passwordList size", String.valueOf(oppertunitieslist.size()));
			dataSource.setOppertunitieslist(oppertunitieslist);

		}
		
		

	}

	private class DBTask extends AsyncTask<String, Integer, String> {

	

		@Override
		protected String doInBackground(String... operation) {
			// TODO Auto-generated method stub
			if (operation[0].equalsIgnoreCase("insert")) {
				Log.e("DBTask inset", "doInBackground");
				onCreate();
				SQLiteDatabase database = dbHelper.getWritableDatabase();
				database.insert(Constants.PASSWORD_TABLE_NAME, null,
						dataSource.passwordToContentValues());
				database.close();

			} else if (operation[0].equalsIgnoreCase("update")) {

				onCreate();
				Log.e("DBTask update", "doInBackground");
				SQLiteDatabase database = dbHelper.getWritableDatabase();
				database.update(Constants.PASSWORD_TABLE_NAME,
						dataSource.passwordToContentValues(),
						dataSource.getWhereClause(), dataSource.getWhereArgs());
				database.close();

			} else if (operation[0].equalsIgnoreCase("delete")) {
				onCreate();
				Log.e("DBTask delete", "doInBackground");
				SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
				dataBase.delete(Constants.PASSWORD_TABLE_NAME,
						dataSource.getWhereClause(), dataSource.getWhereArgs());
				dataBase.close();

			} 

			else {
				Log.e("Invalid db task", "invalid dsfsdfasdas");
			}

			return null;
		}

	}

}
