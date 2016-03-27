package com.example.aleksandr.liberator.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.bluetooth.service.BluetoothService;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
    private final short REQUEST_PAIR_DEVICE = 2;

    private ArrayAdapter<String> mArrayAdapter;
    private AlertDialog.Builder builderSingle;
    private ArrayList<BluetoothDevice> mDevises = new ArrayList<>();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {

                case BluetoothDevice.ACTION_FOUND:

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    mDevises.add(device);
                    mArrayAdapter.notifyDataSetChanged();

                    System.out.println(device.getName() + "\n" + device.getAddress());

                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                    Log.e(getClass().getName(), "Builder show");
                    builderSingle.show();
                    System.out.println("ACTION_DISCOVERY_STARTED");
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    System.out.println("ACTION_DISCOVERY_FINISHED");
                    break;
            }
        }
    };

    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!initAdapter()) {
            //todo Toast warning
            //Device does not support Bluetooth
            finish();
            return;
        }
        mArrayAdapter = new ArrayAdapter<>(BTAdapter.this, android.R.layout.select_dialog_singlechoice);

        registerBtReceiver();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            onActivityResult(REQUEST_ENABLE_BT, RESULT_OK, null);
        }

        builderSingle = new AlertDialog.Builder(BTAdapter.this);
        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("Select One Name:-");
        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builderSingle.setAdapter(
                mArrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBluetoothAdapter.cancelDiscovery();
                        String strName = mArrayAdapter.getItem(which);
                        final BluetoothDevice mDevice = mDevises.get(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                BTAdapter.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        try {
//                                            makeDiscoverable();
                                            createBond(mDevice);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Intent mIntent = new Intent(BluetoothService.BLUETOOTH_SERVICE);
                                        mIntent.putExtra(BluetoothService.BLUETOOTH_DEVICE, mDevice);
                                        startService(mIntent);
                                        dialog.dismiss();
                                        BTAdapter.this.finish();
                                    }
                                });
                        builderInner.show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode + " ___________ " + resultCode);
        if (requestCode == REQUEST_ENABLE_BT) {
            switch (resultCode) {
                case RESULT_OK:
                    mBluetoothAdapter.startDiscovery();
//                    initPairedDevices();//get paired devices, possible use in the future
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

    public boolean createBond(BluetoothDevice btDevice)
            throws Exception
    {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }
    private void makeDiscoverable() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(discoverableIntent, REQUEST_PAIR_DEVICE);
        Log.i("Log", "Discoverable ");
    }

    private void initPairedDevices() {
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mArrayAdapter.notifyDataSetChanged();
                System.out.println(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    private boolean initAdapter() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null;
    }

    private void registerBtReceiver() {
        IntentFilter btIntent = new IntentFilter();
        btIntent.addAction(BluetoothDevice.ACTION_FOUND);
        btIntent.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        btIntent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        btIntent.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        registerReceiver(mReceiver, btIntent);
    }

    private void unregisterBtReceiver() {
        unregisterReceiver(mReceiver);
    }
}
