package com.example.aleksandr.liberator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.static_params.StaticParams;

public class SetLocalParamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_local_param);

        initUi();

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int fragment = extra.getInt(StaticParams.SHOW_FRAGMENT);
        showNextFragment(fragment);
    }

    private void initUi() {
        ImageView ibBack = (ImageView) findViewById(R.id.iv_button_back);

        ibBack.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_button_back:
                    onBackPressed();
                    break;
            }
        }
    };


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
