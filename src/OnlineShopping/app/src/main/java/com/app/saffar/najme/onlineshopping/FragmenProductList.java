package com.app.saffar.najme.onlineshopping;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmenProductList extends Fragment {


    public static RecyclerView recyclerView;
    private ProductsAdapter mAdapter;
    boolean doubleBackToExitPressedOnce = false;

    FloatingActionButton fab;

    public static boolean addItem =false;
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
                   // Toast.makeText(G.context,"farzaneh",Toast.LENGTH_SHORT).show();

                    if (doubleBackToExitPressedOnce) {


                        return false;
                    }

                   doubleBackToExitPressedOnce = true;
                    Toast.makeText(G.context, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 2000);

                    return true;

                }

                return false;
            }
        });
    }


    public FragmenProductList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_list, container, false);
        Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");

        Button btn_continue = (Button) view.findViewById(R.id.btn_continue);
        btn_continue.setTypeface(type);
        TextView txt_shopping=(TextView)view.findViewById(R.id.txt_shopping);


        txt_shopping.setTypeface(type);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenDeliveryType nextFrag= new FragmenDeliveryType();
                Bundle bundle = new Bundle();

                String stringOfList = "";
                for(int i = 0; i < G.productList.size(); i++){
                    Product product = G.productList.get(i);
                    stringOfList = "Item" + i + ": " + product.getName() + ", " + product.getAmount() + ", " + product.getDesc() + "\n";
                }
                bundle.putString("list",  stringOfList);
                nextFrag.setArguments(bundle);

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        Animation animation = AnimationUtils.loadAnimation(G.context, R.anim.simple_grow);
        fab.startAnimation(animation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmenProduct nextFrag= new FragmenProduct();
                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setOnScrollListener(new MyRecyclerScroll() {
            @Override
            public void show() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
            @Override
            public void hide() {
                fab.animate().translationY(fab.getHeight() + 10).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        });

        mAdapter = new ProductsAdapter(G.productList);


        if(addItem){
            addItem = false;
            Bundle bundle = this.getArguments();
            if(bundle != null) {
                String name = bundle.getString("name", "");
                String amount = bundle.getString("amount", "");
                String desc = bundle.getString("desc", "");

                if(!name.equals("")) {
                    Product product = new Product(name, amount, desc);
                    G.productList.add(product);

                    mAdapter.notifyDataSetChanged();
                }
            }
        }


        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(G.context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //prepareMovieData();

        return view;

    }

    private void prepareMovieData() {
        Product movie = new Product("Mad Max: Fury Road", "Action & Adventure", "2015");
        G.productList.add(movie);

        movie = new Product("Inside Out", "Animation, Kids & Family", "2015");
        G.productList.add(movie);

        movie = new Product("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        G.productList.add(movie);

        movie = new Product("Shaun the Sheep", "Animation", "2015");
        G.productList.add(movie);

        movie = new Product("The Martian", "Science Fiction & Fantasy", "2015");
        G.productList.add(movie);

        movie = new Product("Mission: Impossible Rogue Nation", "Action", "2015");
        G.productList.add(movie);

        movie = new Product("Up", "Animation", "2009");
        G.productList.add(movie);

        movie = new Product("Star Trek", "Science Fiction", "2009");
        G.productList.add(movie);

        movie = new Product("The LEGO Movie", "Animation", "2014");
        G.productList.add(movie);

        movie = new Product("Iron Man", "Action & Adventure", "2008");
        G.productList.add(movie);

        movie = new Product("Aliens", "Science Fiction", "1986");
        G.productList.add(movie);

        movie = new Product("Chicken Run", "Animation", "2000");
        G.productList.add(movie);

        movie = new Product("Back to the Future", "Science Fiction", "1985");
        G.productList.add(movie);

        movie = new Product("Raiders of the Lost Ark", "Action & Adventure", "1981");
        G.productList.add(movie);

        movie = new Product("Goldfinger", "Action & Adventure", "1965");
        G.productList.add(movie);

        movie = new Product("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        G.productList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    /*
    public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            this.finish();
        }
        */
/*
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
    */


}
