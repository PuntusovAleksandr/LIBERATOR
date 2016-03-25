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
public class SetTemperatureWaterFragment extends ShowParamFragment {

    private Context mContext;

    public SetTemperatureWaterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (mContext == null) {
            mContext = getActivity();
        }

        // Required empty public constructor
        int color = mContext.getResources().getColor(R.color.red);
        tvTitle.setText(R.string.set_temperature);
        tvTitle.setTextColor(color);

        ivIcon.setImageResource(R.drawable.termometr);
        tvParams.setText("15");
        tvParams.setTextColor(color);


        tvValue.setText("C");
        tvValue.setTextColor(color);
        return view;
    }
}
