package com.teamNIKaml.reminder.dbcomponents;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// private String db;

	private String sql_create;

	public DBHelper(Context context, int version, String db, String sql_create) {
		super(context, db, null, version);
		// this.db = db;
		this.sql_create = sql_create;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql_create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
