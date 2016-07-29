package com.app.saffar.najme.onlineshopping;

import android.support.v7.app.AppCompatActivity;


public class ActivityEnhanced extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onResume() {
        G.currentActivity = this;
        super.onResume();
    }
}

//


