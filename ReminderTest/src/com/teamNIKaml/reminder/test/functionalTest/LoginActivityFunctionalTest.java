package com.teamNIKaml.reminder.test.functionalTest;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;

import com.teamNIKaml.reminder.activity.LoginActivity;
import com.teamNIKaml.reminder.activity.PostLoginActivity;
import com.teamNIKaml.reminder.activity.R;

public class LoginActivityFunctionalTest extends
	ActivityInstrumentationTestCase2<LoginActivity> {

    private static final int TIMEOUT_IN_MS = 5000;
    private static final String TEST_MESSAGE = "Hello Receiver";
    private LoginActivity mSenderActivity;

    public LoginActivityFunctionalTest() {
	super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	setActivityInitialTouchMode(true);
	mSenderActivity = getActivity();
    }

    /**
     * Tests the preconditions of this test fixture.
     */
    @MediumTest
    public void testPreconditions() {
	assertNotNull("mSenderActivity is null", mSenderActivity);
    }

    @MediumTest
    public void testSendMessageToReceiverActivity() {

	// Because this functional test tests interaction across multiple
	// components these views
	// are part of the actual test method and not of the test fixture
	final Button loginButton = (Button) mSenderActivity
		.findViewById(R.id.loginButton);
	final EditText passwordEditText = (EditText) mSenderActivity
		.findViewById(R.id.password);

	// Create and add an ActivityMonitor to monitor interaction between the
	// system and the
	// ReceiverActivity
	Instrumentation.ActivityMonitor receiverActivityMonitor = getInstrumentation()
		.addMonitor(PostLoginActivity.class.getName(), null, false);

	// Request focus on the EditText field. This must be done on the
	// UiThread because
	getInstrumentation().runOnMainSync(new Runnable() {
	    @Override
	    public void run() {
		passwordEditText.requestFocus();
	    }
	});
	// Wait until all events from the MainHandler's queue are processed
	getInstrumentation().waitForIdleSync();

	// Send the text message
	getInstrumentation().sendStringSync(TEST_MESSAGE);
	getInstrumentation().waitForIdleSync();

	// Click on the loginButton to send the message to ReceiverActivity
	TouchUtils.clickView(this, loginButton);

	// Wait until ReceiverActivity was launched and get a reference to it.
	PostLoginActivity receiverActivity = (PostLoginActivity) receiverActivityMonitor
		.waitForActivityWithTimeout(TIMEOUT_IN_MS);
	// Verify that ReceiverActivity was started
	assertNotNull("ReceiverActivity is null", receiverActivity);
	assertEquals("Monitor for ReceiverActivity has not been called", 1,
		receiverActivityMonitor.getHits());
	assertEquals("Activity is of wrong type", PostLoginActivity.class,
		receiverActivity.getClass());

	// Unregister monitor for ReceiverActivity
	getInstrumentation().removeMonitor(receiverActivityMonitor);
    }
}
