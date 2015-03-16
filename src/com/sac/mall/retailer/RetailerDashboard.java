package com.sac.mall.retailer;

import com.google.android.gms.internal.et;
import com.sac.mall.R;
import com.sac.mall.administrator.Administrator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RetailerDashboard extends Activity{

	Button bt_add, bt_view;
	TextView tv_dis;
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.retailer_dashboard);
		
		String retailer = getIntent().getExtras().getString("mall_name");
		
		getActionBar().setTitle(retailer);
		
		bt_add = (Button) findViewById(R.id.retailer_add_discount);
		bt_view = (Button) findViewById(R.id.retailer_view_discount);
		tv_dis = (TextView) findViewById(R.id.retailer_discountdescp);
		
		bt_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Dialog d = new Dialog(RetailerDashboard.this);
				d.setContentView(R.layout.ret_dialog);
				d.setCancelable(true);
				d.setTitle("Add discount");
				final EditText et_uid = (EditText) d.findViewById(R.id.add_d);
				//EditText et_pw = (EditText) d.findViewById(R.id.et_dialog_login_admin_pw);
				Button bt_log = (Button) d.findViewById(R.id.add_d_bt);
				// bt_sign = (Button) d.findViewById(R.id.bt_dialog_login_admin_signup);
				bt_log.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						tv_dis.setText(et_uid.getText().toString());
						d.dismiss();
					}
				});
				d.show();
			}
		});
		bt_view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Dialog d = new Dialog(RetailerDashboard.this);
				d.setContentView(R.layout.dialog);
				d.setCancelable(true);
				d.setTitle("Add discount");
				TextView et_uid = (TextView) d.findViewById(R.id.tvd);
				et_uid.setText(tv_dis.getText().toString());
				d.show();
			}
		});
	}
}
