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
public class FragmentContactUs extends Fragment {

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


    public FragmentContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_contact_us, container, false);


        Typeface type = Typeface.createFromAsset(G.context.getAssets(),"fonts/font_sanz.ttf");


      //  TextView txt1=(TextView)view.findViewById(R.id.txt_countact_us1);
        TextView txt2=(TextView)view.findViewById(R.id.txtcontact_us2);
        TextView txt3=(TextView)view.findViewById(R.id.txt_contact_us3);
        TextView txt4=(TextView)view.findViewById(R.id.txt_contact_us4);
        TextView txt5=(TextView)view.findViewById(R.id.txt_contact_us5);
        TextView txt6=(TextView)view.findViewById(R.id.txt_contact_us6);
        TextView txt7=(TextView)view.findViewById(R.id.txt_contact_us7);

        //txt1.setTypeface(type);
        txt2.setTypeface(type);
        txt3.setTypeface(type);
        txt4.setTypeface(type);
        txt5.setTypeface(type);
        txt6.setTypeface(type);
        txt7.setTypeface(type);
        // Inflate the layout for this fragment
        return view;
    }


}
