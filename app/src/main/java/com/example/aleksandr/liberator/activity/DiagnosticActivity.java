package com.example.aleksandr.liberator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.utils.Utils;

public class DiagnosticActivity extends AppCompatActivity {

    private RelativeLayout rlHaating, rlScrew, rlBurnerAuger, rlFan, rlBurnerClearning, rlAdditionReley;
    private ImageView btBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);

        setToolBar();
        setUi();


    }

    private void setUi() {
        rlHaating = (RelativeLayout) findViewById(R.id.rl_heating_element);
        rlScrew = (RelativeLayout) findViewById(R.id.rl_screw);
        rlBurnerAuger = (RelativeLayout) findViewById(R.id.rl_burner_auger);
        rlFan = (RelativeLayout) findViewById(R.id.rl_fan);
        rlBurnerClearning = (RelativeLayout) findViewById(R.id.rl_burner_cleaning);
        rlAdditionReley = (RelativeLayout) findViewById(R.id.rl_additional_relay);

        btBack = (ImageView) findViewById(R.id.iv_button_back_diagnostic);

        rlHaating.setOnClickListener(listener);
        rlScrew.setOnClickListener(listener);
        rlBurnerAuger.setOnClickListener(listener);
        rlFan.setOnClickListener(listener);
        rlBurnerClearning.setOnClickListener(listener);
        rlAdditionReley.setOnClickListener(listener);
        btBack.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.disableButton(v);
            switch (v.getId()) {
                case R.id.rl_heating_element:
                    break;
                case R.id.rl_screw:
                    break;
                case R.id.rl_burner_auger:
                    break;
                case R.id.rl_fan:
                    break;
                case R.id.rl_burner_cleaning:
                    break;
                case R.id.rl_additional_relay:
                    break;
                case R.id.iv_button_back_diagnostic:
                    onBackPressed();
                    finish();
                    break;

            }
        }
    };

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_diagnostic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.diagnostic);
        /**
         * for back pressed
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
