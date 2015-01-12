package com.teamNIKaml.reminder.activity;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddOppertunities extends Activity { 
	
	
	private ListView listView;
	private Button addButton;
	private View v ;
	private LayoutInflater li;
	
	//private OppertunitiesDataSource dataSource = OppertunitiesDataSource.getOppertunitiesDataSource();
	private EditText projectTitle,description,contact,designation,address1,address2,county,city;
	private EditText compcode,postcode,dayphone,email,eveningphone,other;	
    private Spinner property_type,module_type,country;
	private List<String> propertyTypeList,moduleTypeList,countryList;
	private ArrayAdapter<String> propertyTypeAdaptor,moduleTypeAdaptor,countryAdaptor;
	//private OppertunityService service = new OppertunityService(this);
	//private List<OppertunitiesDataSource> oppertunitieslist = new ArrayList<OppertunitiesDataSource>();	
	
	
	
	/* @Override
		@Deprecated
		protected Dialog onCreateDialog(int id) {
			// TODO Auto-generated method stub
	    	Dialog dlg = null;
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	
	    	 li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	 v =li.inflate(R.layout.activity_add_opertunities, null);	   ; 	
	    	 
	    	 initDialog(v);
	    	 if(id == 102)
	    	 {
	    		 setDialogData();	 
	    	 }
	    	 
	    	 
	    	
	    	dlg = builder.setView(v)
	    			.setPositiveButton("Next", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					})
	    			.create();
			return dlg;
		}
	
 @Override
 public void onCreate(Bundle savedInstanceState)
 {
     super.onCreate(savedInstanceState);
     
        setContentView(R.layout.oppertunities);
        
        
        addButton =(Button)findViewById(R.id.addOppertuniites);
        addButton.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(100);
				
			}
		});
        
        
        
        listView = (ListView)findViewById(R.id.myOppertunities);
        OppertunitiesAdaptor oppertunitiesAdaptor = new OppertunitiesAdaptor(this);
        
        service.getOppertunitiesService();
        Log.e("onCreate opeertunities","onCreate oppertunities");
        oppertunitieslist = dataSource.getOppertunitieslist();
        
        for(OppertunitiesDataSource oppertunities : oppertunitieslist)
		 {
     	   oppertunitiesAdaptor.addTitle(oppertunities.getProjectTitle());
     	   oppertunitiesAdaptor.addStatus(oppertunities.getStatus());
     	   oppertunitiesAdaptor.addendDate(oppertunities.getContactName());
     	   
			 
		 }
        
       
        
       // listView.setAdapter(oppertunitiesAdaptor);
        
        
        
        oppertunitiesAdaptor.notifyDataSetChanged();
        listView.setOnItemClickListener(new OnItemClickListener(){


        

        	@Override
        	public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {

        	   // TextView textview1 = (TextView) v.findViewById(R.id.listitem1);
        	   // TextView textview2 = (TextView) v.findViewById(R.id.text2);
        		
        		processOpertunities(listView.getItemAtPosition(pos).toString());
        		
        		

        	    //Log.v("textview1",textview1.getText().toString().trim());
        	    //Log.v("textview2",textview2.getText().toString().trim());

        	}



        	});

        
        
      // listView.setAdapter(oppertunitiesAdaptor);
        //listView = new ListView(this);
       // listView.setAdapter(oppertunitiesAdaptor);
       //setListAdapter(oppertunitiesAdaptor);
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
 	 if(dataSource.getProjectTitle() != null)
 	 {
		 projectTitle.setText(dataSource.getProjectTitle());
		  description.setText(dataSource.getDesignation());
		  contact.setText(dataSource.getContactName());
		  designation.setText(dataSource.getDesignation());
		  address1.setText(dataSource.getAddress1());
		  address2.setText(dataSource.getAddress2());
		  county.setText(dataSource.getCounty());
		  city.setText(dataSource.getCity());
		  compcode.setText(dataSource.getCompCode());
		  postcode.setText(dataSource.getPostCode());
		  dayphone.setText(dataSource.getDayPhone());
		  email.setText(dataSource.getEmailId());
		  eveningphone.setText(dataSource.getEveningPhone());
		  other.setText(dataSource.getOther());
		 propertyTypeList.add(dataSource.getPropertyType());
		  moduleTypeList.add(dataSource.getModuleType());
		  countryList.add(dataSource.getCountry());
		  propertyTypeAdaptor = new ArrayAdapter<String>(getApplicationContext(),
				  R.layout.spinnertext, propertyTypeList);
		  moduleTypeAdaptor = new ArrayAdapter<String>(getApplicationContext(),
		            R.layout.spinnertext, moduleTypeList);
		  countryAdaptor = new ArrayAdapter<String>(getApplicationContext(),
				  R.layout.spinnertext, countryList);
		 property_type.setAdapter(propertyTypeAdaptor);
		  module_type.setAdapter(moduleTypeAdaptor);
		 country.setAdapter(countryAdaptor);
 	 }
 	 else
 	 {
 		 Log.e("Null set dailog", "#dsfsfsdfsdf");
 	 }
		
	}
 
 

 
 
    
    @SuppressWarnings("deprecation")
	private void processOpertunities(Object item)
    {
    	if(item == null)
    		Toast.makeText(this,"item null",Toast.LENGTH_LONG).show();
    	else
    	{
    		//Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show();
    		showDialog(102);
    	}
    }*/

}
