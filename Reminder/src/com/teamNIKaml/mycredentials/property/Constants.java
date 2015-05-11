package com.teamNIKaml.mycredentials.property;

public class Constants {

	public static final String SHARED_PREFERENCE_NAME = "localdiskchildlocator";
	public static final String DB_NAME_REMINDER = "reminder.db";
	public static final String DB_NAME_PASSWORD = "password.db";
	
	public static final String KEY_OPTIMIZER = "password_teamNikaml";
	// Password db
	public static final String PASSWORD_TABLE_NAME = "password";
	public static final String PASSWORD_DB_QUERY = "CREATE TABLE IF NOT EXISTS "
			+ Constants.PASSWORD_TABLE_NAME
			+ "("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
			+ "catagory TEXT,"
			+ "accountName TEXT,"
			+ "username TEXT,"
			+ "password TEXT)";
	// reminder table

	public static final String REMINDER_TABLE_NAME = "reminder";
	public static final String REMINDER_DB_QUERY = "CREATE TABLE IF NOT EXISTS "
			+ Constants.REMINDER_TABLE_NAME
			+ "("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
			+ "name TEXT,"
			+ "date TEXT," + "note TEXT)";

	public static final String[] CATAGORY_SPINNER_ARRAY = { "Social", "E-mail",
			"E-Commerse", "Bank", "Others" };

	private Constants() {
	}

}
