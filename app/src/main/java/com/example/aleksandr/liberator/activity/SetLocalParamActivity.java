package com.example.aleksandr.liberator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.static_params.StaticParams;

public class SetLocalParamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_local_param);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int fragment = extra.getInt(StaticParams.SHOW_FRAGMENT);

    }

    private void showNextFragment(int showStartFragment) {
        switch (showStartFragment) {
            case StaticParams.SHOW_TEMPERATURE_NOW:

                break;
            case StaticParams.SHOW_POWER:

                break;
            case StaticParams.SHOW_TEMPERATURE_WATER:

                break;
            case StaticParams.SHOW_TEMPERATURE_AIR:

                break;
        }
    }
}
