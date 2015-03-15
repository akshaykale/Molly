package com.sac.mall.mongo;


import com.sac.mall.main.User;

public class QueryBuilderCreateUser {
	
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
		return "users";
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
	public String createContact(User contact)
	{
		return String
		.format("{\"user_name\": \"%s\", "
				+ "\"user_email\": \"%s\","
				+ "\"user_password\": \"%s\"}",
				contact.getUser_name(), contact.getUser_email(), contact.getUser_password());
	}
	
	/**
	 * Update a given contact record
	 * @param contact
	 * @return
	 */
	public String setContactData(User contact) {
		return String.format("{ \"$set\" : " 
				+ "{\"user_name\": \"%s\", "
				+ "\"user_email\": \"%s\","
				+ "\"user_password\": \"%s\"}",
				contact.getUser_name(), contact.getUser_email(), contact.getUser_password());
	}
	
}
