package com.example.aleksandr.liberator.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.adapters.RecyclerViewAdapter;
import com.example.aleksandr.liberator.data_base.Db;
import com.example.aleksandr.liberator.data_base.entity.EntitySettings;
import com.example.aleksandr.liberator.static_params.StaticParams;

import java.util.List;

public class SettingsSetParamsActivity extends AppCompatActivity {

    private int resTitle, resColorCategory;
    private List<EntitySettings> mListEvents;
    private String typeContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_set_params);

        resTitle = getIntent().getIntExtra(StaticParams.KEY_TITLE, R.string.settings_app);
        resColorCategory = getIntent().getIntExtra(StaticParams.KEY_COLOR, R.color.black);

        initToolBar();
        setTypeContent();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_params);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mListEvents = Db.getInstance(SettingsSetParamsActivity.this).getSettingsByContent(typeContent);
        RecyclerViewAdapter mRecyclerViewAdapter
                = new RecyclerViewAdapter(mListEvents, SettingsSetParamsActivity.this);

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        ImageView ivBack = (ImageView) findViewById(R.id.iv_back_set_params);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }

    private void setTypeContent() {
        switch (resTitle) {
            case R.string.params_boiler:
                typeContent = StaticParams.TYPE_BOILER;
                break;
            case R.string.params_burner:
                typeContent = StaticParams.TYPE_BURN;
                break;
            case R.string.service:
                typeContent = StaticParams.TYPE_MENU;
                break;
        }
    }

    private void initToolBar() {
        TextView textCategory = (TextView) findViewById(R.id.tv_settings_category);
        textCategory.setText(resTitle);
        textCategory.setTextColor(Color.WHITE);
        textCategory.setBackgroundColor(getResources().getColor(resColorCategory));

        TextView textSettings = (TextView) findViewById(R.id.tv_category_settings);
        textSettings.setTextColor(Color.WHITE);
        textSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

}
