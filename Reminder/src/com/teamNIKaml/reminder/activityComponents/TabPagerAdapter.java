package com.teamNIKaml.reminder.activityComponents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.teamNIKaml.reminder.password.fragment.Password;
import com.teamNIKaml.reminder.reminder.fragment.Reminder;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		case 0:
			return new Password();

		case 1:
			return new Reminder();
		}
		return null;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2; // No of Tabs
	}

}