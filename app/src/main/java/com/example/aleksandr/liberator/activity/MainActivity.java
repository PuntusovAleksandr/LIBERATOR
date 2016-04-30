package com.example.aleksandr.liberator.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.example.aleksandr.liberator.bluetooth.service.BluetoothService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends Activity {

    protected static final String TAG = "bluetoothdemo";
    private int REQUEST_ENABLE_BT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BluetoothAdapter BT = BluetoothAdapter.getDefaultAdapter();
        if (BT == null) {
            Toast.makeText(MainActivity.this, R.string.devise_not_have_bluetoth, Toast.LENGTH_SHORT).show();
            return;
        }
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
}
