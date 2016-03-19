package com.example.aleksandr.liberator.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;

/**
 * Created by Aleksandr on 20.03.2016.
 */
public class ShowParamFragment extends Fragment {

    TextView tvTitle, tvParams, tvValue;
    ImageView ivIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature_water_now, container, false);

        tvTitle = (TextView) view.findViewById(R.id.tv_title_param);
        tvParams = (TextView) view.findViewById(R.id.tv_temperature);
        tvValue = (TextView) view.findViewById(R.id.tv_value);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);


        return view;
    }

}
