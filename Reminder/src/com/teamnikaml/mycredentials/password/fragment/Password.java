package com.teamnikaml.mycredentials.password.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamnikaml.mycredentials.R;
import com.teamnikaml.mycredentials.appmodel.AppConstants;
import com.teamnikaml.mycredentials.password.adaptor.PasswordAdaptorEList;
import com.teamnikaml.mycredentials.password.database.PasswordHelper;
import com.teamnikaml.mycredentials.password.model.PasswordDataSource;
import com.teamnikaml.scrollviewlib.model.MyHorizontalScrollView;
import com.teamnikaml.scrollviewlib.model.MyScrollView;

public class Password extends Fragment {

	/*private PasswordAdaptorEList listAdapter;
	private ExpandableListView expListView;
	
	private HashMap<String, List<String>> accountName;
	private HashMap<String, List<String>> username;
	private HashMap<String, List<String>> password;
	private Button addOppertunitiesButton;
	private LayoutInflater li;
	
	*/
	
	private List<PasswordDataSource> passwordList = new ArrayList<PasswordDataSource>();
	
	private List<String> catagory;
	private PasswordDataSource dataSource = PasswordDataSource
			.getPasswordDataSource();
	private PasswordHelper dbHelper = new PasswordHelper();
	
	List<Integer> horizondalId = new ArrayList<Integer>();
	private int id = 0;
	
	
	private MyScrollView view;
	
	private CatagoryFragment fragment;
	
	@SuppressLint("HandlerLeak")
	private final Handler myHandler = new Handler() {
	    public void handleMessage(Message msg) {
	    	 	prepareData();
	    	 	if(fragment !=null)
	    	 	{
	    	 		int index = fragment.getCatagoaryIndex();
	    	 		fragment = new CatagoryFragment(index);
	    	 	
	    	 	}
	    }
	};
	
	 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		/*li = inflater;

		View passwordView = inflater.inflate(R.layout.password_frag, container,
				false);*/
		//init(passwordView);
		//setListner();
		init();

		return view.getScrollView();
	}
	
	
	
	
	




	private void setListner() {
		// TODO Auto-generated method stub
		/*expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

			
				}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// updateList();
				Toast.makeText(getActivity().getApplicationContext(),
						catagory.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getActivity().getApplicationContext(),
						catagory.get(groupPosition)
								+ " : "
								+ accountName.get(catagory.get(groupPosition))
										.get(childPosition), Toast.LENGTH_SHORT)
						.show();

				dataSource.setAccountName(accountName.get(
						catagory.get(groupPosition)).get(childPosition));
				dataSource.setUsername(username
						.get(catagory.get(groupPosition)).get(childPosition));
				dataSource.setCatagory(catagory.get(groupPosition));
				dataSource.setPassword(password
						.get(catagory.get(groupPosition)).get(childPosition));

				EditPasswordDialog dialog = new EditPasswordDialog(li);
				dialog.show(getActivity().getFragmentManager(), "AddPassword");

				return false;
			}
		});

		addOppertunitiesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddPasswordDialog dialog = new AddPasswordDialog(li);
				dialog.show(getChildFragmentManager(), "AddPassword");

			}
		});*/
	}

	private void init() {
		
		
		dataSource = PasswordDataSource.getPasswordDataSource();
		dataSource.setContext(getActivity().getApplicationContext());
		dbHelper.setHandler(myHandler);
		dbHelper.select(null, null, null, null);
		
		view = new MyScrollView(getActivity());
		
		setHorizontalScrollView();
		
		addFrameLayout();
		
		
	/*	catagory = new ArrayList<String>();
		accountName = new HashMap<String, List<String>>();
		username = new HashMap<String, List<String>>();
		password = new HashMap<String, List<String>>();

		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[0]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[1]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[2]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[3]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[4]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[5]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[6]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[7]);
		catagory.add(AppConstants.CATAGORY_SPINNER_ARRAY[8]);

		//expListView = (ExpandableListView) v.findViewById(R.id.passwordList);
		//addOppertunitiesButton = (Button) v.findViewById(R.id.addButton);
		dataSource = PasswordDataSource.getPasswordDataSource();
		dataSource.setContext(getActivity().getApplicationContext());
		dbHelper.setHandler(myHandler);
		dbHelper.select(null, null, null, null);

		prepareListData();

		listAdapter = new PasswordAdaptorEList(getActivity()
				.getApplicationContext(), catagory, accountName, username,
				password);
		expListView.setAdapter(listAdapter);

		dbHelper.setPassword(this);*/

	}
	
