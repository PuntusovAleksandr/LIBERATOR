package com.example.aleksandr.liberator.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.fragments.process_fragments.ProcessFragment;
import com.example.aleksandr.liberator.fragments.start_fragments.SetTemperatureWaterFragment;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Utils;

public class ProcessActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageButton ibStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        fragmentManager = getFragmentManager();
        setProcessFragment();

        ibStop = (ImageButton) findViewById(R.id.ib_stop);
        ibStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.disableButton(v);
                if (v.getId() == R.id.ib_stop) {
                    // TODO: 20.03.2016 Надо сделать остановку ввсех процессов
                    stopAllProcess();
                    finish();
                }
            }
        });

    }

    private void stopAllProcess() {
        // TODO: 20.03.2016 Надо сделать остановку ввсех процессов
    }

    private void setProcessFragment() {
        ProcessFragment processFragment = new ProcessFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.process_coordinator, processFragment,
                StaticParams.TAG_PROCESS_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public void startWorkFragment() {
        SetTemperatureWaterFragment waterFragment = (SetTemperatureWaterFragment) fragmentManager
                .findFragmentByTag(StaticParams.TAG_SET_TEMPERATURE_FRAGMENT);
        if (waterFragment == null) {
            waterFragment = new SetTemperatureWaterFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.process_coordinator, waterFragment,
                    StaticParams.TAG_SET_TEMPERATURE_FRAGMENT);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            setEnableOtherParams();
        }
    }

    private void setEnableOtherParams() {


    }
}
