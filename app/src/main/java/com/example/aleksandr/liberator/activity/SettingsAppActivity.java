package com.example.aleksandr.liberator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.utils.Utils;

public class SettingsAppActivity extends AppCompatActivity {

    private RelativeLayout rlBoouler, rlBurner, rlService, rlDiagnostic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_app);
        setToolBar();
        setUi();
    }

    private void setUi() {
        rlBoouler = (RelativeLayout) findViewById(R.id.rl_boiler);
        rlBurner = (RelativeLayout) findViewById(R.id.rl_burner);
        rlService = (RelativeLayout) findViewById(R.id.rl_menu);
        rlDiagnostic = (RelativeLayout) findViewById(R.id.rl_check);

        rlBoouler.setOnClickListener(listener);
        rlBurner.setOnClickListener(listener);
        rlService.setOnClickListener(listener);
        rlDiagnostic.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.disableButton(v);
            switch (v.getId()) {
                case R.id.rl_boiler:
                    break;
                case R.id.rl_burner:
                    break;
                case R.id.rl_menu:
                    break;
                case R.id.rl_check:
                    break;

            }
        }
    };

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.settings_app);


        /**
         * for back pressed
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
