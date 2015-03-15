package com.sac.mall.main;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sac.mall.R;
import com.sac.mall.administrator.Administrator;
import com.sac.mall.shopper.MallList;
import com.sac.mall.shopper.ShopperDashboard;

public class FragmentC extends Fragment{

	EditText et_uid,et_pw;
	Button bt_login;
	TextView tv_retail,tv_admin;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_c, container, false);
		
		init(view);
		LoginAsShopper();
		LoginAsRetailer();
		LoginAsAdministrator();
		
		
		return view;	
	}

	private void init(View view) {
		bt_login = (Button) view.findViewById(R.id.bt_shopper_login);
		et_uid = (EditText) view.findViewById(R.id.et_shopper_uid);
		et_pw = (EditText) view.findViewById(R.id.et_shopper_pw);
		tv_retail = (TextView) view.findViewById(R.id.tv_loginas_retailer);
		tv_admin = (TextView) view.findViewById(R.id.tv_loginas_admin);
	}
	
	private void LoginAsShopper() {
		bt_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String uid = et_uid.getText().toString();
				String pw = et_pw.getText().toString();
				if(uid.equals("user1") && pw.equals("user1")){
					SharedPreferences sp = getActivity().getSharedPreferences("com.sac.file", 0);
					Editor ed = sp.edit();
					ed.putInt("islogedin", 1);
					ed.commit();
					startActivity(new Intent(getActivity(),MallList.class));
					getActivity().finish();
				}else{
					Toast.makeText(getActivity(), "Wrong uid or pw", 1).show();
				}
			}
		});
	}

	private void LoginAsRetailer() {
		
	}

	private void LoginAsAdministrator() {
		tv_admin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog d = new Dialog(getActivity());
				d.setContentView(R.layout.dialog_loginas_admin);
				d.setCancelable(true);
				final EditText et_uid = (EditText) d.findViewById(R.id.et_dialog_login_admin_uid);
				EditText et_pw = (EditText) d.findViewById(R.id.et_dialog_login_admin_pw);
				Button bt_log = (Button) d.findViewById(R.id.bt_dialog_login_admin_login);
				Button bt_sign = (Button) d.findViewById(R.id.bt_dialog_login_admin_signup);
				bt_log.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(!et_uid.getText().toString().equals("")){
						Intent i = new Intent(getActivity(),Administrator.class);
						i.putExtra("mall_name", et_uid.getText().toString());
						startActivity(i);
						}else{
							Toast.makeText(getActivity(), "Enter Mall Name", 1).show();
						}
					
					}
				});
				bt_sign.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				});
				d.show();
			}
		});
	}

	
}