package com.example.aleksandr.liberator.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.aleksandr.liberator.StartActivity;
import com.example.aleksandr.liberator.fragments.PowerFragment;
import com.example.aleksandr.liberator.fragments.SetTemperatureWaterFragment;
import com.example.aleksandr.liberator.fragments.TemperatureAirFragment;
import com.example.aleksandr.liberator.fragments.TemperatureWaterNowFragment;

/**
 * Created by Aleksandr on 20.03.2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private Context mContext;

    public TabAdapter(FragmentManager fm, StartActivity startActivity) {
        super(fm);
        this.mContext = startActivity;
        this.numberOfTabs = 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TemperatureWaterNowFragment(mContext);
            case 1:
                return new PowerFragment(mContext);
            case 2:
                return new SetTemperatureWaterFragment(mContext);
            case 3:
                return new TemperatureAirFragment(mContext);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        if (numberOfTabs > 0) {
            return numberOfTabs;
        } else
            return 0;
    }
}
