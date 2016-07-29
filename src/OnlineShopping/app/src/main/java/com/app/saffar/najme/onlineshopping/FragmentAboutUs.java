package com.app.saffar.najme.onlineshopping;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAboutUs extends Fragment {

    @Override
    public void onResume() {
        G.currentFragment=this;
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    // handle back button
                    Toast.makeText(G.context,"farzaneh",Toast.LENGTH_SHORT).show();

                    FragmenProductList nextFrag= new FragmenProductList();
                    G.currentFragment.getFragmentManager().beginTransaction()
                            .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                            .addToBackStack(null)
                            .commit();

                    return true;

                }

                return false;
            }
        });
    }


    public FragmentAboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about_us, container, false);


        Typeface type = Typeface.createFromAsset(G.context.getAssets(),"fonts/font_sanz.ttf");


        TextView txt1=(TextView)view.findViewById(R.id.txt_about_us1);
        TextView txt2=(TextView)view.findViewById(R.id.txt_about_us2);
        TextView txt3=(TextView)view.findViewById(R.id.txt_about_us3);
        TextView txt4=(TextView)view.findViewById(R.id.txt_about_us4);
        TextView txt5=(TextView)view.findViewById(R.id.txt_about_us5);
        TextView txt6=(TextView)view.findViewById(R.id.txt_about_us6);
        TextView txt7=(TextView)view.findViewById(R.id.txt_about_us7);
        TextView txt8=(TextView)view.findViewById(R.id.txt_about_us8);
        TextView txt9=(TextView)view.findViewById(R.id.txt_about_us9);
        TextView txt10=(TextView)view.findViewById(R.id.txt_about_us10);
        TextView txt11=(TextView)view.findViewById(R.id.txt_about_us11);
        TextView txt12=(TextView)view.findViewById(R.id.txt_about_us12);


        txt1.setTypeface(type);
        txt2.setTypeface(type);
        txt3.setTypeface(type);
        txt4.setTypeface(type);
        txt5.setTypeface(type);
        txt6.setTypeface(type);
        txt8.setTypeface(type);
        txt9.setTypeface(type);
        txt10.setTypeface(type);
        txt11.setTypeface(type);
        txt12.setTypeface(type);
        // Inflate the layout for this fragment
        return view;
    }

}