private void addFrameLayout() {
		
		
		
		View tempview = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_container, null);

		

		view.addView(tempview, R.id.container);
		
	
		
	
		fragment= new CatagoryFragment(0);
		
		
		
		
		
			tempview = view.getScrollView().findViewById( R.id.container);
		
		getFragmentManager().beginTransaction().replace(tempview.getId(), fragment).commit();
	
		
	
		
		
		
		

	}

	private void setHorizontalScrollView() {
		// TODO Auto-generated method stub
		
		id = 0;
		
		int[] horizondalImage ={R.drawable.ic_drawer,
				R.drawable.ic_app_launcher,
				R.drawable.ic_launcher,
				R.drawable.ic_action_search,
				R.drawable.ic_action_search,
				R.drawable.ic_app_launcher,
				R.drawable.ic_drawer,
				R.drawable.ic_app_launcher,
				R.drawable.ic_launcher};
		
		ImageView imageView;
		ImageClickListner clickListner = new ImageClickListner();
		MyHorizontalScrollView myHorizontalScrollView = new MyHorizontalScrollView(getActivity());
		for(int i=0;i<horizondalImage.length;i++,id++)
		{
		myHorizontalScrollView.addImageView(horizondalImage[i], id);
		imageView = (ImageView) myHorizontalScrollView.getHorizontalScrollView().findViewById(id);
		imageView.setOnClickListener(clickListner);
		horizondalId.add(id);
		}
		
		view.addView(myHorizontalScrollView.getHorizontalScrollView(), id++);
		
		
		
		
		
		
	}

	public void updateList() {
		
		prepareData();

		/*prepareListData();

		listAdapter.setAccountName(accountName);
		listAdapter.setPassword(password);
		listAdapter.setCatagory(catagory);
		listAdapter.setUsername(username);

		System.out.println(accountName);
		Log.e("<<>><<>>>", accountName.toString());
		
		listAdapter.notifyDataSetChanged();
*/
	}

	private void prepareData() {
		
		// TODO Auto-generated method stub prepareData
		
		passwordList = dataSource.getPasswordList();
	

		/*List<String> accountListSocial = new ArrayList<String>();
		List<String> accountListEmail = new ArrayList<String>();
		List<String> accountListCommerse = new ArrayList<String>();
		List<String> accountListBank = new ArrayList<String>();
		List<String> accountListWebSite = new ArrayList<String>();
		List<String> accountListPin = new ArrayList<String>();
		List<String> accountListComputer = new ArrayList<String>();
		List<String> accountListNetWork = new ArrayList<String>();
		List<String> accountListother = new ArrayList<String>();

		List<String> userListSocial = new ArrayList<String>();
		List<String> userListEmail = new ArrayList<String>();
		List<String> userListCommerse = new ArrayList<String>();
		List<String> userListBank = new ArrayList<String>();
		List<String> userListWebsite = new ArrayList<String>();
		List<String> userListPin = new ArrayList<String>();
		List<String> userListComputer = new ArrayList<String>();
		List<String> userListNetwork = new ArrayList<String>();
		List<String> userListOther = new ArrayList<String>();

		List<String> passListSocial = new ArrayList<String>();
		List<String> passListEmail = new ArrayList<String>();
		List<String> passListCommerse = new ArrayList<String>();
		List<String> passListBank = new ArrayList<String>();
		List<String> passListWebsite = new ArrayList<String>();
		List<String> passListPin = new ArrayList<String>();
		List<String> passListComputer = new ArrayList<String>();
		List<String> passListNetwork = new ArrayList<String>();
		List<String> passListOther = new ArrayList<String>();*/

	

		/*for (PasswordDataSource pass : passwordList) {

			if (pass.getCatagory().equalsIgnoreCase(
					AppConstants.CATAGORY_SPINNER_ARRAY[0])) {

				accountListSocial.add(pass.getAccountName());
				userListSocial.add(pass.getUsername());
				passListSocial.add(pass.getPassword());

			} else if (pass.getCatagory().equalsIgnoreCase(
					AppConstants.CATAGORY_SPINNER_ARRAY[1])) {

				accountListEmail.add(pass.getAccountName());
				userListEmail.add(pass.getUsername());
				passListEmail.add(pass.getPassword());

			} else if (pass.getCatagory().equalsIgnoreCase(
					AppConstants.CATAGORY_SPINNER_ARRAY[2])) {

				accountListCommerse.add(pass.getAccountName());
				userListCommerse.add(pass.getUsername());
				passListCommerse.add(pass.getPassword());
			} else if (pass.getCatagory().equalsIgnoreCase(
					AppConstants.CATAGORY_SPINNER_ARRAY[3])) {

				accountListBank.add(pass.getAccountName());
				userListBank.add(pass.getUsername());
				passListBank.add(pass.getPassword());
			}
			
			//"Social NetWork", "E-mail","E-Commerse", "Bank","Websites","Pin Code","Computer","Network", "Others"
			
			 else if (pass.getCatagory().equalsIgnoreCase(
						AppConstants.CATAGORY_SPINNER_ARRAY[4])) {

					accountListWebSite.add(pass.getAccountName());
					userListWebsite.add(pass.getUsername());
					passListWebsite.add(pass.getPassword());
				}
			 else if (pass.getCatagory().equalsIgnoreCase(
						AppConstants.CATAGORY_SPINNER_ARRAY[5])) {

					accountListPin.add(pass.getAccountName());
					userListPin.add(pass.getUsername());
					passListPin.add(pass.getPassword());
				}
			 else if (pass.getCatagory().equalsIgnoreCase(
						AppConstants.CATAGORY_SPINNER_ARRAY[6])) {

					accountListComputer.add(pass.getAccountName());
					userListComputer.add(pass.getUsername());
					passListComputer.add(pass.getPassword());
				}
			 else if (pass.getCatagory().equalsIgnoreCase(
						AppConstants.CATAGORY_SPINNER_ARRAY[7])) {

					accountListNetWork.add(pass.getAccountName());
					userListNetwork.add(pass.getUsername());
					passListNetwork.add(pass.getPassword());
				}

			else {

				accountListother.add(pass.getAccountName());
				userListOther.add(pass.getUsername());
				passListOther.add(pass.getPassword());

			}

		}
		
		
		if(dataSource.getRowcount() == 0)
		{
			accountListSocial = new ArrayList<String>();
			accountListEmail = new ArrayList<String>();
			accountListCommerse = new ArrayList<String>();
			accountListBank = new ArrayList<String>();
			 accountListWebSite = new ArrayList<String>();
			 accountListPin = new ArrayList<String>();
			 accountListComputer = new ArrayList<String>();
			accountListNetWork = new ArrayList<String>();
			accountListother = new ArrayList<String>();

			userListSocial = new ArrayList<String>();
			userListEmail = new ArrayList<String>();
			userListCommerse = new ArrayList<String>();
			userListBank = new ArrayList<String>();
			userListWebsite = new ArrayList<String>();
			userListPin = new ArrayList<String>();
			userListComputer = new ArrayList<String>();
			userListNetwork = new ArrayList<String>();
			userListOther = new ArrayList<String>();

			passListSocial = new ArrayList<String>();
			passListEmail = new ArrayList<String>();
			passListCommerse = new ArrayList<String>();
			passListBank = new ArrayList<String>();
			passListWebsite = new ArrayList<String>();
			passListPin = new ArrayList<String>();
			passListComputer = new ArrayList<String>();
			passListNetwork = new ArrayList<String>();
			passListOther = new ArrayList<String>();

		}*/
		
	

		/*accountName.put(catagory.get(0), accountListSocial);
		accountName.put(catagory.get(1), accountListEmail);
		accountName.put(catagory.get(2), accountListCommerse);
		accountName.put(catagory.get(3), accountListBank);
		accountName.put(catagory.get(4), accountListWebSite);
		accountName.put(catagory.get(5), accountListPin);
		accountName.put(catagory.get(6), accountListComputer);
		accountName.put(catagory.get(7), accountListNetWork);
		accountName.put(catagory.get(8), accountListother);
		
		
		

		username.put(catagory.get(0), userListSocial);
		username.put(catagory.get(1), userListEmail);
		username.put(catagory.get(2), userListCommerse);
		username.put(catagory.get(3), userListBank);
		username.put(catagory.get(4), userListWebsite);
		username.put(catagory.get(5), userListPin);
		username.put(catagory.get(6), userListComputer);
		username.put(catagory.get(7), userListNetwork);
		username.put(catagory.get(8), userListOther);		

		password.put(catagory.get(0), passListSocial);
		password.put(catagory.get(1), passListEmail);
		password.put(catagory.get(2), passListCommerse);
		password.put(catagory.get(3), passListBank);
		password.put(catagory.get(4), passListWebsite);
		password.put(catagory.get(5), passListPin);
		password.put(catagory.get(6), passListComputer);
		password.put(catagory.get(7), passListNetwork);
		password.put(catagory.get(8), passListOther);*/
		
		

	}
	
	
	private class CatagoryFragment extends Fragment {
		
		private LinearLayout linearLayout;
		private int catagoaryIndex;

		public int getCatagoaryIndex() {
			return catagoaryIndex;
		}

		public CatagoryFragment(int catagoaryIndex) {
			super();
			this.catagoaryIndex = catagoaryIndex;
		}

		public void setCatagoaryIndex(int catagoaryIndex) {
			this.catagoaryIndex = catagoaryIndex;
			//setFragmentData();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			init();
		//	bmwView = inflater.inflate(R.layout.fragment_view, null);
			
			return linearLayout;
		}

		/**
		 * @author Nikhil V Jul 27, 2015
		 * @param view2
		 */
		private void init() {
			// TODO Auto-generated method stub
			linearLayout = new LinearLayout(getActivity());
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			linearLayout.setMinimumWidth(LayoutParams.WRAP_CONTENT);
			linearLayout.setMinimumHeight(LayoutParams.WRAP_CONTENT);
			
			
			
			setFragmentData();
		}

		/**
		@author Nikhil V
		Jul 29, 2015
		 */
		private void setFragmentData() {
			// TODO Auto-generated method stub
			//linearLayout.removeAllViews();
			
		
		TextView accountName,userName,password;
		
		Toast.makeText(getActivity(), String.valueOf(passwordList.size()), Toast.LENGTH_LONG).show();
		
		for (final PasswordDataSource pass : passwordList) {

			if (pass.getCatagory().equalsIgnoreCase(
					AppConstants.CATAGORY_SPINNER_ARRAY[catagoaryIndex]))
			{
				View convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_password, null);	
				accountName = (TextView) convertView.findViewById(R.id.accountName);
				userName = (TextView) convertView.findViewById(R.id.username);
				password = (TextView) convertView.findViewById(R.id.password);
				
		
				accountName.setText(pass.getAccountName());
				userName.setText(pass.getUsername());
				password.setText(pass.getPassword());
				
				
				
				convertView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						dataSource.setAccountName(pass.getAccountName());
						dataSource.setUsername(pass.getUsername());
						dataSource.setCatagory(pass.getCatagory());
						dataSource.setPassword(pass.getPassword());

						EditPasswordDialog dialog = new EditPasswordDialog(getActivity());
						dialog.show(getActivity().getFragmentManager(), "AddPassword");
						
					}
				});
				
				
				
				linearLayout.addView(convertView);
				
				Log.e("dsfsdf@@@", pass.getAccountName());
				
				
			}
		}
		
	
			
		
		}

	

		

	}
	
	
	
	
	private class ImageClickListner implements View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			
			
			if(v.getId()>0)
			{
				fragment = new CatagoryFragment(v.getId()-1);
		
			View	tempview = view.getScrollView().findViewById( R.id.container);			
			getFragmentManager().beginTransaction().replace(tempview.getId(), fragment).commit();
			}
			else
			{
				AddPasswordDialog dialog = new AddPasswordDialog(getActivity());
				dialog.show(getFragmentManager(), "AddPassword");
			}
			
			
			
			
		}
		
	}

	

}
