package com.example.aleksandr.liberator.fragments.start_fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.activity.SettingsAppActivity;
import com.example.aleksandr.liberator.data_base.Db;
import com.example.aleksandr.liberator.utils.Settings;
import com.example.aleksandr.liberator.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutoStartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AutoStartFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int countSec;
    private TextView tvSec;
    private Handler mHandler;
    private Context mContext;
    private SharedPreferences sharedPreferences;

    public AutoStartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_auto_start, container, false);
        mContext = getActivity();
        if (mContext instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) mContext;
        } else {
            throw new RuntimeException(mContext.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        sharedPreferences = mContext.getSharedPreferences(Settings.FILE_NAME, Context.MODE_PRIVATE);
        Settings.setCountSeconds(30, sharedPreferences);

        tvSec = (TextView) mView.findViewById(R.id.tv_time_auto_start);

        ImageButton ibStopAutoStart = (ImageButton) mView.findViewById(R.id.ib_stop_auto_start);
        ibStopAutoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.disableButton(v);
                if (countSec > 0) {
                    Settings.setCountSeconds(countSec, sharedPreferences);
                }
                Intent intent = new Intent(getActivity(), SettingsAppActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Db.getInstance(mContext).isAutoStartEnable()) {
            countSec = Settings.getCountSeconds(sharedPreferences);
            setCountSeconds();
            Handler mHandler = new Handler();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    startCount();
                }
            });
        } else {
            onStartDisable();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mHandler != null) {
            mHandler.removeCallbacks(minusCount);
            if (countSec < Settings.getCountSeconds(sharedPreferences)) {
                Settings.setCountSeconds(countSec, sharedPreferences);
            }
        }
    }

    private void setCountSeconds() {
        tvSec.setText(countSec + "");
    }

    Runnable minusCount = new Runnable() {
        @Override
        public void run() {
            setCountSeconds();
            startCount();
        }
    };

    private void startCount() {
        if (countSec > 0) {
            countSec--;
            mHandler = new Handler();

            mHandler.postDelayed(minusCount, 1000);

        } else
            onButtonPressed();
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.autoStartIsEnable();
            Settings.setCountSeconds(30, sharedPreferences);
        }
    }

    public void onStartDisable() {
        if (mListener != null) {
            mListener.autoStartDisable();
            Settings.setCountSeconds(30, sharedPreferences);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void autoStartIsEnable();

        void autoStartDisable();
    }
}
