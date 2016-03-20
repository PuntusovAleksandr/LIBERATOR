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
import com.example.aleksandr.liberator.activity.ProcessActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessFragment extends Fragment {

    private TextView textProcess;
    private ImageView iconProcess;

    private int textInt, iconInt;

    public ProcessFragment() {
        // Required empty public constructor
        this.textInt = R.string.blow;
        this.iconInt = R.drawable.blow;
    }

    public ProcessFragment(int textInt, int iconInt) {
        this.textInt = textInt;
        this.iconInt = iconInt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_process, container, false);

        textProcess = (TextView) view.findViewById(R.id.tv_text_message_progress);
        iconProcess = (ImageView) view.findViewById(R.id.iv_icon_process);

        setStatusProcess();

        return view;
    }


    /**
     * for example
     */
    private void setStatusProcess() {
        textProcess.setText(textInt);
        iconProcess.setImageResource(iconInt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setLoad();
            }
        }, 1500);
    }

    private void setLoad() {
        textInt = R.string.loading;
        iconInt = R.drawable.load;
        textProcess.setText(textInt);
        iconProcess.setImageResource(iconInt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProcessActivity processActivity = (ProcessActivity) getActivity();
                if (processActivity != null) {
                    setFair();
                }
            }
        }, 1500);
    }

    private void setFair() {
        textInt = R.string.fairing;
        iconInt = R.drawable.fair;
        textProcess.setText(textInt);
        iconProcess.setImageResource(iconInt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProcessActivity processActivity = (ProcessActivity) getActivity();
                if (processActivity != null) {
                    setBurn();
                }
            }
        }, 1500);
    }

    private void setBurn() {
        textInt = R.string.blow;
        iconInt = R.drawable.burn;
        textProcess.setText(textInt);
        iconProcess.setImageResource(iconInt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProcessActivity processActivity = (ProcessActivity) getActivity();
                if (processActivity != null) {
                    getNextFragment();
                }
            }
        }, 1500);
    }


    public void getNextFragment() {
        ProcessActivity processActivity = (ProcessActivity) getActivity();
        if (processActivity != null) {
            processActivity.startWorkFragment();
            getActivity().getFragmentManager().popBackStack();
        }
    }
}
