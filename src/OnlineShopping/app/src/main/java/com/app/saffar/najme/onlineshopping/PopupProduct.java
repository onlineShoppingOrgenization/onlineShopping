package com.app.saffar.najme.onlineshopping;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by najme_sa on 25/07/2016.
 */
public class PopupProduct extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_product);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;



        //img_fade.setVisibility(View.VISIBLE);

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));

    }
}
