package com.teamNIKaml.reminder.fragment;

import android.os.Bundle;
import com.teamNIKaml.reminder.activity.R;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Reminder extends Fragment {
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View android = inflater.inflate(R.layout.reminder_frag, container, false);
	        ((TextView)android.findViewById(R.id.textView)).setText("dfsds");
	        return android;
}}
