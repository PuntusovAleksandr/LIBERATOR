package com.example.aleksandr.liberator.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.bluetooth.BTAdapter;
import com.example.aleksandr.liberator.bluetooth.service.BluetoothService;
import com.example.aleksandr.liberator.data_base.Db;
import com.example.aleksandr.liberator.data_base.added_params.AddParamsToDb;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Settings;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends Activity {

    protected static final String TAG = "bluetoothdemo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDataBase();

        BluetoothAdapter BT = BluetoothAdapter.getDefaultAdapter();
        if (BT == null) {
            Toast.makeText(MainActivity.this, R.string.devise_not_have_bluetoth, Toast.LENGTH_SHORT).show();
            return;
        }


//        Intent mIntent = new Intent(this, BTAdapter.class);
//        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        startActivity(mIntent);


        if (!BT.isEnabled()) {
            // TODO: 30.04.2016 включить блютуз, найти устройство и занести данные в бд
            goToStartActivity();
        } else {
            // TODO: 30.04.2016 надо сделать обработку данных с блютуза и занести все это в бд
            goToStartActivity();
        }
    }

    private void goToStartActivity() {
        final Intent mIntent = new Intent(MainActivity.this, StartActivity.class);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(mIntent);
                MainActivity.this.finish();
            }
        }, 3000);
    }

    /**
     * Added data in Db if it not exist
     */
    private void createDataBase() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(Settings.FILE_NAME, Context.MODE_PRIVATE);
        if (Db.getInstance(MainActivity.this).getAllCountSettingsValues() <= 0 &&
                !Settings.isFirstStart(sharedPreferences)) {
            new AddParamsToDb(MainActivity.this);
        }
    }
}
