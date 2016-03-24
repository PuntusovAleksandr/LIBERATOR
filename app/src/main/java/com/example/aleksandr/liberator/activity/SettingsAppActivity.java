package com.example.aleksandr.liberator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Utils;

public class SettingsAppActivity extends AppCompatActivity {

    private RelativeLayout rlBoouler, rlBurner, rlService, rlDiagnostic;
    private ImageView btBack;

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

        btBack = (ImageView) findViewById(R.id.iv_button_back_settings);

        rlBoouler.setOnClickListener(listener);
        rlBurner.setOnClickListener(listener);
        rlService.setOnClickListener(listener);
        rlDiagnostic.setOnClickListener(listener);
        btBack.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.disableButton(v);
            switch (v.getId()) {
                case R.id.rl_boiler:
                    goToSetParams(R.string.params_boiler, R.color.blue);
                    break;
                case R.id.rl_burner:
                    goToSetParams(R.string.params_burner, R.color.color_splash);
                    break;
                case R.id.rl_menu:
                    goToSetParams(R.string.service, R.color.red);
                    break;
                case R.id.rl_check:
                    Intent intent = new Intent(SettingsAppActivity.this, DiagnosticActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_button_back_settings:
                    onBackPressed();
                    finish();
                    break;

            }
        }
    };

    private void goToSetParams(int title, int color) {
        Intent intent = new Intent(SettingsAppActivity.this, SettingsSetParamsActivity.class);
        intent.putExtra(StaticParams.KEY_TITLE, title);
        intent.putExtra(StaticParams.KEY_COLOR, color);
        startActivity(intent);
    }

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
