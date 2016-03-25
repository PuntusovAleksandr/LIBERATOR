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
public class TemperatureAirFragment extends ShowParamFragment {
    private Context mContext;

    public TemperatureAirFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (mContext == null) {
            mContext = getActivity();
        }

        int color = mContext.getResources().getColor(R.color.white);
        tvTitle.setText(R.string.air_);
        tvTitle.setTextColor(color);

        ivIcon.setImageResource(R.drawable.air);
        tvParams.setText("28");
        tvParams.setTextColor(color);

        tvValue.setText("%");
        tvValue.setTextColor(color);

        return view;
    }

}
