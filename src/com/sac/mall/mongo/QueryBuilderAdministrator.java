package com.sac.mall.mongo;

import com.sac.mall.main.MyMall;

public class QueryBuilderAdministrator {
	
	/**
	 * Specify your database name here
	 * @return
	 */
	public String getDatabaseName() {
		return "events";
	}

	/**
	 * Specify your MongoLab API here
	 * @return
	 */
	public String getApiKey() {
		return "qYTGB8mXQVDxk-G7iBncyW5T_IkwdefU";
	}
	
	/**
	 * This constructs the URL that allows you to manage your database, 
	 * collections and documents
	 * @return
	 */
	public String getBaseUrl()
	{
		return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
	}
	
	/**
	 * Completes the formating of your URL and adds your API key at the end
	 * @return
	 */
	public String docApiKeyUrl()
	{
		return "?apiKey="+getApiKey();
	}
	
	/**
	 * Get a specified document
	 * @param docid
	 * @return
	 */
	public String docApiKeyUrl(String docid)
	{
		return "/"+docid+"?apiKey="+getApiKey();
	}
	
	/**
	 * Returns the docs101 collection
	 * @return
	 */
	public String documentRequest()
	{
		return "malls";
	}
	
	/**
	 * Builds a complete URL using the methods specified above
	 * @return
	 */
	public String buildContactsSaveURL()
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl();
	}
	
	/**
	 * This method is identical to the one above. 
	 * @return
	 */
	public String buildContactsGetURL()
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl();
	}
	
	/**
	 * Get a Mongodb document that corresponds to the given object id
	 * @param doc_id
	 * @return
	 */
	public String buildContactsUpdateURL(String doc_id)
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl(doc_id);
	}
	
	
	/**
	 * Formats the contact details for MongoHLab Posting
	 * @param contact: Details of the person 
	 * @return
	 */
	public String createContact(MyMall contact)
	{
		return String
				.format("{\"mall_name\": \"%s\", "
						+ "\"mall_desc\": \"%s\", \"mall_location\": \"%s\", "
						+ "\"mall_lati\": \"%s\", \"mall_longi\": \"%s\", "
						+ "\"mall_ratings\": \"%s\", \"mall_contacts\": \"%s\", "
						+ "\"mall_weburl\": \"%s\"}",
						contact.getMall_name(), contact.getMall_desc(), contact.getMall_location(), contact.getMall_lati()
						, contact.getMall_longi(), contact.getMall_ratings(), contact.getMall_contacts(),
						contact.getMall_weburl());
	}
	
	/**
	 * Update a given contact record
	 * @param contact
	 * @return
	 */
	public String setContactData(MyMall contact) {
		return String
				.format("{\"mall_name\": \"%s\", "
						+ "\"mall_desc\": \"%s\", \"mall_location\": \"%s\", "
						+ "\"mall_lati\": \"%s\", \"mall_longi\": \"%s\", "
						+ "\"mall_ratings\": \"%s\", \"mall_contacts\": \"%s\", "
						+ "\"mall_weburl\": \"%s\"}",
						contact.getMall_name(), contact.getMall_desc(), contact.getMall_location(), contact.getMall_lati()
						, contact.getMall_longi(), contact.getMall_ratings(), contact.getMall_contacts(),
						contact.getMall_weburl());
	}

/*	return String
			.format("{\"mall_name\": \"%s\", "
					+ "\"mall_desc\": \"%s\", \"mall_location\": \"%s\", "
					+ "\"mall_lati\": \"%s\", \"mall_longi\": \"%s\", "
					+ "\"mall_ratings\": \"%s\", \"mall_contacts\": \"%s\", "
					+ "\"mall_events\": \"%s\", \"mall_retailers\": \"%s\", "
					+ "\"mall_weburl\": \"%s\"}",
					contact.getMall_name(), contact.getMall_desc(), contact.getMall_location(), contact.getMall_lati()
					, contact.getMall_longi(), contact.getMall_ratings(), contact.getMall_contacts(),
					contact.getMall_events(),contact.getMall_retailers(),
					contact.getMall_weburl());*/
}
