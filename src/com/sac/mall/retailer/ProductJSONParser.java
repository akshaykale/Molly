package com.sac.mall.retailer;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ProductJSONParser {
	
	public static ArrayList<Retailer> parseFeed( String content) {
		
		try {
			JSONArray jsonArray = new JSONArray(content);
			
			ArrayList<Retailer> produList = new ArrayList<Retailer>();
			
			for (int i=0; i<jsonArray.length(); i++){
				
				JSONObject obj = jsonArray.getJSONObject(i);
				
				Retailer product = new Retailer();
				
				product.setName(obj.getString("r_name"));
				product.setCategory(obj.getString("r_cat"));
				product.setDiscount(obj.getString("r_discount"));

				//Log.d("%%%%%",""+product.getDescription());
				
				produList.add(product);
				Log.d("@@@@@@@", "-->"+product.getName());
			}
			
			return produList;
			
		} catch (JSONException e) {
			e.printStackTrace();
			Log.d("%%%%%","Errorrrr");
			return null;
		}
	}
}
