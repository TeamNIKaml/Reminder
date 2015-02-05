package com.teamNIKaml.reminder.test.unitTest.sqlLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.teamNIKaml.reminder.dbcomponents.DBHelper;
import com.teamNIKaml.reminder.property.Constants;
import com.teamNIKaml.reminder.property.ReminderDataSource;

public class ReminderUnitTest extends AndroidTestCase {

    private static final String TEST_FILE_PREFIX = "test_";
    private DBHelper helper;
    private SQLiteDatabase db;
    private ReminderDataSource dataSource = ReminderDataSource
	    .getReminderDataSource();

    @Override
    protected void setUp() throws Exception {
	super.setUp();

	RenamingDelegatingContext context = new RenamingDelegatingContext(
		getContext(), TEST_FILE_PREFIX);

	helper = new DBHelper(context, 1, Constants.DB_NAME,
		Constants.REMINDER_DB_QUERY);

	assertNotNull(helper);

    }

    @Override
    protected void tearDown() throws Exception {

	super.tearDown();
    }

    public void testPreConditions() {
	assertNotNull(helper);
    }

    private void InsertSmallData() {

	System.out
		.println("################### insert samll #########################");
	db = helper.getWritableDatabase();
	dataSource.setName("Nikhil");
	dataSource.setNote("note");
	dataSource.setDate("note");
	db.insert(Constants.REMINDER_TABLE_NAME, null,
		dataSource.reminderToContentValues());
	db.close();
    }

    private void selectAllSmall() {
	db = helper.getReadableDatabase();
	Cursor c = db.query(Constants.REMINDER_TABLE_NAME, null, null, null,
		null, null, null);
	System.out.println("################### Small selctall count"
		+ c.getCount());
	db.close();
    }

    private void deletesmall() {
	System.out
		.println("################### delete small ######################");
	db = helper.getWritableDatabase();
	db.delete(Constants.REMINDER_TABLE_NAME, null, null);
	db.close();
    }

    private void updateSmall() {
	db = helper.getWritableDatabase();
	System.out
		.println("################## update small ######################");
	dataSource.setName("Nikhil V");
	dataSource.setNote("note");
	dataSource.setDate("note");
	db.update(Constants.REMINDER_TABLE_NAME,
		dataSource.reminderToContentValues(), "_id = ?",
		new String[] { "1" });

	db.close();
    }

    @SmallTest
    public void testSmall() {

	InsertSmallData();
	selectAllSmall();
	updateSmall();
	selectAllSmall();

	deletesmall();
	selectAllSmall();

    }

    public void MediumInsert() {

	System.out
		.println("################### Insert Medium ##########################3");
	db = helper.getWritableDatabase();
	for (int i = 0; i < 10; i++) {
	    dataSource.setName("Nikhil V " + String.valueOf(i));
	    dataSource.setNote("note"+ String.valueOf(i));
		dataSource.setDate("note"+ String.valueOf(i));

	    db.insert(Constants.REMINDER_TABLE_NAME, null,
		    dataSource.reminderToContentValues());
	}

	db.close();

    }

    public void MediumSelectAll() {

	db = helper.getReadableDatabase();
	Cursor c = db.query(Constants.REMINDER_TABLE_NAME, null, null, null,
		null, null, null);
	System.out.println("##########selectAll Medium count" + c.getCount());
	db.close();

    }

    public void MediumUpdate() {

	System.out
		.println("#########################@ Medium update ####################3");
	db = helper.getWritableDatabase();

	for (int i = 0; i < 10; i++) {
	    dataSource.setName("Nikhil  " + String.valueOf(i));
	    dataSource.setNote("note"+ String.valueOf(i));
	  		dataSource.setDate("note"+ String.valueOf(i));
	    db.update(Constants.REMINDER_TABLE_NAME,
		    dataSource.reminderToContentValues(), "_id = ?",
		    new String[] { String.valueOf(i) });
	}

	db.close();

    }

    public void MediumSelectOne() {

	db = helper.getReadableDatabase();
	Cursor c = db.query(Constants.REMINDER_TABLE_NAME, null, "_id = ?",
		new String[] { "7" }, null, null, null);
	c.moveToFirst();
	System.out.println("############# Select one" + c.getString(1));
	db.close();

    }

    public void MediumDeleteOne() {

	System.out
		.println("#################### Medium Delete one ##################");
	db = helper.getWritableDatabase();
	db.delete(Constants.REMINDER_TABLE_NAME, "_id = ?",
		new String[] { "7" });
	db.close();

    }

    public void MediumDeleteAll() {

	System.out
		.println("################### Medium delete all ###################");
	db = helper.getWritableDatabase();
	db.delete(Constants.REMINDER_TABLE_NAME, null, null);
	db.close();

    }

    @MediumTest
    public void testMedium() {
	MediumInsert();
	MediumSelectAll();
	MediumUpdate();
	MediumSelectOne();
	MediumDeleteOne();
	MediumSelectAll();
	MediumDeleteAll();
	MediumSelectAll();

    }

    public void InsertLarge() {

	System.out
		.println("#################################Insert Large#####################");
	db = helper.getWritableDatabase();
	for (int i = 0; i < 100; i++) {
	    dataSource.setName("Nikhil V " + String.valueOf(i));
	    dataSource.setNote("note"+ String.valueOf(i));
	  		dataSource.setDate("note"+ String.valueOf(i));
	    db.insert(Constants.REMINDER_TABLE_NAME, null,
		    dataSource.reminderToContentValues());
	}

	db.close();
    }

    public void LargeSelectAll() {

	db = helper.getReadableDatabase();
	Cursor c = db.query(Constants.REMINDER_TABLE_NAME, null, null, null,
		null, null, null);
	System.out
		.println("############### Select All Large ########################"
			+ c.getCount());
	db.close();

    }

    public void LargeUpdate() {
	db = helper.getWritableDatabase();

	System.out.println("##########update Large###########");

	for (int i = 0; i < 100; i++) {
	    dataSource.setName("Nikhil " + String.valueOf(i));
	    dataSource.setNote("note"+ String.valueOf(i));
	  		dataSource.setDate("note"+ String.valueOf(i));
	    db.update(Constants.REMINDER_TABLE_NAME,
		    dataSource.reminderToContentValues(), "_id = ?",
		    new String[] { String.valueOf(i) });
	}

	db.close();

    }

    public void LargeSelectOne() {

	db = helper.getReadableDatabase();
	Cursor c = db.query(Constants.REMINDER_TABLE_NAME, null, "_id = ?",
		new String[] { "77" }, null, null, null);
	if (c.moveToFirst())
	    System.out.println("#######################Large Select one ######"
		    + c.getString(1));
	db.close();

    }

    public void LargeDeleteOne() {
	System.out.println("#############Delete one #################");
	db = helper.getWritableDatabase();
	db.delete(Constants.REMINDER_TABLE_NAME, "_id = ?",
		new String[] { "34" });
	db.close();

    }

    public void LargeDeleteAll() {
	System.out.println("############Delete all #############");
	db = helper.getWritableDatabase();
	db.delete(Constants.REMINDER_TABLE_NAME, null, null);
	db.close();

    }

    @LargeTest
    public void testLarge() {
	InsertLarge();
	LargeSelectAll();
	LargeUpdate();
	LargeSelectOne();
	LargeDeleteOne();
	LargeSelectAll();
	LargeDeleteAll();
	LargeSelectAll();

    }

}
