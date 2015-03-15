package com.sac.mall.retailer;

import java.util.ArrayList;

import com.sac.mall.R;
import com.sac.mall.main.MyMall;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodRetailer extends Activity{

	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_retailer);
		lv = (ListView) findViewById(R.id.lv_food);
		MyMall m = (MyMall) getIntent().getExtras().getSerializable("mall_obj");
		
		/*Toast.makeText(getApplicationContext(), ""+m.getMall_name()
				+"\n"+m.getMall_desc()
				+"\n"+m.getMall_lati()
				+"\n"+m.getMall_location()
				+"\n"+m.getMall_longi()
				+"\n"+m.getMall_ratings()
				+"\n"+m.getMall_retailers(), 0).show();*/
		
		ArrayList<Retailer> retailers = ProductJSONParser.parseFeed(m.getMall_retailers());
		
		final String[] r_n = new String[2];
		final String[] r_d = new String[2];
		int i =0;
		for(Retailer r : retailers){
			if(r.category.equals("Food")){
				r_n[i] = r.getName();r_d[i] = r.getDiscount();
				i++;
			}
		}
		Log.d("@@@@@@@", r_n[0]+"..."+r_n[1]);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, r_n);
	    
	    
       // Assign adapter to ListView
        lv.setAdapter(adapter); 
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				Dialog d = new Dialog(FoodRetailer.this);
				d.setContentView(R.layout.dialog);
				d.setCancelable(true);
				TextView tv = (TextView) d.findViewById(R.id.tvd);
				d.setTitle(r_n[pos]);
				tv.setText(r_d[pos]);
				d.show();
				
			}
		});
	}
}
