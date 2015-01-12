package com.teamNIKaml.reminder.fragment;

import java.util.ArrayList;
import java.util.List;

import com.teamNIKaml.reminder.property.PasswordDataSource;
import com.teamNIKaml.reminder.activity.R;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



public class AddPasswordDialog extends DialogFragment {
	
	private PasswordDataSource dataSource = PasswordDataSource.getPasswordDataSource();
	private EditText projectTitle,description,contact,designation,address1,address2,county,city;
	private EditText compcode,postcode,dayphone,email,eveningphone,other;	
    private Spinner property_type,module_type,country;
	private List<String> propertyTypeList,moduleTypeList,countryList;
	private ArrayAdapter<String> propertyTypeAdaptor,moduleTypeAdaptor,countryAdaptor;
	
	private LayoutInflater li;
	
	public AddPasswordDialog(LayoutInflater li) {
		super();
		this.li = li;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		
		
  	View v =li.inflate(R.layout.activity_add_password, null);	   ; 	
   	 
   	 initDialog(v);
   	setDialogData();
		
		return new AlertDialog.Builder(getActivity()).setView(v)
				// Set Dialog Icon
				//.setIcon(R.drawable.androidhappy)
				// Set Dialog Title
				//.setTitle("Alert DialogFragment")
				// Set Dialog Message
				//.setMessage("Alert DialogFragment Tutorial")
 
				// Positive button
				.setPositiveButton("Next", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do something else
					}
				})
 
				// Negative Button
				.create();
	}

	private void initDialog(View v) {
		// TODO Auto-generated method stub
		 projectTitle =(EditText) v.findViewById(R.id.projectTitle);
		  description =(EditText)v.findViewById(R.id.description);
		  contact =(EditText)v.findViewById(R.id.contact);
		  designation = (EditText)v.findViewById(R.id.designation); 
		  address1 = (EditText) v.findViewById(R.id.address1);
		  address2 = (EditText) v.findViewById(R.id.address2);
		  county = (EditText) v.findViewById(R.id.county);
		  city = (EditText) v.findViewById(R.id.city);
		  compcode = (EditText) v.findViewById(R.id.compcode);
		  postcode = (EditText) v.findViewById(R.id.postcode);
		  dayphone = (EditText) v.findViewById(R.id.dayphone);
		  email = (EditText) v.findViewById(R.id.emailOppertunities);
		  eveningphone = (EditText) v.findViewById(R.id.eveningphone);
		  other =	(EditText) v.findViewById(R.id.other);
		  property_type = (Spinner) v.findViewById(R.id.propertyspinner);
		  module_type = (Spinner) v.findViewById(R.id.modulespinner);
		  country = (Spinner) v.findViewById(R.id.countryspinner);		
		  propertyTypeList = new ArrayList<String>();
		  moduleTypeList = new ArrayList<String>();
		  countryList = new ArrayList<String>();
	}
	
	
	 private void setDialogData() {
			// TODO Auto-generated method stub
	    	
			
		}
	
		
	
}
