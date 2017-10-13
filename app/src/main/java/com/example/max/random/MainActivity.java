package com.example.max.random;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class MainActivity extends AppCompatActivity {

    Button saveButton, rollButton, throwButton;
    TextView rollResult, throwResult;
    EditText editText;
    ImageView coinImage;
    Switch switchButton;
    LinearLayout linearLayout;

    public static final String MyPREFERENCES = "MyPreferences" ;
    public static final String Name = "nameKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = (Button) findViewById(R.id.saveButton);
        rollButton = (Button) findViewById(R.id.diceButton);
        throwButton = (Button) findViewById(R.id.throwButton);
        switchButton = (Switch) findViewById(R.id.switch1);

        throwResult = (TextView) findViewById(R.id.throwResult);
        rollResult = (TextView) findViewById(R.id.rollResult);
        editText = (EditText) findViewById(R.id.editText);

        coinImage = (ImageView) findViewById(R.id.coinImage);
        linearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, name);
                //apply() seems better than commit()
                editor.apply();
            }
        });

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