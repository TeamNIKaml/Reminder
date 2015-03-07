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
	private Context context;
	private String whereClause;
	private String[] whereArgs;
	private String sortOrder;
	private String[] projection;
	private PasswordDataSource dataSource;
	private static PasswordDataSource passwordDataSource;
	private List<PasswordDataSource> passwordList = new ArrayList<PasswordDataSource>();
	
	private int rowcount;

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

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

		return "Catagory : " + getCatagory() + "  Account Name:"
				+ getAccountName() + "  Username:" + getUsername()
				+ "  Password:" + getPassword();

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



	public ContentValues passwordToContentValues() {
		ContentValues values = new ContentValues();
		values.put("catagory", this.catagory);
		values.put("accountName", this.accountName);
		values.put("username", this.username);
		values.put("password", this.password);
		return values;
	}

	public PasswordDataSource cursorToPasswordsDataSource(Cursor cursor) {

		dataSource = new PasswordDataSource();

		dataSource.setCatagory(cursor.getString(1));
		dataSource.setAccountName(cursor.getString(2));
		dataSource.setUsername(cursor.getString(3));
		dataSource.setPassword(cursor.getString(4));
		// setDataSource(this);
		return dataSource;
		// return ;

	}

	public List<PasswordDataSource> getPasswordList() {
		return passwordList;
	}

	public void SetPasswordList(List<PasswordDataSource> passwordList) {
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
