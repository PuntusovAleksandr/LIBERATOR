package com.example.aleksandr.liberator.fragments.start_fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aleksandr.liberator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemperatureWaterNowFragment extends ShowParamFragment {

    private Context mContext;

    public TemperatureWaterNowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (mContext == null) {
            mContext = getActivity();
        }

        tvTitle.setText(R.string.temperature_now);
        int color = mContext.getResources().getColor(R.color.blue);
        tvTitle.setTextColor(color);

        ivIcon.setImageResource(R.drawable.termometr_blue);
        tvParams.setText("25");
        tvParams.setTextColor(color);

        tvValue.setText("C");
        tvValue.setTextColor(color);

        return view;
    }
}
