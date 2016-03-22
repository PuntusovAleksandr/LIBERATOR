package com.example.aleksandr.liberator.fragments.process_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.activity.StartActivity;

/**
 * Created by Aleksandr on 23.03.2016.
 */
public class EndProcess extends Fragment {

    private TextView textProcess;
    private ImageView iconProcess;

    private int textInt, iconInt;

    public EndProcess() {
        // Required empty public constructor
        this.textInt = R.string.after_burning;
        this.iconInt = R.drawable.burn;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_process, container, false);

        textProcess = (TextView) view.findViewById(R.id.tv_text_message_progress);
        iconProcess = (ImageView) view.findViewById(R.id.iv_icon_process);

        textProcess.setText(textInt);
        iconProcess.setImageResource(iconInt);

        StartActivity activity = (StartActivity) getActivity();
        if (activity != null) {
            activity.setIvFullIconFromFragments(R.drawable.after_burning_full_circle);
        }

        setStatusProcess();

        return view;
    }


    /**
     * for example
     */
    private void setStatusProcess() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StartActivity activity = (StartActivity) getActivity();
                if (activity != null) {
                    activity.setIvFullIconFromFragments(R.drawable.after_burning_half_circle);
                    setLoad();
                }
            }
        }, 1500);
    }

    private void setLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StartActivity activity = (StartActivity) getActivity();
                if (activity != null) {
                    activity.setIvFullIconFromFragments(R.drawable.after_burning_end_circle);
                    setFair();
                }
            }
        }, 1500);
    }

    private void setFair() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StartActivity activity = (StartActivity) getActivity();
                if (activity != null) {
                    activity.setIvFullIconFromFragments(R.drawable.full_start_circle_min);
                    getNextFragment();
                }
            }
        }, 1500);
    }

    public void getNextFragment() {
        StartActivity activity = (StartActivity) getActivity();
        if (activity != null) {
            activity.setStartFragmentAfterEndProcess();
            getActivity().getFragmentManager().popBackStack();

        }
    }
}
