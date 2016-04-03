package com.example.aleksandr.liberator.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Looper;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Stas
 * @since 27.03.2016.
 */
public class ConnectThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private final BluetoothAdapter mBluetoothAdapter;
    private SocketConnection mSocketConnection;

    public ConnectThread(BluetoothDevice device, BluetoothAdapter bluetoothAdapter, Context context,
                         SocketConnection connection, UUID uuid) {

        mBluetoothAdapter = bluetoothAdapter;
        mSocketConnection = connection;

        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        mmDevice = device;
        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code

            tmp = device.createRfcommSocketToServiceRecord(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mmSocket = tmp;
    }

    public void run() {
        // Cancel discovery because it will slow down the connection
//        mBluetoothAdapter.cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            Looper.prepare();
            mmSocket.connect();
            mSocketConnection.socketConnected(mmSocket);
        } catch (IOException connectException) {
            connectException.printStackTrace();
            mSocketConnection.socketFail();
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                closeException.printStackTrace();
            }
        }

    }

    /**
     * Will cancel an in-progress connection, and close the socket
     */
    public void cancel() {
        try {
            mmSocket.close();
            mSocketConnection.socketClosed();
        } catch (IOException e) {
            mSocketConnection.socketFail();
            e.printStackTrace();
        }
    }

    public interface SocketConnection {

        void socketConnected(BluetoothSocket socket);

        void socketFail();

        void socketClosed();
    }

}
