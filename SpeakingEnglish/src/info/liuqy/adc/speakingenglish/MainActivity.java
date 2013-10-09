package info.liuqy.adc.speakingenglish;

import android.app.TabActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.content.Intent;
import android.content.SharedPreferences;

// use the option Content by Intent. based on ApiDemo tabs3.

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements OnTabChangeListener {
	
	static final String PREFERENCE_NAME = "setting";
	static final String SAVED_TAB = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);

        final TabHost tabHost = getTabHost();

        {
        	Intent intentEn = new Intent(this, SpeakingEnglishActivity.class); 
        	intentEn.setAction(SpeakingEnglishActivity.TYPE_EN);
        	tabHost.addTab(tabHost.newTabSpec("tab1")
        			              .setIndicator("En")
        			              .setContent(intentEn));
        }
        
        {
        	Intent intentCh = new Intent(this, SpeakingEnglishActivity.class);

        	intentCh.setAction(SpeakingEnglishActivity.TYPE_CH);
        	tabHost.addTab(tabHost.newTabSpec("tab2")
        			              .setIndicator("Ch")
        		                  .setContent(intentCh));
        }
        
        //TODO: set activated tab.
        int index = 0;
        
        SharedPreferences settings = getSharedPreferences(PREFERENCE_NAME, 0);
        
        
        String savedTab  = settings.getString(SAVED_TAB, "tab0");
        
        Log.i("mainABC", "OnCreate:" + savedTab);
        
        if ("tab1".equalsIgnoreCase(savedTab)) {
        	index = 0;
        } else if ("tab2".equalsIgnoreCase(savedTab)) {
        	index = 1;
        } else {
            Log.i("mainABC", "OnCreate: no saved data");
            index = 0;
        }
        
        tabHost.setCurrentTab(index);
        
        // Let activity to handle onTabChanged directly as we implements the
        // OnTabChangeListener interface. Alternative is to
        // have an in-line OnTabChangeListener instance.
    	//  http://www.oschina.net/question/54100_32483
    	//  mTabHost.setOnTabChangedListener(new OnTabChangeListener()
        tabHost.setOnTabChangedListener(this);
    }
    
    @Override
    public void onTabChanged(String tabId) {
    	Log.i("mainABC", "onTabChanged:" + tabId);
    }

    // TODO: how about to put it in OnStop ?
    @Override
    protected void onPause() {
    
    	// save the data.
    	
    	TabHost tabHost = getTabHost();
    	String currentTab = tabHost.getCurrentTabTag();
    	Log.i("mainABC", "OnPause:" + currentTab);
    	SharedPreferences settings = getSharedPreferences(PREFERENCE_NAME, 0);
    	
    	if (!currentTab.equalsIgnoreCase(settings.getString(SAVED_TAB, "tab0"))) {
    	
        	SharedPreferences.Editor editor = settings.edit();
        	
        	editor.putString(SAVED_TAB, currentTab);
        	
        	editor.commit();
        	
        	Log.i("mainABC", "OnPause: save " + currentTab);

    	}
    	
    	super.onPause();
    }
    
    public void clearTab () {
    	
    	SharedPreferences settings = getSharedPreferences(PREFERENCE_NAME, 0);
    	
    	if (!"tab0".equalsIgnoreCase(settings.getString(SAVED_TAB, "tab0"))) {
    	
        	SharedPreferences.Editor editor = settings.edit();
        	
        	editor.remove(SAVED_TAB);
        	
        	editor.commit();
        	
        }
    }
}
