package com.sac.mall.mongo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sac.mall.main.MyMall;
/**
 * Async Task to retrieve your stored contacts from mongolab
 * @author KYAZZE MICHAEL
 *
 */
public class GetMallsAsyncTask extends AsyncTask<MyMall, Void, ArrayList<MyMall>> {
	static BasicDBObject user = null;
	static String OriginalObject = "";
	static String server_output = null;
	static String temp_output = null;

	@Override
	protected ArrayList<MyMall> doInBackground(MyMall... arg0) {
		
		ArrayList<MyMall> mycontacts = new ArrayList<MyMall>();
		try 
		{			
			
			QueryBuilderAdministrator qb = new QueryBuilderAdministrator();						
	        URL url = new URL(qb.buildContactsGetURL());
	        HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
	        conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			while ((temp_output = br.readLine()) != null) {
				server_output = temp_output;
			}
			
            // create a basic db list
			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
			Object o = com.mongodb.util.JSON.parse(mongoarray);
			

			DBObject dbObj = (DBObject) o;
			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
			
		  for (Object obj : contacts) {
			DBObject userObj = (DBObject) obj; 
			//Log.d("@@", "added");
			MyMall temp = new MyMall(); 
			temp.setMall_name(userObj.get("mall_name").toString());
			temp.setMall_desc(userObj.get("mall_desc").toString());
			temp.setMall_location(userObj.get("mall_location").toString());
			temp.setMall_lati(userObj.get("mall_lati").toString());
			temp.setMall_longi(userObj.get("mall_longi").toString());	
			temp.setMall_location(userObj.get("mall_location").toString());
			temp.setMall_ratings(userObj.get("mall_ratings").toString());
			temp.setMall_weburl(userObj.get("mall_weburl").toString());
			mycontacts.add(temp);
			
			
			}
		
		}catch (Exception e) {
			e.getMessage();
		}
		
		return mycontacts; 
	}
}
