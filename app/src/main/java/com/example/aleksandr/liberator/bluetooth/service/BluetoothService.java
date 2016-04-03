package com.example.aleksandr.liberator.bluetooth.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.aleksandr.liberator.IBluetoothService;
import com.example.aleksandr.liberator.bluetooth.AcceptConnection;
import com.example.aleksandr.liberator.bluetooth.BluetoothHandler;
import com.example.aleksandr.liberator.bluetooth.ConnectThread;
import com.example.aleksandr.liberator.bluetooth.ConnectedThread;

import java.util.UUID;


/**
 * @author Stas
 * @since 27.03.2016.
 */
public class BluetoothService extends Service implements ConnectThread.SocketConnection {

    private BluetoothAdapter mBluetoothAdapter;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private Handler mHandler;
    public static final String BLUETOOTH_DEVICE = "bluetooth_device";
    public static final String BLUETOOTH_SERVICE = "com.example.aleksandr.liberator.bluetooth.service.BluetoothService";
    private UUID applicationUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ParcelUuid[] mParcelUuids;

    private final IBluetoothService.Stub mStub = new IBluetoothService.Stub() {
        @Override
        public void socketReady(int socket) throws RemoteException {

        }
    };


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("SERVICE STARTED");

        BluetoothDevice mDevice;
        if (intent.getExtras() != null) {
            try {
                initAdapter();
                mDevice = intent.getParcelableExtra(BLUETOOTH_DEVICE);
                mParcelUuids = mDevice.getUuids();
                if (mParcelUuids != null) {
                    applicationUUID = mParcelUuids[0].getUuid();
                }
                AcceptConnection mAcceptConnection = new AcceptConnection(mBluetoothAdapter, applicationUUID);
                mAcceptConnection.start();
                mConnectThread = new ConnectThread(mDevice,
                        mBluetoothAdapter, this, this, applicationUUID);
                mConnectThread.start();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    private boolean initAdapter() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null;
    }

    @Override
    public void socketConnected(BluetoothSocket socket) {

        AcceptConnection mAcceptConnection = new AcceptConnection(mBluetoothAdapter, mParcelUuids[0].getUuid());
        mAcceptConnection.start();

        try {

            mHandler = new BluetoothHandler();

            mConnectedThread = new ConnectedThread(socket, mHandler);

            mConnectedThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void socketFail() {

    }

    @Override
    public void socketClosed() {

    }
}
