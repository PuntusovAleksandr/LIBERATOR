package com.example.aleksandr.liberator.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.fragments.process_fragments.ProcessFragment;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Utils;

public class ProcessActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
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
                    ыещзФддЗкщщсуыы();
                    finish();
                }
            }
        });

    }

    private void ыещзФддЗкщщсуыы() {
        // TODO: 20.03.2016 Надо сделать остановку ввсех процессов
    }

    private void setProcessFragment() {
        ProcessFragment processFragment = new ProcessFragment();
        fragmentManager.beginTransaction()
                .add(R.id.process_coordinator, processFragment, StaticParams.TAG_PROCESS_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }
}
