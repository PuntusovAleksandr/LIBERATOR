package com.example.aleksandr.liberator.fragments.start_fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.static_params.StaticParams;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        StaticParams.SHOW_SPLASH = false;
        SplashTaak splashTaak = new SplashTaak();
        splashTaak.execute();

        return view;
    }

    class SplashTaak extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            try {
                TimeUnit.SECONDS.sleep(StaticParams.TIME_START);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getActivity() != null) {
                getActivity().getFragmentManager().popBackStack();
            }
            return null;
        }
    }

}
