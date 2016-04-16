package com.example.aleksandr.liberator.utils;

import android.os.Handler;
import android.view.View;

/**
 * Created by Aleksandr on 20.03.2016.
 */
public class Utils {

    /**
     * for disable button by one pressed
     * @param v
     */
    public static void disableButton(final View v) {
        v.setClickable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setClickable(true);
            }
        }, 500);
    }
}
