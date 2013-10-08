package info.liuqy.adc.speakingenglish;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.content.Intent;

// use the option Content by Intent. based on ApiDemo tabs3.

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);

        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("En")
                .setContent(new Intent(this, SpeakingEnglishActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("Ch")
                .setContent(new Intent(this, SpeakingEnglishActivity.class)));

    }
}
