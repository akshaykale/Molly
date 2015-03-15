package com.sac.mall.main;

import java.io.Serializable;

public class MyMall implements Serializable{

	public String mall_name;
	public String mall_desc;
	public String mall_location;
	public String mall_lati;
	public String mall_longi;
	public String mall_ratings;
	public String mall_contacts;
	public String mall_weburl;
	public String mall_retailers;
	public String mall_events;
	public String mall_highlights;
	public MyMall() {
		mall_name = "";
		mall_desc = "";
		mall_location = "";
		mall_lati = "12.1212";
		mall_longi = "73.3231";
		mall_ratings = "";
		mall_contacts = "";
		mall_weburl = "www.google.com";
		mall_retailers = "[{\"r_name\":\"Adidas\" , \"r_cat\":\"Sports\" , \"r_discount\":\"Flat 50% off\"} , " +
				" {\"r_name\":\"Nike\" , \"r_cat\":\"Sports\" , \"r_discount\":\"Flat 30% off\"} ," +
				" {\"r_name\":\"Mc. Donalds\" , \"r_cat\":\"Food\" , \"r_discount\":\"Rs. 50 off on 100 Rs purchase\"} ," +
				" {\"r_name\":\"KFC\" , \"r_cat\":\"Food\" , \"r_discount\":\"Crushers free on any large Meal\"} ," +
				" {\"r_name\":\"Levi's\" , \"r_cat\":\"Lifestyle\" , \"r_discount\":\"Flat 33% off\"} , " +
				" {\"r_name\":\"U.S. Polo\" , \"r_cat\":\"Lifestyle\" , \"r_discount\":\"Flat 27% off\"} , " +
				" {\"r_name\":\"TimeZone\" , \"r_cat\":\"Entertainment\" , \"r_discount\":\"Recharge of Rs 100 free on min recharge of 200\"} , " +
				" {\"r_name\":\"Horror House\" , \"r_cat\":\"Entertainment\" , \"r_discount\":\"Rs.50 Off\"} , " +
				" {\"r_name\":\"Reliance Digital\" , \"r_cat\":\"Other\" , \"r_discount\":\"10% cashback\"}  , " +
				" {\"r_name\":\"BigBazar\" , \"r_cat\":\"Other\" , \"r_discount\":\"Flat 40% off\"}]";
		mall_events = "Arjit Singh Concert, Blood Donation Camp";
		mall_highlights = "Womens day Special offers";
	}

	public String getMall_name() {
		return mall_name;
	}

	public void setMall_name(String mall_name) {
		this.mall_name = mall_name;
	}

	public String getMall_desc() {
		return mall_desc;
	}

	public void setMall_desc(String mall_desc) {
		this.mall_desc = mall_desc;
	}

	public String getMall_location() {
		return mall_location;
	}

	public void setMall_location(String mall_location) {
		this.mall_location = mall_location;
	}

	public String getMall_lati() {
		return mall_lati;
	}

	public void setMall_lati(String mall_lati) {
		this.mall_lati = mall_lati;
	}

	public String getMall_longi() {
		return mall_longi;
	}

	public void setMall_longi(String mall_longi) {
		this.mall_longi = mall_longi;
	}

	public String getMall_ratings() {
		return mall_ratings;
	}

	public void setMall_ratings(String mall_ratings) {
		this.mall_ratings = mall_ratings;
	}

	public String getMall_contacts() {
		return mall_contacts;
	}

	public void setMall_contacts(String mall_contacts) {
		this.mall_contacts = mall_contacts;
	}

	public String getMall_weburl() {
		return mall_weburl;
	}

	public void setMall_weburl(String mall_weburl) {
		this.mall_weburl = mall_weburl;
	}

	public String getMall_retailers() {
		return mall_retailers;
	}

	public void setMall_retailers(String mall_retailers) {
		this.mall_retailers = mall_retailers;
	}

	public String getMall_events() {
		return mall_events;
	}

	public void setMall_events(String mall_events) {
		this.mall_events = mall_events;
	}

	public String getMall_highlights() {
		return mall_highlights;
	}

	public void setMall_highlights(String mall_highlights) {
		this.mall_highlights = mall_highlights;
	}
	
	
	
	
	
	
	
	
	
	
	
	
/*	public String doc_id;
	public String first_name;
	public String last_name;
	public String phone;
	public String email;
	
	
	
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}*/
	
	

}
