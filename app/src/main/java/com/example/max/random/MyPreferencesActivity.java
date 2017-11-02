package com.example.max.random;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        if(getActionBar()!=null){
            getActionBar().setDisplayShowHomeEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyPreferenceFragment()).commit();
        prepareLayout();

        if(getActionBar()!=null){
            getActionBar().setDisplayShowHomeEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }
    }

    private void prepareLayout() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        View content = root.getChildAt(0);
        RelativeLayout toolbarContainer = (RelativeLayout) View.inflate(this, R.layout.activity_preferences, null);

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);

        mToolBar = (Toolbar) toolbarContainer.findViewById(R.id.bar);
        mToolBar.setTitle("Settings");
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();//finish your activity
        }
        return super.onMenuItemSelected(featureId, item);
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}