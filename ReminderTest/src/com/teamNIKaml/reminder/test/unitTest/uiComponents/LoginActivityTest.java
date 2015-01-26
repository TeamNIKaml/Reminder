package com.teamNIKaml.reminder.test.unitTest.uiComponents;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    @MediumTest
    public void testPreconditions() {

	assertNotNull("loginActivity is null", loginActivity);
	assertNotNull("appName is null", appName);
	assertNotNull("passWord is null", passWord);
    }
    @MediumTest
    public void test_AppName_TextView_Label() {

	final String expected = loginActivity.getString(R.string.app_name);
	final String actual = appName.getText().toString();
	assertEquals("appName contains wrong text", expected, actual);
    }
    
    
    @MediumTest
    public void test_AppName_TextView_Layout() {
        //Retrieve the top-level window decor view
        final View decorView = loginActivity.getWindow().getDecorView();

        //Verify that the mClickMeButton is on screen
        ViewAsserts.assertOnScreen(decorView, appName);

        //Verify width and heights
        final ViewGroup.LayoutParams layoutParams = appName.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    
    
    @MediumTest
    public void test_Password_EditText_Hint() {

	final String expected = "Enter your new password";
	final String actual = passWord.getHint().toString();
	assertEquals("Password Hint contains wrong text", expected, actual);
    }
    @MediumTest
    public void test_LoginButton_Text() {

	final String expected = "Register";
	final String actual = loginButton.getText().toString();
	assertEquals("Password Hint contains wrong text", expected, actual);
    }
    
    
    @MediumTest
    public void test_LoginButton_Layout() {
        //Retrieve the top-level window decor view
        final View decorView = loginActivity.getWindow().getDecorView();

        //Verify that the mClickMeButton is on screen
        ViewAsserts.assertOnScreen(decorView, loginButton);

        //Verify width and heights
        final ViewGroup.LayoutParams layoutParams = loginButton.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.MATCH_PARENT);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}