package com.sac.mall.administrator;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sac.mall.R;
import com.sac.mall.main.MyMall;
import com.sac.mall.mongo.GetMallsAsyncTask;
import com.sac.mall.mongo.QueryBuilder;
import com.sac.mall.retailer.ProductJSONParser;
import com.sac.mall.retailer.Retailer;

public class AdministratorDashboard extends Activity {

	Button admindashboard_addretailer, admin_viewretailer;
	Button viewretailers, viewevents,viewmall;
	
	Retailer r;
	MyMall reqMall;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admistrator_dashboard_);

		viewretailers = (Button) findViewById(R.id.retailer_add_discount);
		viewmall = (Button) findViewById(R.id.administrator_viewmall);
		viewevents = (Button) findViewById(R.id.administrator_viewevents);
		
		String mall_name = getIntent().getExtras().getString("mall_name");
		Log.d("!!", mall_name);
		ArrayList<MyMall> mallList = null;
		GetMallsAsyncTask a = new GetMallsAsyncTask();
		try {
			mallList = a.execute().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Log.d("**", "" + mallList.size());
		reqMall = new MyMall();
		for (MyMall m : mallList) {
			if (m.getMall_name().equals(mall_name)) {
				reqMall = m;
				/*Toast.makeText(
						getApplicationContext(),
						"" + m.getMall_name() + "\n" + m.getMall_desc() + "\n"
								+ m.getMall_lati() + "\n"
								+ m.getMall_location() + "\n"
								+ m.getMall_longi() + "\n"
								+ m.getMall_ratings() + "\n"
								+ m.getMall_weburl(), 0).show();*/
				break;
			}
		}
		
		viewEventsListener();
		viewRetailersListener();
		viewMallListener();

		admindashboard_addretailer = (Button) findViewById(R.id.retailer_view_discount);
		admin_viewretailer = (Button) findViewById(R.id.retailer_add_discount);

		admindashboard_addretailer
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						final Dialog dialog = new Dialog(
								AdministratorDashboard.this);

						dialog.setContentView(R.layout.add_retailer_);

						dialog.setCancelable(true);

						dialog.setTitle("Add retailer");

						final RadioGroup rg = (RadioGroup) dialog
								.findViewById(R.id.add_group);

						dialog.show();

						final EditText et_name = (EditText) dialog
								.findViewById(R.id.addretailer_name);
						final EditText et_pass = (EditText) dialog
								.findViewById(R.id.addretailer_pass);
						final int idrad = rg.getCheckedRadioButtonId();

						Button add = (Button) dialog
								.findViewById(R.id.addbutton);
						add.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								r = new Retailer();
								r.setName(et_name.getText().toString());
								r.setPassword(et_pass.getText().toString());
								r.setDiscount("");
								RadioButton rb = (RadioButton) findViewById(idrad);
								String selection = " Not Mention";
								if (rg.getCheckedRadioButtonId() != -1) {
									int id = rg.getCheckedRadioButtonId();
									View radioButton = rg.findViewById(id);
									int radioId = rg.indexOfChild(radioButton);
									RadioButton btn = (RadioButton) rg
											.getChildAt(radioId);
									selection = (String) btn.getText();
								}

								r.setCategory(selection);

								dialog.dismiss();

								String retailer_to_up = retailermake(r)+",";
								
								Toast.makeText(getApplicationContext(),
										selection, 1).show();
								reqMall.setMall_retailers(reqMall.getMall_retailers()+retailer_to_up);
								reqMall.setMall_desc("Akshay");
								try {
									updateContact();
								} catch (UnknownHostException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});

					}
				});

	}

	private void viewMallListener() {
		viewmall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog d = new Dialog(AdministratorDashboard.this);
				d.setContentView(R.layout.dialog);
				d.setTitle("Mall Info");
				String info = ""+reqMall.getMall_name()+"\n"+
						reqMall.getMall_location()+"\n"+reqMall.getMall_contacts()+"\n"+reqMall.getMall_weburl();
				d.setCancelable(true);
				TextView tv = (TextView) d.findViewById(R.id.tvd);
				tv.setText(info);
				d.show();
			}
		});
	}

	private void viewRetailersListener() {
		viewretailers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog d = new Dialog(AdministratorDashboard.this);
				d.setContentView(R.layout.dialog);
				d.setTitle("Retailer");
				String info = "";
				ArrayList<Retailer> rr = ProductJSONParser.parseFeed(reqMall.getMall_retailers());
				for(Retailer r : rr){
					info += r.getName() + "(" + r.getCategory() +")" +"\n";
				}
				d.setCancelable(true);
				TextView tv = (TextView) d.findViewById(R.id.tvd);
				tv.setText(info);
				d.show();
			}
		});
	}

	private void viewEventsListener() {
		viewevents.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog d = new Dialog(AdministratorDashboard.this);
				d.setContentView(R.layout.dialog);
				d.setTitle("Events Info");
				String info = ""+reqMall.getMall_events();
				d.setCancelable(true);
				//Toast.makeText(getApplicationContext(), "haha"+info, 1).show();
				TextView tv = (TextView) d.findViewById(R.id.tvd);
				tv.setText(info);
				d.show();
			}
		});
	}

	public String retailermake(Retailer r) {
		return String.format("{\"name\": \"%s\", "
				+ "\"category\": \"%s\", \"discount\": \"%s\", "
				+ "\"password\": \"%s\"}", r.getName(), r.getCategory(),
				r.getDiscount(), r.getPassword());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void updateContact() throws UnknownHostException {
		 MyMall contact = new MyMall();
		 contact = reqMall;
		 MongoLabUpdateContact tsk = new MongoLabUpdateContact();
		 tsk.execute(contact); 
		 //Intent i = new Intent(this, ViewContactsActivity.class);
		 //startActivity(i);
		 Toast.makeText(getApplicationContext(), "Added", 1).show();
		 }
		 
		 /**
		 * AsyncTask to update a given contact
		 * @author KYAZZE MICHAEL
		 *
		 */
		 final class MongoLabUpdateContact extends AsyncTask<Object, Void, Boolean> {

		 @Override
		 protected Boolean doInBackground(Object... params) {
			 MyMall contact = (MyMall) params[0];
		 
		 try {

		 QueryBuilder qb1 = new QueryBuilder();
		 URL url = new URL(qb1.buildContactsUpdateURL(contact.mall_desc));
		// URL url = new URL(qb.buildContactsUpdateURL(contact.getMall_name()));
		 HttpURLConnection connection = (HttpURLConnection) url
		 .openConnection();
		 connection.setRequestMethod("PUT");
		 connection.setDoOutput(true);
		 connection.setRequestProperty("Content-Type",
		 "application/json");
		 connection.setRequestProperty("Accept", "application/json");
		 
		 OutputStreamWriter osw = new OutputStreamWriter(
		 connection.getOutputStream());
		 
		 osw.write(qb1.setContactData(contact)); 
		 osw.flush();
		 osw.close();
		 if(connection.getResponseCode() <205)
		 {

		 return true;
		 }
		 else
		 {
		 return false;
		 
		 }
		 
		 } catch (Exception e) {
		 e.getMessage();
		 return false;
		 
		 }

		 }
		 
		 }
	
	
	
	
	
	
	
	
	

}
