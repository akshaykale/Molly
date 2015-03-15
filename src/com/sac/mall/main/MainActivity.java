package com.sac.mall.main;

import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sac.mall.R;
import com.sac.mall.administrator.Administrator;
import com.sac.mall.mongo.CreateUserAsyncTask;
import com.sac.mall.mongo.AdministratorAsyncTask;
import com.sac.mall.shopper.ShopperMain;
//import com.wordpress.michaelkyazze.codeperspective101.R;

public class MainActivity extends Activity {
	EditText editText_last_name;
	EditText editText_phone;
	EditText editText_email;
	EditText editText_fname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText_fname = (EditText) findViewById(R.id.editText_fname);
		editText_last_name = (EditText) findViewById(R.id.editText_last_name);
		editText_email = (EditText) findViewById(R.id.editText_email);
		editText_phone = (EditText) findViewById(R.id.editText_phone);

		startActivity(new Intent(MainActivity.this,ShopperMain.class));
		
	}

	public void saveContact(View v) throws UnknownHostException {

		
		User contact = new User();
		contact.user_name = editText_fname.getText().toString();
		//contact.last_name = editText_last_name.getText().toString();
		contact.user_email = editText_email.getText().toString();
		contact.user_password = editText_phone.getText().toString();
		
		/*MyContact contact = new MyContact();
		contact.first_name = editText_fname.getText().toString();
		contact.last_name = editText_last_name.getText().toString();
		contact.email = editText_email.getText().toString();
		contact.phone = editText_phone.getText().toString();
*/
		CreateUserAsyncTask tsk = new CreateUserAsyncTask();
		tsk.execute(contact);
		
		Intent i = new Intent(this, ViewContactsActivity.class);
		startActivity(i);
	

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_view_contacts) {
			// Redirect to Profile Activity
			Intent intent = new Intent(this, ViewContactsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
