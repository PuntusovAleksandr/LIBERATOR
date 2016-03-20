package com.example.aleksandr.liberator.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.adapter.TabAdapter;
import com.example.aleksandr.liberator.fragments.SplashFragment;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Utils;

public class StartActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    public static final String TAG_SPLASH_FRAGMENT = "SplashFragment";
    private ImageButton ibStartStop, ibSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        fragmentManager = getFragmentManager();

        runSplash();
        setUi();

    }

    private void setUi() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabAdapter adapter = new TabAdapter(fragmentManager);
        viewPager.setAdapter(adapter);

        ibStartStop = (ImageButton) findViewById(R.id.ib_fab);
        ibSettings = (ImageButton) findViewById(R.id.ib_settings);

        ibStartStop.setOnClickListener(listener);
        ibSettings.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.disableButton(v);
            switch (v.getId()) {
                case R.id.ib_fab:
                    break;
                case R.id.ib_settings:
                    Intent intent = new Intent(StartActivity.this, SettingsAppActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void runSplash() {
        if (StaticParams.SHOW_SPLASH) {
            StaticParams.SHOW_SPLASH = false;
            SplashFragment splashFragment = new SplashFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, splashFragment, TAG_SPLASH_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        }
    }

}
