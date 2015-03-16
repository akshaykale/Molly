package com.sac.mall.shopper;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.raw.arview.ARView;
import com.sac.mall.R;
import com.sac.mall.location.GPSTracker;
import com.sac.mall.main.MyMall;
import com.sac.mall.main.ViewContactsActivity;
import com.sac.mall.mongo.GetMallsAsyncTask;

public class MallList extends Activity implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener{

	
	ListView lv_mallList;
	ArrayList<MyMall> mallList = null;
	
	ImageButton floating;
	
	GPSTracker gps;
	double geoLatitude, geoLongitude;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099cc")));
		setContentView(R.layout.mall_list);
		
		lv_mallList = (ListView) findViewById(R.id.lv_mall_mallview);
		
		floating = (ImageButton) findViewById(R.id.floating);
		
		getLastLocation();
		//get malls from server
		if(isOnline())
			getMallsList();
		
		MallListListener();
		
		floating.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MallList.this,ARView.class);
				i.putExtra("loc",  mallList );
				startActivity(i);
			}
		});
		
	}
	

	private void MallListListener() {
		lv_mallList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				Intent i = new Intent(getApplicationContext(),MallInfo.class);
				i.putExtra("mall_obj", mallList.get(pos));
				startActivity(i);
			}
		});
	}


	public boolean isOnline(){
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo netInfo = cm.getActiveNetworkInfo();
    	
    	if(netInfo != null && netInfo.isConnectedOrConnecting()){
    		return true;
    	}else{
    		return false;
    	}
    }

	@SuppressWarnings("null")
	public void getLastLocation(){
		Location temp = null;
		gps = new GPSTracker(MallList.this);
		
		if(gps.canGetLocation()) {
			double latitude= gps.getLatitude();
			double longitude = gps.getLongitude();
			//geoLatitude = latitude;geoLongitude = longitude;
			Toast.makeText(
					getApplicationContext(),
					"Your Location is -\nLat: " + latitude + "\nLong: "
							+ longitude, Toast.LENGTH_LONG).show();
		} else {
			gps.showSettingsAlert();
		}
		
	}
	
	private void getMallsList() {
		ArrayList<MyMall> returnValues = null;
		GetMallsAsyncTask a = new GetMallsAsyncTask();
		try {
			returnValues = a.execute().get();
			mallList = returnValues;
			
			lv_mallList.setAdapter(new MyAdapter(getApplicationContext()));
			
			Log.d("##", "haha "+returnValues.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		/*for(MyMall m : returnValues){
			Toast.makeText(getApplicationContext(), ""+m.getMall_name()
					+"\n"+m.getMall_desc()
					+"\n"+m.getMall_lati()
					+"\n"+m.getMall_location()
					+"\n"+m.getMall_longi()
					+"\n"+m.getMall_ratings()
					+"\n"+m.getMall_weburl(), 0).show();
		}*/
	}
	
	
	
	
	
	/////////////adapter
	class MyAdapter extends BaseAdapter {
		Context context;	
		
		int[] images = {R.drawable.amanora, R.drawable.asas, R.drawable.ishanya, R.drawable.xcx};
		public MyAdapter(Context c) {
			this.context = c;
		}
		
		@Override
		public int getCount() {
			return mallList.size();
		}

		@Override
		public Object getItem(int position) {
			return mallList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		public String distanceKm(double lat1, double lon1, double lat2, double lon2) {
		    int EARTH_RADIUS_KM = 6371;
		    double lat1Rad = Math.toRadians(lat1);
		    double lat2Rad = Math.toRadians(lat2);
		    double deltaLonRad = Math.toRadians(lon2 - lon1);
		    return ""+ (Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.cos(deltaLonRad)) * EARTH_RADIUS_KM);
		}
		public int randInt(int min, int max) {
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflater = (LayoutInflater)
					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.mall_cards_ui, parent,false);
			
			TextView tv_row_title = (TextView) row.findViewById(R.id.tv_mall_card_mallname);
			TextView tv_row_disc = (TextView) row.findViewById(R.id.tv_mall_card_distance);
			ImageView iv_int = (ImageView) row.findViewById(R.id.iv_mall_card_getdirection);
			LinearLayout ll = (LinearLayout) row.findViewById(R.id.card_back);
			//ImageView iv_dir = (ImageView) row.findViewById(R.id.iv_dir);
			//ImageView iv_notint = (ImageView) row.findViewById(R.id.iv_notin);
			
			ll.setBackgroundResource(images[randInt(0, 3)]);
			
			iv_int.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "getting directions...", 0).show();
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						    Uri.parse("http://maps.google.com/maps?saddr="+geoLatitude+","+
						    		geoLongitude+"&daddr="+mallList.get(position).getMall_lati()+","+
						    		mallList.get(position).getMall_longi()));
						startActivity(intent);
				}
			});
			
			if( !mallList.isEmpty()){
				tv_row_title.setText(mallList.get(position).getMall_name());
				tv_row_disc.setText(distanceKm(Double.parseDouble(mallList.get(position).getMall_lati()),
						Double.parseDouble(mallList.get(position).getMall_longi()),
						geoLatitude,geoLongitude)	);
				
			}			
			return row;
		}		
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
			Intent viewIntent =
			          new Intent("android.intent.action.VIEW",
			            Uri.parse("http://www.olacabs.com//"));
			          startActivity(viewIntent);
			return true;
		}
		/*if (id == R.id.action_arview) {
			Intent i = new Intent(MallList.this,ARView.class);
			i.putExtra("loc",  mallList );
			startActivity(i);
        	return true;
			
		}*/
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		
	}

	@Override
	public void onDisconnected() {
		
	}

	
	
	protected void onStart() {
	      super.onStart();
	      
	   }
	   @Override
	   protected void onStop() {
	      super.onStop();
	      
	   }
}
