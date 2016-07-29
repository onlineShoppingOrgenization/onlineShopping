package com.app.saffar.najme.onlineshopping;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmenDeliveryType extends Fragment {
    public boolean wish_hours = false;
    public boolean one_houe = false;
    public boolean main=false;
    public TextView WishHours;
    public   static   View DeliveryView;
    // public static   EditText edt_set_hour;

    public static String format = "";

    String shoppingList = "";

    public FragmenDeliveryType() {
// Required empty public constructor
    }

    @Override
    public void onResume() {
        G.currentFragment = this;
        if (FragmentWishHours.set == true) {
            wish_hours = true;
        }
        if(FragmentWishHours.confirm_hour==true&&main==true){
            WishHours.setBackgroundColor(Color.parseColor("#FFfcaa18"));

        }
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
        DeliveryView = inflater.inflate(R.layout.fragment_delivery_type, container, false);
        main=true;

        Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");

     //   edt_set_hour=(EditText)view.findViewById(R.id.edt_set_hour);

        TextView txx_tahvil=(TextView)DeliveryView.findViewById(R.id.txt_tahvil);
        txx_tahvil.setTypeface(type);
         WishHours = (TextView) DeliveryView.findViewById(R.id.wish_hours);
        WishHours.setTypeface(type);
        final TextView OneHours = (TextView) DeliveryView.findViewById(R.id.one_hour);
        OneHours.setTypeface(type);

        WishHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wish_hours = true;
                one_houe = false;
                WishHours.setBackgroundColor(Color.parseColor("#FFfcaa18"));
                OneHours.setBackgroundColor(Color.parseColor("#FF6F0B0D"));

                FragmentWishHours wish = new FragmentWishHours();

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, wish, "")
                        .addToBackStack(null)
                        .commit();
            }
        });


        OneHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wish_hours = false;
                one_houe = true;
                OneHours.setBackgroundColor(Color.parseColor("#FFfcaa18"));
                WishHours.setBackgroundColor(Color.parseColor("#FF6F0B0D"));

                Calendar c = Calendar.getInstance();
                int hour= c.get(Calendar.HOUR);
                int min = c.get(Calendar.MINUTE);
                set_Time(hour,min);
            }
        });


        Button btn_send_order = (Button) DeliveryView.findViewById(R.id.btn_send_order);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String list = bundle.getString("list", "");

            if (list != "") {
                shoppingList = list;
            }
        }

        btn_send_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                if (wish_hours == true) {
// smsManager.sendTextMessage("09135467585", null, "ShoppingList \n" + shoppingList +"ساعت دلخواه" +"\n"+FragmentWishHours.time2, null, null);
// FragmentWishHours.time2=null;
                    Toast.makeText(G.context, FragmentWishHours.time2, Toast.LENGTH_SHORT).show();
                } else if (one_houe == true) {
// smsManager.sendTextMessage("09135467585", null, "ShoppingList \n" + shoppingList +" یک ساعت", null, null);
                }
                //smsManager.sendTextMessage("09137980399", null, "ShoppingList \n" + shoppingList, null, null);

                Toast.makeText(G.context, "Succsessfully Send " + shoppingList, Toast.LENGTH_SHORT).show();

/*FragmenProductList.addItem = true;
FragmenProductList nextFrag= new FragmenProductList();
Bundle bundle = new Bundle();
nextFrag.setArguments(bundle);

G.currentFragment.getFragmentManager().beginTransaction()
.replace(R.id.relativelayout_for_fragment, nextFrag,"")
.addToBackStack(null)
.commit();*/
            }
        });





        return DeliveryView;
    }

    private  void set_Time(int hour,int min){
        hour++;
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        //  time.setText(new StringBuilder().append(hour).append(" : ").append(min)
        //   .append(" ").append(format));
        String time=new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format).toString();

       EditText edt_set_hour=(EditText)DeliveryView.findViewById(R.id.edt_set_hour);
        edt_set_hour.setText(time);
    }

   public static void set_in_edittext(String name){

       EditText edt_set_hour=(EditText)DeliveryView.findViewById(R.id.edt_set_hour);
       String p=" by86v6v c5 "+name;
       edt_set_hour.setText(p);
       //Toast.makeText(G.context,time,Toast.LENGTH_SHORT).show();
    }
}