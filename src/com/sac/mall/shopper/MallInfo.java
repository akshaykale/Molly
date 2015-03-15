package com.sac.mall.shopper;

import com.sac.mall.R;
import com.sac.mall.main.MyMall;
import com.sac.mall.retailer.EntertainmentRetailer;
import com.sac.mall.retailer.FoodRetailer;
import com.sac.mall.retailer.LifestyleRetailer;
import com.sac.mall.retailer.OtherRetailer;
import com.sac.mall.retailer.SportsRetailer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MallInfo extends Activity{

	TextView tv_mallname,tv_location,tv_highl,tv_events;
	ImageView iv_food,iv_entertainment,iv_other,iv_sports,iv_lifestyle,iv_movies;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mallinfo_display);
		
		tv_mallname = (TextView) findViewById(R.id.mallinfo_mallname);
		tv_highl = (TextView) findViewById(R.id.mallinfo_mallhigh);
		tv_location = (TextView) findViewById(R.id.mallinfo_malladdr);
		tv_events = (TextView) findViewById(R.id.mallinfo_mallevents);
		
		iv_entertainment = (ImageView) findViewById(R.id.iv_entertainment);
		iv_food = (ImageView) findViewById(R.id.iv_food);
		iv_sports = (ImageView) findViewById(R.id.iv_sports);
		iv_other = (ImageView) findViewById(R.id.iv_other);
		iv_lifestyle = (ImageView) findViewById(R.id.iv_lifestyle);
		iv_movies = (ImageView) findViewById(R.id.iv_movies);
		
		final MyMall m = (MyMall) getIntent().getExtras().getSerializable("mall_obj");
		
	
	/*		Toast.makeText(getApplicationContext(), ""+m.getMall_name()
					+"\n"+m.getMall_desc()
					+"\n"+m.getMall_lati()
					+"\n"+m.getMall_location()
					+"\n"+m.getMall_longi()
					+"\n"+m.getMall_ratings()
					+"\n"+m.getMall_weburl(), 0).show();
	*/
			tv_mallname.setText(m.getMall_name());
			tv_events.setText(m.getMall_events());
			tv_highl.setText(m.getMall_highlights());
			tv_location.setText(m.getMall_location());
			
	
			iv_movies.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent viewIntent =
			          new Intent("android.intent.action.VIEW",
			            Uri.parse("http://www.bookmyshow.com/"));
			          startActivity(viewIntent);
				}
			});
			
			iv_entertainment.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Entertainment", 1).show();
					Intent i = new Intent(getApplicationContext(),EntertainmentRetailer.class);
					i.putExtra("mall_obj", m);
					startActivity(i);
				}
			});
			iv_food.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Food", 1).show();
					Intent i = new Intent(getApplicationContext(),FoodRetailer.class);
					i.putExtra("mall_obj", m);
					startActivity(i);
				}
			});
			iv_lifestyle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "LifeStyle", 1).show();
					Intent i = new Intent(getApplicationContext(),LifestyleRetailer.class);
					i.putExtra("mall_obj", m);
					startActivity(i);
				}
			});
			iv_other.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Other", 1).show();
					Intent i = new Intent(getApplicationContext(),OtherRetailer.class);
					i.putExtra("mall_obj", m);
					startActivity(i);
				}
			});
			iv_sports.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Sports", 1).show();
					Intent i = new Intent(getApplicationContext(),SportsRetailer.class);
					i.putExtra("mall_obj", m);
					startActivity(i);
				}
			});
	}
}
