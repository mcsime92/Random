package com.example.max.random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button preferenceButton, rollButton, throwButton, showUserName;
    TextView nameResult, rollResult, throwResult;
    EditText editText;
    ImageView coinImage;
    Switch switchButton;
    LinearLayout linearLayout;

    public static final String MyPREFERENCES = "MyPreferences";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceButton = (Button) findViewById(R.id.preferenceButton);
        rollButton = (Button) findViewById(R.id.diceButton);
        throwButton = (Button) findViewById(R.id.throwButton);
        switchButton = (Switch) findViewById(R.id.switch1);
        showUserName = (Button) findViewById(R.id.showUserNameButton);

        throwResult = (TextView) findViewById(R.id.throwResult);
        rollResult = (TextView) findViewById(R.id.rollResult);
        nameResult = (TextView) findViewById(R.id.nameResult);

        coinImage = (ImageView) findViewById(R.id.coinImage);
        linearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

        //TODO: Adjust background according to chosen color theme.
        // String downloadType = sharedpreferences.getString("backgroundColor","Color.WHITE");
        //linearLayout.setBackgroundColor(Integer.parseInt(downloadType));

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        showUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String strUserName = SP.getString("username", "NA");
                nameResult.setText(strUserName);

            }
        });

        preferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MyPreferencesActivity.class);
                startActivity(i);

            }
        });


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

        //TODO: to be replaced by Preference function, and then switch button deleted.
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    linearLayout.setBackgroundColor(Color.RED);

                } else {
                    linearLayout.setBackgroundColor(Color.BLUE);
                }
            }

        });
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
}