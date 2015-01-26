package com.teamNIKaml.reminder.test.unitTest.uiComponents;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.teamNIKaml.reminder.activity.LoginActivity;
import com.teamNIKaml.reminder.activity.R;

public class LoginActivityTest extends
	ActivityInstrumentationTestCase2<LoginActivity> {

    public LoginActivityTest(Class<LoginActivity> activityClass) {
	super(activityClass);
	// TODO Auto-generated constructor stub
    }

    private LoginActivity loginActivity;
    private TextView appName;

    public LoginActivityTest() {
	super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();

	loginActivity = getActivity();
	appName = (TextView) loginActivity.findViewById(R.id.app_name);
    }

    public void testPreconditions() {

	assertNotNull("loginActivity is null", loginActivity);
	assertNotNull("appName is null", appName);
    }

  
    public void testAppNameTextView() {

	final String expected = loginActivity.getString(R.string.app_name);
	final String actual = appName.getText().toString();
	assertEquals("appName contains wrong text", expected, actual);
    }
}