package com.example.aleksandr.liberator.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;

import java.util.Set;

/**
 * @author Stas
 * @since 27.03.2016.
 * <p/>
 * Used to create and start bluetooth adapter with user input confirmation
 * <p/>
 * !!! launch activity only with {@link android.content.Intent#FLAG_ACTIVITY_CLEAR_TOP} | {@link android.content.Intent#FLAG_ACTIVITY_SINGLE_TOP}
 */
public class BTAdapter extends Activity {

    private BluetoothAdapter mBluetoothAdapter;
    private final short REQUEST_ENABLE_BT = 1;
    private ArrayAdapter<String> mArrayAdapter;
    public static String UUID;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (initAdapter()) {
            //todo Toast warning
            //Device does not support Bluetooth
            return;
        }
        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        UUID = tManager.getDeviceId();
        registerBtReceiver();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            switch (resultCode) {
                case RESULT_OK:
                    break;
                case RESULT_CANCELED:
                    //todo ask user to re-enable bt
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBtReceiver();
    }

    private boolean initAdapter() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null;
    }

    private void registerBtReceiver() {
        IntentFilter btIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, btIntent);
    }

    private void unregisterBtReceiver() {
        unregisterReceiver(mReceiver);
    }
}
