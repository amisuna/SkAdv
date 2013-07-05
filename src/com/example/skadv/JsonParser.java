/**
 * Class for work with json data.
 */
package com.example.skadv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alex Misuno
 *
 */
public class JsonParser {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	
	// constructor
	public JsonParser() {

	}
	
	public JSONArray getJSONFromUrl(String url) {

		InputStream is = null;
		String result = "";
		JSONArray jsonArray = null;
		
		// HTTP
		try {	    	
			HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch(Exception e) {
			return null;
		}
	    
		// Read response to string
		try {	    	
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();	            
		} catch(Exception e) {
			return null;
		}
 
		// Convert string to object
		try {
			jsonArray = new JSONArray(result);            
		} catch(JSONException e) {
			return null;
		}
    
		return jsonArray;
 
	}
	
	
}
