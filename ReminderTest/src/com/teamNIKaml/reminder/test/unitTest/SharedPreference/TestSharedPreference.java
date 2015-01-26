package com.teamNIKaml.reminder.test.unitTest.SharedPreference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.MediumTest;

public class TestSharedPreference extends AndroidTestCase {

    private static final String TEST_FILE_PREFIX = "test_";

    private SharedPreferences pref;
    private Editor editor;
    private RenamingDelegatingContext context;

    @Override
    protected void setUp() throws Exception {
	super.setUp();

	context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);

    }

    @Override
    protected void tearDown() throws Exception {

	super.tearDown();
    }

    public void testPreConditions() {
	assertNotNull(context);
    }

    private void writeToSharedPreference() {
	pref = context.getSharedPreferences("localdiskchildlocator", 0);
	editor = pref.edit();
	editor.putInt("loginstatus", 100);
	editor.putString("password", "Password");
	editor.commit();
    }

    private void readFromSharedPreference() {

	pref = context.getSharedPreferences("localdiskchildlocator", 0);
	System.out.println(pref.getString("password", ""));
	System.out.println(pref.getInt("loginstatus", 0));

    }

    @MediumTest
    public void testSharedPreference() {
	writeToSharedPreference();
	readFromSharedPreference();
    }

}
