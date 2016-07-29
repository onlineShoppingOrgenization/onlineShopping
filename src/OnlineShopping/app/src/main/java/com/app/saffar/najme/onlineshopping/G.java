package com.app.saffar.najme.onlineshopping;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class G extends Application {

    public static Context context;
    public static LayoutInflater inflater;
    public static Activity currentActivity;
    public static Fragment currentFragment;
    public static final Handler HANDLER = new Handler();
    public static  List<Product> productList = new ArrayList<>();

    public static ViewGroup myLayout;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
}
