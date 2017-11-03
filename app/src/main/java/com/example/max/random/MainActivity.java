package com.example.max.random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button rollButton;
    Button throwButton;
    TextView nameResult, rollResult, throwResult;
    ImageView coinImage;
    LinearLayout linearLayout;
    Toolbar toolbar;

    DrawerLayout mDrawer;
    ActionBarDrawerToggle actionBarDrawerToggle;

    public static final String MyPREFERENCES = "MyPreferences";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollButton = (Button) findViewById(R.id.diceButton);
        throwButton = (Button) findViewById(R.id.throwButton);

        throwResult = (TextView) findViewById(R.id.throwResult);
        rollResult = (TextView) findViewById(R.id.rollResult);
        nameResult = (TextView) findViewById(R.id.nameResult);

        coinImage = (ImageView) findViewById(R.id.coinImage);
        linearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawer.addDrawerListener(actionBarDrawerToggle);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(v);
            }
        });

        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throwCoin(v);
            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();

        //Username
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String strUserName = SP.getString("username", "NA");
        toolbar.setTitle("Home");

        //TODO: add welcoming msg to the SetText.
        nameResult.setText(strUserName);

        //Background color
        String backgroundColor = SP.getString("backgroundColor", "NA");

        //TODO: rewrite with switch between cases

        if (backgroundColor.equals("Color.GREEN")) {
            linearLayout.setBackgroundColor(Color.GREEN);
        } else if (backgroundColor.equals("Color.GRAY")) {
            linearLayout.setBackgroundColor(Color.GRAY);
        } else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }

    }

    //TODO: 1. add a timer to put space between function calls.
    //TODO: 2. add visuals of dice or coin spinning.

    public void rollDice(View v) {

        int diceResult = (int) (Math.random() * 6) + 1;
        rollResult.setText(String.valueOf(diceResult));
    }

    public void throwCoin(View v) {
        int coinResult = (int) (Math.random() * 2) + 1;

        if (coinResult > 1) {

            throwResult.setText("Head");
            Drawable res = ContextCompat.getDrawable(this, R.drawable.head);
            coinImage.setImageDrawable(res);

        } else {
            throwResult.setText("Tail");
            Drawable res = ContextCompat.getDrawable(this, R.drawable.tail);
            coinImage.setImageDrawable(res);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        String title = "";

        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                title = getString(R.string.catg_drawer1);

                getSupportActionBar().setTitle(title);
                break;

            case R.id.nav_second_fragment:
                Intent j = new Intent(getApplicationContext(), MyPreferencesActivity.class);
                j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(j);

                title = getString(R.string.catg_drawer2);

                getSupportActionBar().setTitle(title);
                break;

            default:
                title = getString(R.string.catg_drawer1);
                getSupportActionBar().setTitle(title);
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

}