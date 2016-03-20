package com.example.aleksandr.liberator;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.example.aleksandr.liberator.adapter.TabAdapter;
import com.example.aleksandr.liberator.fragments.SplashFragment;
import com.example.aleksandr.liberator.static_params.StaticParams;

public class StartActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    public static final String TAG_SPLASH_FRAGMENT = "SplashFragment";
    private ImageButton ibStartStop;

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
    }


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
