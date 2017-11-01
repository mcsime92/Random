package com.example.max.random;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MyPreferencesActivity extends PreferenceActivity {

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPreferenceFragment()).commit();
        prepareLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPreferenceFragment()).commit();
        prepareLayout();
    }

    private void prepareLayout() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        View content = root.getChildAt(0);
        RelativeLayout toolbarContainer = (RelativeLayout) View.inflate(this, R.layout.activity_preferences, null);

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);

        mToolBar = (Toolbar) toolbarContainer.findViewById(R.id.bar);
        mToolBar.setTitle(getTitle());
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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