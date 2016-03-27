package com.example.aleksandr.liberator.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.fragments.process_fragments.EndProcess;
import com.example.aleksandr.liberator.fragments.process_fragments.ProcessFragment;
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
    private ImageView ivFullIcon, imageViewPress;
    private RelativeLayout rlLeftParams, rlAir, rlProgress;

    /**
     * flag for check pressed button "Start" / "Stop"
     */
    private boolean pressedButtonStart;
    /**
     * flag for check end process
     */
    private boolean enableProcessEnd;

    /**
     * this value - for check fragment by nex pressed on button left or right
     */
    private int showStartFragment = StaticParams.MIN_START_FRAGMENT;

    /**
     * for double closed
     */
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        fragmentManager = getFragmentManager();

        runSplash();
        setUi();

    }

    /**
     * create and init all view
     */
    private void setUi() {

        ibStartStop = (ImageButton) findViewById(R.id.ib_start_stop);
        ibSettings = (ImageButton) findViewById(R.id.ib_settings);
        ibLeft = (ImageButton) findViewById(R.id.ib_left);
        ibRight = (ImageButton) findViewById(R.id.ib_right);

        ivFullIcon = (ImageView) findViewById(R.id.iv_circle_start);
        imageViewPress = (ImageView) findViewById(R.id.iv_circle_start_press);

        rlLeftParams = (RelativeLayout) findViewById(R.id.rl_left_params);
        rlAir = (RelativeLayout) findViewById(R.id.rl_air_params);
        rlProgress = (RelativeLayout) findViewById(R.id.rl_progress_bar);

        ibStartStop.setOnClickListener(listener);
        ibSettings.setOnClickListener(listener);
        ibLeft.setOnClickListener(listener);
        ibRight.setOnClickListener(listener);

        imageViewPress.setOnClickListener(listener);

        pressedButtonStart = false;
        enableProcessEnd = false;

        setInvisibleAllParams();
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.disableButton(v);
            Intent intent;
            switch (v.getId()) {
                // press left
                case R.id.ib_left:
                    if (showStartFragment == StaticParams.MIN_START_FRAGMENT) {
                        showStartFragment = StaticParams.MAX_START_FRAGMENT;
                    } else showStartFragment--;
                    showNextFragment(showStartFragment);
                    break;
                //press right
                case R.id.ib_right:
                    if (showStartFragment == StaticParams.MAX_START_FRAGMENT) {
                        showStartFragment = StaticParams.MIN_START_FRAGMENT;
                    } else showStartFragment++;
                    showNextFragment(showStartFragment);
                    break;
                // press button start
                case R.id.ib_start_stop:
                    if (pressedButtonStart && !enableProcessEnd) {
                        pressButtonStop();
                    } else if (pressedButtonStart && enableProcessEnd) {
                        startEndProcess();
                    } else {
                        pressButtonStart();
                    }
                    break;
                // press settings
                case R.id.ib_settings:
                    intent = new Intent(StartActivity.this, SettingsAppActivity.class);
                    startActivity(intent);
                    break;
             // press inner icon
                case R.id.iv_circle_start_press:
                    intent = new Intent(StartActivity.this, SetLocalParamActivity.class);
                    intent.putExtra(StaticParams.SHOW_FRAGMENT, showStartFragment);
                    startActivity(intent);
                    break;
            }
        }
    };


    /**
     * when pressed button "Stop"
     */
    public void pressButtonStop() {
        setStartFragment();
        enableButtonLeftRight();
        setInvisibleAllParams();

        imageViewPress.setEnabled(true);
        showStartFragment = StaticParams.MIN_START_FRAGMENT;
    }

    /**
     * when pressed button "Start"
     */
    private void pressButtonStart() {
        String tag = StaticParams.TAG_PROCESS_FRAGMENT;
        ProcessFragment fragment =
                (ProcessFragment) getFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = new ProcessFragment();
        }
        setFragment(fragment, tag);

        disableButtonLeftRight();
        imageViewPress.setEnabled(false);
        enableProcessEnd = false;
    }


    private void disableButtonLeftRight() {
        ibRight.setVisibility(View.INVISIBLE);
        ibLeft.setVisibility(View.INVISIBLE);
        ibSettings.setVisibility(View.INVISIBLE);
        pressedButtonStart = true;
        ibStartStop.setImageResource(R.drawable.stop);
    }


    private void enableButtonLeftRight() {
        ibRight.setVisibility(View.VISIBLE);
        ibLeft.setVisibility(View.VISIBLE);
        ibSettings.setVisibility(View.VISIBLE);
        pressedButtonStart = false;
        ibStartStop.setImageResource(R.drawable.start);
        ivFullIcon.setImageResource(R.drawable.full_start_circle_min);
    }


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

    /**
     * By first start call this method
     */
    private void runSplash() {
        pressedButtonStart = false;
        if (StaticParams.SHOW_SPLASH) {
            StaticParams.SHOW_SPLASH = false;
            SplashFragment splashFragment = new SplashFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, splashFragment, StaticParams.TAG_SPLASH_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        } else setStartFragment();
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

    public void setIvFullIconFromFragments(int res) {
        ivFullIcon.setImageResource(res);
    }

    public void showFullParams() {
        setVisibleAllParams();
        setStartFragment();
        enableProcessEnd = true;
        ibStartStop.setVisibility(View.VISIBLE);
        imageViewPress.setEnabled(true);
        showStartFragment = StaticParams.MIN_START_FRAGMENT;
    }

    /**
     * show all icons:
     * btLeft, btRight, btSettings, leftPanel, paramAir and progressFuel
     */
    private void setVisibleAllParams() {
        rlLeftParams.setVisibility(View.VISIBLE);
        rlAir.setVisibility(View.VISIBLE);
        rlProgress.setVisibility(View.VISIBLE);
        ibRight.setVisibility(View.VISIBLE);
        ibLeft.setVisibility(View.VISIBLE);
        ibSettings.setVisibility(View.VISIBLE);
        showStartFragment = StaticParams.MIN_START_FRAGMENT;
    }

    private void setInvisibleAllParams() {
        rlLeftParams.setVisibility(View.INVISIBLE);
        rlAir.setVisibility(View.INVISIBLE);
        rlProgress.setVisibility(View.INVISIBLE);
    }


    private void startEndProcess() {
        String tag = StaticParams.TAG_END_PROCESS_FRAGMENT;
        EndProcess fragment =
                (EndProcess) getFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = new EndProcess();
        }
        setFragment(fragment, tag);

        disableButtonLeftRight();
        setInvisibleAllParams();
        ibStartStop.setVisibility(View.INVISIBLE);
        imageViewPress.setEnabled(false);
    }

    public void setStartFragmentAfterEndProcess() {
        setStartFragment();
        enableButtonLeftRight();
        ibStartStop.setVisibility(View.VISIBLE);
        imageViewPress.setEnabled(true);
        showStartFragment = StaticParams.MIN_START_FRAGMENT;
    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            System.exit(0);
            return;
        }

            this.doubleBackToExitPressedOnce = true;
            Snackbar.make(ibLeft, R.string.double_click_to_exit, Snackbar.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
    }
}
