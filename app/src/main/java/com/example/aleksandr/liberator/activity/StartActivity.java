package com.example.aleksandr.liberator.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.fragments.start_fragments.PowerFragment;
import com.example.aleksandr.liberator.fragments.start_fragments.SetTemperatureWaterFragment;
import com.example.aleksandr.liberator.fragments.start_fragments.SplashFragment;
import com.example.aleksandr.liberator.fragments.start_fragments.TemperatureAirFragment;
import com.example.aleksandr.liberator.fragments.start_fragments.TemperatureWaterNowFragment;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Utils;

public class StartActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private ImageButton ibStartStop, ibSettings, ibLeft, ibRight;

    /**
     * this value - for check fragment by nex pressed on button left or right
     */
    private int showStartFragment = StaticParams.MIN_START_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        fragmentManager = getFragmentManager();

        runSplash();
        setUi();

    }

    private void setUi() {

        ibStartStop = (ImageButton) findViewById(R.id.ib_start_stop);
        ibSettings = (ImageButton) findViewById(R.id.ib_settings);
        ibLeft = (ImageButton) findViewById(R.id.ib_left);
        ibRight = (ImageButton) findViewById(R.id.ib_right);

        ibStartStop.setOnClickListener(listener);
        ibSettings.setOnClickListener(listener);
        ibLeft.setOnClickListener(listener);
        ibRight.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.disableButton(v);
            Intent intent;
            switch (v.getId()) {
                case R.id.ib_left:
                    if (showStartFragment == StaticParams.MIN_START_FRAGMENT) {
                        showStartFragment = StaticParams.MAX_START_FRAGMENT;
                    }else showStartFragment--;
                    showNextFragment(showStartFragment);
                    break;
                case R.id.ib_right:
                    if (showStartFragment == StaticParams.MAX_START_FRAGMENT) {
                        showStartFragment = StaticParams.MIN_START_FRAGMENT;
                    }else showStartFragment++;
                    showNextFragment(showStartFragment);
                    break;
                case R.id.ib_start_stop:
                    // TODO: 22.03.2016 click button
                    break;
                case R.id.ib_settings:
                    intent = new Intent(StartActivity.this, SettingsAppActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void showNextFragment(int showStartFragment) {
        String tag;
        Fragment fragment;
        switch (showStartFragment) {
            case StaticParams.SHOW_TEMPERATURE_NOW:
                tag = StaticParams.TAG_TEMPERATURE_NOW_FRAGMENT;
                fragment = getFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new TemperatureWaterNowFragment();
                }
                setFragment(fragment, tag);
                break;
            case StaticParams.SHOW_POWER:
                tag = StaticParams.TAG_POWER_FRAGMENT;
                fragment = getFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new PowerFragment();
                }
                setFragment(fragment, tag);
                break;
            case StaticParams.SHOW_TEMPERATURE_WATER:
                tag = StaticParams.TAG_WATER_FRAGMENT;
                fragment = getFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new SetTemperatureWaterFragment();
                }
                setFragment(fragment, tag);
                break;
            case StaticParams.SHOW_TEMPERATURE_AIR:
                tag = StaticParams.TAG_AIR_FRAGMENT;
                fragment = getFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = new TemperatureAirFragment();
                }
                setFragment(fragment, tag);
                break;
        }
    }

    private void setFragment(Fragment fragment, String tag) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, tag)
                .commit();
    }


    private void runSplash() {
        if (StaticParams.SHOW_SPLASH) {
            StaticParams.SHOW_SPLASH = false;
            SplashFragment splashFragment = new SplashFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, splashFragment, StaticParams.TAG_SPLASH_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        }else setStartFragment();
    }

    public void setStartFragment() {
        TemperatureWaterNowFragment temperatureWaterNowFragment =
                (TemperatureWaterNowFragment) getFragmentManager().
                        findFragmentByTag(StaticParams.TAG_TEMPERATURE_NOW_FRAGMENT);
        if (temperatureWaterNowFragment == null) {
            temperatureWaterNowFragment = new TemperatureWaterNowFragment();
        }
        setFragment(temperatureWaterNowFragment, StaticParams.TAG_TEMPERATURE_NOW_FRAGMENT);
    }


}
