package com.app.saffar.najme.onlineshopping;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmenProduct extends Fragment {
    public static View prodoct_view;


    public FragmenProduct() {
        // Required empty public constructor
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       prodoct_view =  inflater.inflate(R.layout.fragment_product, container, false);

        Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");

        TextView txx_add_g00d=(TextView)prodoct_view.findViewById(R.id.txt_add_good);
        txx_add_g00d.setTypeface(type);
        Button btn_product_registration = (Button) prodoct_view.findViewById(R.id.btn_product_registration);
        btn_product_registration.setTypeface(type);
      //  Button btn_product_remove = (Button) view.findViewById(R.id.btn_product_remove);
      //  btn_product_remove.setTypeface(type);

        final EditText edt_product = (EditText) prodoct_view.findViewById(R.id.edt_product);
        final EditText edt_amount = (EditText) prodoct_view.findViewById(R.id.edt_amount);
        final EditText edt_desc = (EditText) prodoct_view.findViewById(R.id.edt_desc);

        final Bundle bundle = this.getArguments();
        if(bundle != null) {
            String name = bundle.getString("name", "");
            String amount = bundle.getString("amount", "");
            String desc = bundle.getString("desc", "");

            edt_product.setText(name);
            edt_amount.setText(amount);
            edt_desc.setText(desc);
        }

        btn_product_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenProductList.addItem = true;
                FragmenProductList nextFrag= new FragmenProductList();
                Bundle bundle = new Bundle();
                bundle.putString("name", edt_product.getText().toString());
                bundle.putString("amount", edt_amount.getText().toString());
                bundle.putString("desc", edt_desc.getText().toString());
                nextFrag.setArguments(bundle);

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();
            }
        });

        /*
        btn_product_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundle != null && !FragmenProductList.addItem) {
                    int itemPos = bundle.getInt("itemPos", 0);
                    G.productList.remove(itemPos);
                }

                FragmenProductList nextFrag= new FragmenProductList();

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();
            }
        });
        */

        prodoct_view.setFocusableInTouchMode(true);
        prodoct_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if( keyCode == KeyEvent.KEYCODE_BACK ){

                    Toast.makeText(G.context,"wishFragment",Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

        });
        return prodoct_view;
    }

/*
    @Override
    public void doBack() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            //TODO: Perform your logic to pass back press here
            for(Fragment fragment : fragmentList){
                if(fragment instanceof OnBackPressedListener){
                    ((OnBackPressedListener)fragment).onBackPressed();
                }
            }
        }
    }
    */
}
