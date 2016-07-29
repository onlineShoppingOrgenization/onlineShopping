package com.app.saffar.najme.onlineshopping;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
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
public class FragmentWishHours extends Fragment {

    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    public static String time2;
    public static boolean set=false;
    public static boolean confirm_hour=false;

    public FragmentWishHours() {
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

                    FragmenDeliveryType nextFrag= new FragmenDeliveryType();
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
        View view =  inflater.inflate(R.layout.fragment_wish_hours, container, false);

        Resources system = Resources.getSystem();
        int hourNumberPickerId = system.getIdentifier("hour", "id", "android");
        int minuteNumberPickerId = system.getIdentifier("minute", "id", "android");
        int ampmNumberPickerId = system.getIdentifier("amPm", "id", "android");

        timePicker1 = (TimePicker) view.findViewById(R.id.timePicker1);
        NumberPicker hourNumberPicker = (NumberPicker) timePicker1.findViewById(hourNumberPickerId);
        NumberPicker minuteNumberPicker = (NumberPicker) timePicker1.findViewById(minuteNumberPickerId);
        NumberPicker ampmNumberPicker = (NumberPicker) timePicker1.findViewById(ampmNumberPickerId);

       setDividerColor(hourNumberPicker, Color.rgb(111,11,13));
        setDividerColor(minuteNumberPicker,Color.rgb(111,11,13));
      setDividerColor(ampmNumberPicker, Color.rgb(111,11,13));

        TextView txt_wish_hour=(TextView)view.findViewById(R.id.txt_wish_hour);
        Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");
        txt_wish_hour.setTypeface(type);

        Button set_hour=(Button)view.findViewById(R.id.setHour);

       // Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");
        set_hour.setTypeface(type);

        set_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set=true;

                calendar = Calendar.getInstance();

                timePicker1.clearFocus();
// re-read the values, in my case i put them in a Time object.
               int h = timePicker1.getCurrentHour();
                int m = timePicker1.getCurrentMinute();
                time2=null;
                showTime(h, m);


                confirm_hour=true;


                FragmenDeliveryType next=new FragmenDeliveryType();


                next.set_in_edittext("hbascyf");
                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, next,"")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button cancel=(Button)view.findViewById(R.id.cancelHour);

      //  Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");
        cancel.setTypeface(type);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmenDeliveryType next=new FragmenDeliveryType();
                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, next,"")
                        .addToBackStack(null)
                        .commit();
            }
        });



        return view;
    }

    public void showTime(int hour, int min) {
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
     String  time2=new StringBuilder().append(hour).append(" : ").append(min)
               .append(" ").append(format).toString();
      //  Toast.makeText(G.context,time2,Toast.LENGTH_SHORT).show();
        FragmenDeliveryType f=new FragmenDeliveryType();

        EditText edt=(EditText)f.DeliveryView.findViewById(R.id.edt_set_hour);
        Toast.makeText(G.context,time2,Toast.LENGTH_SHORT).show();
        edt.setText("fffffffffff");


    }


    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
