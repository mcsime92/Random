package com.example.max.random;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MyPreferencesActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        imageView = (ImageView) findViewById(R.id.toolbar_image);
        mToolBar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("Settings");

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPreferenceFragment()).commit();
        prepareLayout();

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(getApplicationContext(), "your icon was clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        mToolBar = (Toolbar) findViewById(R.id.bar);
        imageView = (ImageView) findViewById(R.id.toolbar_image);
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("Settings");

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPreferenceFragment()).commit();
        prepareLayout();

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "your icon was clicked", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void prepareLayout() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        View content = root.getChildAt(0);
        RelativeLayout toolbarContainer = (RelativeLayout) View.inflate(this, R.layout.activity_preferences, null);

        mToolBar = (Toolbar) toolbarContainer.findViewById(R.id.bar);
        imageView = (ImageView) findViewById(R.id.toolbar_image);

        setSupportActionBar(mToolBar);
        mToolBar.setTitle("Settings");

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "your icon was clicked", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}