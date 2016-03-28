package com.example.aleksandr.liberator.utils;

import android.content.SharedPreferences;

/**
 * Created by Aleksandr on 27.03.2016.
 */
public class Settings {

    /**
     * The constant FILE_NAME.
     */
// Settings xml file name
    public static final String FILE_NAME = "settings";
    private static final String KEY_FIRST_START = "key_first_start";
    private static final boolean DEF_FIRST_START = false;



    public static boolean isFirstStart(SharedPreferences preferences) {
        return preferences.getBoolean(KEY_FIRST_START, DEF_FIRST_START);
    }


    public static void setFirstStart(boolean ip, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_FIRST_START, ip);
        editor.commit();
    }

}
