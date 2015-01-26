package com.teamNIKaml.reminder.property;

public class Constants {

	public static final String DB_NAME = "reminder.db";
	//Password db 
	public static final String PASSWORD_TABLE_NAME = "password";
	public static final String PASSWORD_DB_QUERY = "CREATE TABLE IF NOT EXISTS "
			+ Constants.PASSWORD_TABLE_NAME
			+ "("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
			+ "catagory TEXT,"
			+ "accountName TEXT,"
			+ "username TEXT,"
			+ "password TEXT)";
	//reminder table
	
	public static final String REMINDER_TABLE_NAME = "REMINDER";
	public static final String REMINDER_DB_QUERY = "CREATE TABLE IF NOT EXISTS "
			+ Constants.REMINDER_TABLE_NAME
			+ "("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
			+ "reminderName TEXT,"
			+ "date TEXT,"
			+ "note TEXT)";
	
	public static final String[] CATAGORY_SPINNER_ARRAY = { "Social", "E-mail", "E-Commerse",
			"Bank", "Others" };

	private Constants() {
	}

}
