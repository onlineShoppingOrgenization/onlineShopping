package com.app.saffar.najme.onlineshopping;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    @Override
    public void onResume() {
        G.currentFragment=this;
        super.onResume();
    }



    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit, container, false);

        Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");

        TextView txt_name_edit=(TextView)view.findViewById(R.id.txt_name_edit);
        TextView txt_meghdar_edit=(TextView)view.findViewById(R.id.txt_meghdar_edit);
        TextView txt_description_edit=(TextView)view.findViewById(R.id.txt_description_edit);

        txt_name_edit.setTypeface(type);
        txt_meghdar_edit.setTypeface(type);
        txt_description_edit.setTypeface(type);


        final EditText edt_product1 = (EditText) FragmenProduct.prodoct_view.findViewById(R.id.edt_product);
        final EditText edt_amount1 = (EditText) FragmenProduct.prodoct_view.findViewById(R.id.edt_amount);
        final EditText edt_desc1 = (EditText) FragmenProduct.prodoct_view.findViewById(R.id.edt_desc);

        final EditText edt_product = (EditText) view.findViewById(R.id.edt_product2);
        final EditText edt_amount = (EditText) view.findViewById(R.id.edt_amount2);
        final EditText edt_desc = (EditText) view.findViewById(R.id.edt_desc2);

        edt_product.setTypeface(type);
        edt_amount.setTypeface(type);
        edt_desc.setTypeface(type);



        final Bundle bundle = this.getArguments();
        if(bundle != null) {
            String name = bundle.getString("name", "");
            String amount = bundle.getString("amount", "");
            String desc = bundle.getString("desc", "");

            edt_product.setText(name);
            edt_amount.setText(amount);
            edt_desc.setText(desc);
        }

        Button btn_product_remove=(Button) view.findViewById(R.id.btn_product_remove);
        btn_product_remove.setTypeface(type);

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



        Button btn_edit=(Button)view.findViewById(R.id.btn_product_edit);
        btn_edit.setTypeface(type);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundle != null && !FragmenProductList.addItem) {
                    int itemPos = bundle.getInt("itemPos", 0);
                    G.productList.remove(itemPos);
                }

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
        

        return view;
    }




}
