package com.teamNIKaml.reminder.test.functionalTest;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teamNIKaml.reminder.activity.LoginActivity;
import com.teamNIKaml.reminder.activity.R;

public class LoginActivityFunctionalTest extends
	ActivityUnitTestCase<LoginActivity> {

    private LoginActivity loginActivity;
    private TextView appName;
    private EditText passWord;
    private Button loginButton;
    private Intent loginIntent;

    public LoginActivityFunctionalTest() {
	super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();

	loginIntent = new Intent(getInstrumentation().getTargetContext(),
		LoginActivity.class);

	loginActivity = getActivity();
	appName = (TextView) loginActivity.findViewById(R.id.app_name);
	passWord = (EditText) loginActivity.findViewById(R.id.password);
	loginButton = (Button) loginActivity.findViewById(R.id.loginButton);
    }

    public void testPreconditions() {

	assertNotNull("loginActivity is null", loginActivity);
	assertNotNull("appName is null", appName);
	assertNotNull("passWord is null", passWord);
	assertNotNull("Login Button is null", loginButton);
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {

	startActivity(loginIntent, null, null);
	loginButton.performClick();

	final Intent launchIntent = getStartedActivityIntent();

	assertNotNull("Intent was null", launchIntent);

	assertTrue(isFinishCalled());

    }

}
