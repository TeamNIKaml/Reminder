package com.teamNIKaml.reminder.property;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class PasswordDataSource {

	private String catagory;
	private String accountName;
	private String username;
	private String password;

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Context context;
	private String whereClause;
	private String[] whereArgs;
	private List<PasswordDataSource> passwordList = new ArrayList<PasswordDataSource>();
	private String[] projection;
	private static PasswordDataSource passwordDataSource;
	private String sortOrder;

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		return "";

	}

	public Context getContext() {
		return this.context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public static PasswordDataSource getPasswordDataSource() {

		if (passwordDataSource == null)
			passwordDataSource = new PasswordDataSource();
		return passwordDataSource;
	}

	public static void setPasswordDataSource(PasswordDataSource password) {

		passwordDataSource = password;
	}

	private PasswordDataSource() {

	}

	public ContentValues passwordToContentValues() {
		ContentValues values = new ContentValues();
		values.put("catagory", this.catagory);
		values.put("accountName", this.accountName);
		values.put("username", this.username);
		values.put("password", this.password);
		return values;
	}

	public PasswordDataSource cursorToPasswordsDataSource(Cursor cursor) {

		setCatagory(cursor.getString(1));
		setAccountName(cursor.getString(2));
		setUsername(cursor.getString(3));
		setPassword(cursor.getString(4));
		return this;

	}

	public List<PasswordDataSource> getOppertunitieslist() {
		return passwordList;
	}

	public void setOppertunitieslist(List<PasswordDataSource> passwordList) {
		this.passwordList = passwordList;
	}

	public String[] getProjection() {
		return projection;
	}

	public void setProjection(String[] projection) {
		this.projection = projection;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
