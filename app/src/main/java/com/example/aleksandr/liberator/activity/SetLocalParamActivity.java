package com.example.aleksandr.liberator.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.static_params.StaticParams;

public class SetLocalParamActivity extends AppCompatActivity {

    private ImageButton ibMinus, ibPlus, ibOk;
    private TextView tvTitle, tvParam, tvValue;
    private ImageView ivIconFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_local_param);

        initUi();

        int fragment =
                getIntent().getIntExtra(StaticParams.SHOW_FRAGMENT, StaticParams.MIN_START_FRAGMENT);
        showNextFragment(fragment);
    }

    private void initUi() {
        ImageView ibBack = (ImageView) findViewById(R.id.iv_button_back);
        ivIconFragment = (ImageView) findViewById(R.id.iv_icon_process);

        tvTitle = (TextView) findViewById(R.id.tv_title_local);
        tvParam = (TextView) findViewById(R.id.tv_param_local);
        tvValue = (TextView) findViewById(R.id.tv_value_local);

        ibMinus = (ImageButton) findViewById(R.id.ib_minus);
        ibPlus = (ImageButton) findViewById(R.id.ib_plus);
        ibOk = (ImageButton) findViewById(R.id.ib_ok);

        ibMinus.setOnClickListener(listener);
        ibPlus.setOnClickListener(listener);
        ibOk.setOnClickListener(listener);

        ibBack.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String string = tvParam.getText().toString();
            int valueParam = 0;
            if (string.length() > 0) {
                valueParam = Integer.parseInt(string);
            }
            switch (v.getId()) {
                case R.id.ib_minus:
                    if (valueParam > 0) {
                        valueParam--;
                        tvParam.setText(String.valueOf(valueParam));
                    }
                    break;
                case R.id.ib_plus:
                    if (valueParam < 100) {
                        valueParam++;
                        tvParam.setText(String.valueOf(valueParam));
                    }
                    break;
                case R.id.ib_ok:
                    saveDataParams();
                    Snackbar.make(v, "Параиетр " + valueParam + " принят", Snackbar.LENGTH_SHORT).show();
                    break;

                case R.id.iv_button_back:
                    onBackPressed();
                    break;
            }
        }
    };

    private void saveDataParams() {
        // TODO: 25.03.2016 надо сделать сохранение параметров в БД
    }

    /**
     * show fragment's settings
     *
     * @param showStartFragment
     */
    private void showNextFragment(int showStartFragment) {
        switch (showStartFragment) {
            case StaticParams.SHOW_TEMPERATURE_NOW:
                setParamsUi(R.string.temperature_now, R.string.celsij, Color.BLUE, R.drawable.termometr_blue);
                break;
            case StaticParams.SHOW_POWER:
                setParamsUi(R.string.power, R.string.power_value, Color.GREEN, R.drawable.power);
                break;
            case StaticParams.SHOW_TEMPERATURE_WATER:
                setParamsUi(R.string.set_temperature, R.string.celsij, Color.RED, R.drawable.termometr);
                break;
            case StaticParams.SHOW_TEMPERATURE_AIR:
                setParamsUi(R.string.air, R.string.celsij, Color.WHITE, R.drawable.air);
                break;
        }
    }

    private void setParamsUi(int textTile, int textValue, int color, int icon) {
        tvTitle.setText(textTile);
        tvValue.setText(textValue);
        tvTitle.setTextColor(color);
        tvValue.setTextColor(color);
        tvParam.setTextColor(color);
        ivIconFragment.setImageResource(icon);
        // TODO: 25.03.2016 надо сделать установку значения из бд или еще окуда то
        tvParam.setText("0");
    }
}
