package com.example.aleksandr.liberator.static_params;

/**
 * Created by Aleksandr on 19.03.2016.
 */
public class StaticParams {


    public static long TIME_START = 3;    // in sec
    public static boolean SHOW_SPLASH = true;

    /**
     * Tags for fragments
     */
    public static final String TAG_SPLASH_FRAGMENT = "SplashFragment";
    public static final String TAG_PROCESS_FRAGMENT = "ProcessFragment";
    public static final String TAG_TEMPERATURE_NOW_FRAGMENT = "TemperatureWaterNowFragment";
    public static final String TAG_POWER_FRAGMENT = "PowerFragment";
    public static final String TAG_WATER_FRAGMENT = "SetTemperatureWaterFragment";
    public static final String TAG_AIR_FRAGMENT = "TemperatureAirFragment";
    public static final String TAG_END_PROCESS_FRAGMENT = "EndProcess";


    /**
     * for show next fragment
     */
    public static final String SHOW_FRAGMENT = "fragment";
    public static final int MAX_START_FRAGMENT = 4;
    public static final int MIN_START_FRAGMENT = 1;

    public static final int SHOW_TEMPERATURE_NOW = 1;
    public static final int SHOW_POWER = 2;
    public static final int SHOW_TEMPERATURE_WATER = 3;
    public static final int SHOW_TEMPERATURE_AIR = 4;


    /**
     * for Extra in Activity
     */
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_COLOR = "key_color";

    /**
     * for name data base
     */
    public static final String DEFAULT_NAME_DB = "Liberator" ;
    public static final int VERSION_DB = 1;

    public static final String TYPE_BOILER = "boiler";
    public static final String TYPE_BURN = "burn";
    public static final String TYPE_MENU = "menu";
}
