package com.example.skadv;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity  {
	private TabHost mTabHost;
	
	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//JSONObject json_o = null;
		super.onCreate(savedInstanceState);
		// hide title bar and etc
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        // --- end title hide
        
        // check network state
        CheckNetwork();
        // --- end check state
        
        // get json 
        //JsonParser json_p = new JsonParser();
        //JSONArray json_a = json_p.getJSONFromUrl(Config.category);
        
        
		setContentView(R.layout.activity_main);
		// create tabs and etc...
		setupTabHost();
		mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
		setupTab(new TextView(this), "Category", "Category.class");
		setupTab(new TextView(this), "Top", "Top.class");
		setupTab(new TextView(this), "Favorite", "Favorite.class");

	}
	
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
	
	private void setupTab(final View view, final String tag, final String className) {
		View tabview = createTabView(mTabHost.getContext(), tag);
			TabHost tabHost = getTabHost();    
			TabHost.TabSpec spec;   
				Intent intent;
			intent = new Intent().setClass(this, Category.class);
				if (className.equals("Category.class")) {
				intent = new Intent().setClass(this, Category.class);}
				if (className.equals("Top.class")) {
					intent = new Intent().setClass(this, Top.class);}
				if (className.equals("Favorite.class")) {
				intent = new Intent().setClass(this, Favorite.class);}


				TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(intent); {
				};
				mTabHost.addTab(setContent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  setContentView(R.layout.activity_main);
	}
	
	public boolean CheckNetwork() {
		boolean result_ = false;
	  Checker state = new Checker();
      state.SetContext(this);        
      state.SetUrl(Config.URL);
      boolean state_ = state.CheckNetworkState();
      boolean connect_ = state.CheckConnection();
      if (state_ == true && connect_ == true) {
      	result_ = true;
      } else {
       	Toast.makeText(getApplicationContext(), "Please, enable network.", Toast.LENGTH_SHORT).show();
      }
      return result_;
	} 	
}
