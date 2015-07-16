package com.teamnikaml.mycredentials.dbcomponents;

public interface IDBHelper {

	public boolean onCreate();

	public void insert();

	public void update(String whereClause, String[] whereArgs);

	public void delete(String where, String[] args);

	public void select(String[] projection, String selection,
			String[] selectionArgs, String sortOrder);

}
