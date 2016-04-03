package com.example.aleksandr.liberator.bluetooth;

import android.os.Handler;
import android.os.Message;


/**
 * @author Stas
 * @since 27.03.2016.
 */
public class BluetoothHandler extends Handler {

    public static final int MESSAGE_READ = 100;


    public BluetoothHandler() {
        super();
    }

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
    }

    @Override
    public void handleMessage(Message message) {
        System.out.println(message.what + " 0_0");

        switch (message.what) {
            case MESSAGE_READ:
                break;
            default:
                break;
        }
    }

    @Override
    public String getMessageName(Message message) {
        return super.getMessageName(message);
    }
}
