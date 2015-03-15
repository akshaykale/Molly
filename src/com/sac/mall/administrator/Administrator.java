package com.sac.mall.administrator;

import com.sac.mall.R;
import com.sac.mall.main.MyMall;
import com.sac.mall.main.ViewContactsActivity;
import com.sac.mall.mongo.CreateUserAsyncTask;
import com.sac.mall.mongo.AdministratorAsyncTask;
import com.sac.mall.shopper.MallList;
//import com.wordpress.michaelkyazze.codeperspective101.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Administrator extends Activity {

	EditText et_mall_name,et_mall_desc,et_mall_location,
		et_mall_phone,et_mall_email,et_mall_lat,et_mall_longi;
	Button bt_addMall;
	//RatingBar rb_mallRating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.administrator_create_mall);
		
		SharedPreferences sp = getSharedPreferences("com.sac.file", MODE_PRIVATE);
		if(sp.getInt("islogedin_admin", 0)==1){
			Intent i = new Intent(getApplicationContext(),AdministratorDashboard.class);
			i.putExtra("mall_name", getIntent().getExtras().getString("mall_name"));
			startActivity(i);
		}
		
		init();
		
		
		
		bt_addMall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				MyMall mall = new MyMall();
				mall.setMall_name(et_mall_name.getText().toString());
				mall.setMall_desc(et_mall_desc.getText().toString());
				mall.setMall_location(et_mall_location.getText().toString());
				mall.setMall_contacts(et_mall_phone.getText().toString());
				mall.setMall_weburl(et_mall_email.getText().toString());
				mall.setMall_lati(et_mall_lat.getText().toString());
				mall.setMall_longi(et_mall_longi.getText().toString());
				
				AdministratorAsyncTask tsk = new AdministratorAsyncTask();
				tsk.execute(mall);
				
			}
		});
	}

	private void init() {
		et_mall_name = (EditText) findViewById(R.id.et_mall_name);
		et_mall_desc = (EditText) findViewById(R.id.et_mall_disc);
		et_mall_location = (EditText) findViewById(R.id.et_mall_location);
		et_mall_phone = (EditText) findViewById(R.id.et_mall_phone);
		et_mall_email = (EditText) findViewById(R.id.et_mall_email);
		et_mall_lat = (EditText) findViewById(R.id.et_mall_latitude);
		et_mall_longi = (EditText) findViewById(R.id.et_mall_longi);
		bt_addMall = (Button) findViewById(R.id.bt_addmall);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admid_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_view_addMall) {
			//Toast.makeText(getApplicationContext(), "not Added", 0).show();
			MyMall mall = new MyMall();
			mall.setMall_name(et_mall_name.getText().toString());
			mall.setMall_desc(et_mall_desc.getText().toString());
			mall.setMall_location(et_mall_location.getText().toString());
			mall.setMall_contacts(et_mall_phone.getText().toString());
			mall.setMall_weburl(et_mall_email.getText().toString());
			mall.setMall_lati(et_mall_lat.getText().toString());
			mall.setMall_longi(et_mall_longi.getText().toString());
			
			AdministratorAsyncTask tsk = new AdministratorAsyncTask();
			tsk.execute(mall);
			Toast.makeText(getApplicationContext(), "Added", 0).show();
			SharedPreferences sp = getSharedPreferences("com.sac.file", MODE_PRIVATE);
			Editor ed = sp.edit();
			ed.putInt("islogedin_admin", 1);
			ed.commit();
			Intent i = new Intent(getApplicationContext(),AdministratorDashboard.class);
			i.putExtra("mall_name", mall.getMall_name());
			startActivity(i);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
