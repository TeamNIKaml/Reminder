package com.teamNIKaml.reminder.test.unitTest.uiComponents;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teamNIKaml.reminder.activity.LoginActivity;
import com.teamNIKaml.reminder.activity.R;

public class LoginActivityTest extends
	ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity loginActivity;
    private TextView appName;
    private EditText passWord;
    private Button loginButton;

    public LoginActivityTest() {
	super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();

	loginActivity = getActivity();
	appName = (TextView) loginActivity.findViewById(R.id.app_name);
	passWord = (EditText) loginActivity.findViewById(R.id.password);
	loginButton = (Button) loginActivity.findViewById(R.id.loginButton);
    }

    public void testPreconditions() {

	assertNotNull("loginActivity is null", loginActivity);
	assertNotNull("appName is null", appName);
	assertNotNull("passWord is null", passWord);
    }

    public void testAppNameTextView() {

	final String expected = loginActivity.getString(R.string.app_name);
	final String actual = appName.getText().toString();
	assertEquals("appName contains wrong text", expected, actual);
    }

    public void testPasswordEditText() {

	final String expected = "Enter your new password";
	final String actual = passWord.getHint().toString();
	assertEquals("Password Hint contains wrong text", expected, actual);
    }

    public void testLoginButton() {

	final String expected = "Register";
	final String actual = loginButton.getText().toString();
	assertEquals("Password Hint contains wrong text", expected, actual);
    }

}