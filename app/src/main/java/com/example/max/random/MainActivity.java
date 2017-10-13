package com.example.max.random;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button rollButton, throwButton;
    TextView rollResult, throwResult;
    ImageView coinImage;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollButton = (Button) findViewById(R.id.diceButton);
        rollResult = (TextView) findViewById(R.id.rollResult);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(v);

            }
        });

        throwButton = (Button) findViewById(R.id.throwButton);
        throwResult = (TextView) findViewById(R.id.throwResult);
        coinImage = (ImageView) findViewById(R.id.coinImage);

        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throwCoin(v);
            }

        });

        linearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

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