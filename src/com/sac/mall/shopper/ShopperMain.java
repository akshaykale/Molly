package com.sac.mall.shopper;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.sac.mall.R;
import com.sac.mall.main.MyMall;
import com.sac.mall.mongo.GetMallsAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ShopperMain extends Activity{

	TextView tv_name,tv_desc,tv_phone,tv_url,tv_location;
	RatingBar rb_rate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopper_main);
		
		init();
		
		ArrayList<MyMall> returnValues = null;
		GetMallsAsyncTask a = new GetMallsAsyncTask();
		try {
			returnValues = a.execute().get();
			Log.d("##", "haha "+returnValues.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		
		for(MyMall m : returnValues){
			Toast.makeText(getApplicationContext(), ""+m.getMall_name()
					+"\n"+m.getMall_desc()
					+"\n"+m.getMall_lati()
					+"\n"+m.getMall_location()
					+"\n"+m.getMall_longi()
					+"\n"+m.getMall_ratings()
					+"\n"+m.getMall_weburl(), 0).show();
		}
		
	}

	private void init() {
		tv_name = (TextView) findViewById(R.id.tv_mall_name);
		tv_desc = (TextView) findViewById(R.id.tv_mall_desc);
		tv_phone = (TextView) findViewById(R.id.tv_mall_phone);
		tv_url = (TextView) findViewById(R.id.tv_mall_url);
		tv_location = (TextView) findViewById(R.id.tv_mall_location);
		//rb_rate = (RatingBar) findViewById(R.id.ratingBar1);
	}
}
