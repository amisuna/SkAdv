/**
 * This class for check all network workaround.
 */
package com.example.skadv;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * @author Alex Misuno
 *
 */
public class Checker {
	Context mContext;
	private String url_;
	
	public void SetUrl(String url) {
		url_ = url;
	}
	
	public void SetContext(Context mContext) {
		this.mContext = mContext;
		
	}
	
	public boolean CheckConnection() {
		try {
            URL url = new URL(url_);
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(3000);
            urlc.connect();
            if (urlc.getResponseCode() == 200) {
                return true;
            }
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean CheckNetworkState() {
		ConnectivityManager cm =
		        (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		    return cm.getActiveNetworkInfo() != null && 
		       cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
}
