package com.example.aleksandr.liberator.fragments;


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
public class PowerFragment extends ShowParamFragment {

    private Context mContext;


    public PowerFragment() {
    }

    public PowerFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (mContext == null) {
            mContext = getActivity();
        }

        int color = mContext.getResources().getColor(R.color.green);
        tvTitle.setText("Мощность");
        tvTitle.setTextColor(color);

        ivIcon.setImageResource(R.drawable.power);
        tvParams.setText("25");
        tvParams.setTextColor(color);


        tvValue.setText("кВт");
        tvValue.setTextColor(color);

        return view;
    }

}
