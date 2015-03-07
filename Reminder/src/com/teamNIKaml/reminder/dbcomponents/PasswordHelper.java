package com.teamNIKaml.reminder.dbcomponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;

import com.teamNIKaml.reminder.fragment.Password;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.PasswordDataSource;

public class PasswordHelper implements IDBHelper {

	private PasswordDataSource dataSource = PasswordDataSource
			.getPasswordDataSource();
	private DBHelper dbHelper;
	private List<PasswordDataSource> passwordList ;
	private Password password;
	
	private static Handler myHandler;
	
	
	public void setHandler(Handler h){
		myHandler = h;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@Override
	public boolean onCreate() {

		dbHelper = new DBHelper(dataSource.getContext(), 1,
				Constants.DB_NAME_PASSWORD, Constants.PASSWORD_DB_QUERY);
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
		

/*		if (myHandler != null) {
			myHandler.sendEmptyMessage(1);
		}*/

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
				database.insert(Constants.PASSWORD_TABLE_NAME, null,
						dataSource.passwordToContentValues());
				database.close();

			} else if (operation[0].equalsIgnoreCase("update")) {

				onCreate();

				SQLiteDatabase database = dbHelper.getWritableDatabase();
				database.update(Constants.PASSWORD_TABLE_NAME,
						dataSource.passwordToContentValues(),
						whereClause, whereArgs);
				System.out.println("where clause :"+whereClause+"\n where args :"+Arrays.toString(whereArgs));
				database.close();

			} else if (operation[0].equalsIgnoreCase("delete")) {
				onCreate();

				SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
				dataBase.delete(Constants.PASSWORD_TABLE_NAME,
						whereClause, whereArgs);
				System.out.println("where clause :"+whereClause+"\n where args :"+Arrays.toString(whereArgs));
				dataBase.close();

			} else if (operation[0].equalsIgnoreCase("select")) {
				PasswordDataSource dataSource1;
				onCreate();

				SQLiteDatabase database = dbHelper.getReadableDatabase();
				Cursor cursor = database.query(Constants.PASSWORD_TABLE_NAME,
						projection,
						whereClause, whereArgs,
						null, null, sortOrder);
				
			passwordList	= new ArrayList<PasswordDataSource>();
			
			if(cursor.getCount() == 0)
			{
				dataSource1 = new PasswordDataSource();
				dataSource1.setAccountName("");
				dataSource1.setCatagory("");
				dataSource1.setPassword("");
				dataSource1.setUsername("");
				passwordList.add(dataSource1);
				dataSource.SetPasswordList(passwordList);
				database.close();
				return operation[0];
			}

				if (cursor.moveToFirst()) {

					do {
						dataSource1 = dataSource
								.cursorToPasswordsDataSource(cursor);
						passwordList.add(dataSource1);
					} while (cursor.moveToNext());

					dataSource.SetPasswordList(passwordList);
					database.close();

				}
				callHandler();
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
	
	private void callHandler(){
		if (myHandler != null) {
			myHandler.sendEmptyMessage(1);
		}
	}

}
