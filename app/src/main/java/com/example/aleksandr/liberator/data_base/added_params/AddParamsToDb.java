package com.example.aleksandr.liberator.data_base.added_params;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.data_base.Db;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Settings;

/**
 * Created by Aleksandr on 28.03.2016.
 */
public class AddParamsToDb {

    private Context context;

    public AddParamsToDb(Context context) {
        this.context = context;
        addSetBoiler();
        addSetBurn();
        addSetMenu();
        SharedPreferences sharedPreferences
                = context.getSharedPreferences(Settings.FILE_NAME, Context.MODE_PRIVATE);
        Settings.setFirstStart(true, sharedPreferences);
    }

    private void addSetMenu() {

        Db.getInstance(context).addedEntitySetting(
                1, StaticParams.TYPE_MENU, context.getString(R.string.auto_start),
                "", StaticParams.AUTO_START_PARAMS[0],
                "22", StaticParams.AUTO_START_PARAMS.length + "",
                StaticParams.AUTO_START_PARAMS[0], StaticParams.AUTO_START_PARAMS[StaticParams.AUTO_START_PARAMS.length - 1]
        );
        Db.getInstance(context).addedEntitySetting(
                2, StaticParams.TYPE_MENU, context.getString(R.string.mode),
                "", StaticParams.MODE_PARAMS[0],
                "120х02", StaticParams.MODE_PARAMS.length + "",
                StaticParams.MODE_PARAMS[0], StaticParams.MODE_PARAMS[StaticParams.MODE_PARAMS.length - 1]
        );
        Db.getInstance(context).addedEntitySetting(
                3, StaticParams.TYPE_MENU, context.getString(R.string.fuel_loading),
                "", StaticParams.LOAD_FUEL_PARAMS[0],
                "120х04", StaticParams.LOAD_FUEL_PARAMS.length + "",
                StaticParams.LOAD_FUEL_PARAMS[0], StaticParams.LOAD_FUEL_PARAMS[StaticParams.LOAD_FUEL_PARAMS.length - 1]
        );
        Db.getInstance(context).addedEntitySetting(
                4, StaticParams.TYPE_MENU, context.getString(R.string.system_burn),
                "", StaticParams.SYSTEM_BURNING_PARAMS[0],
                "120х08", StaticParams.SYSTEM_BURNING_PARAMS.length + "",
                StaticParams.SYSTEM_BURNING_PARAMS[0], StaticParams.SYSTEM_BURNING_PARAMS[StaticParams.SYSTEM_BURNING_PARAMS.length - 1]
        );
        Db.getInstance(context).addedEntitySetting(
                5, StaticParams.TYPE_MENU, context.getString(R.string.enable_out_termosstat),
                "", StaticParams.OUT_TERMOSTAAT[0],
                "120х10", StaticParams.OUT_TERMOSTAAT.length + "",
                StaticParams.OUT_TERMOSTAAT[0], StaticParams.OUT_TERMOSTAAT[StaticParams.OUT_TERMOSTAAT.length - 1]
        );
        Db.getInstance(context).addedEntitySetting(
                6, StaticParams.TYPE_MENU, context.getString(R.string.settings_module),
                context.getString(R.string.power_value), StaticParams.SETTINGS_MODEL_PARAMS[0],
                "13", StaticParams.SETTINGS_MODEL_PARAMS.length + "",
                StaticParams.SETTINGS_MODEL_PARAMS[0], StaticParams.SETTINGS_MODEL_PARAMS[StaticParams.SETTINGS_MODEL_PARAMS.length - 1]
        );

        Db.getInstance(context).addedEntitySetting(
                7, StaticParams.TYPE_MENU, context.getString(R.string.control_relay),
                "", StaticParams.RELAY_PARAMS[0],
                "14", StaticParams.RELAY_PARAMS.length + "",
                StaticParams.RELAY_PARAMS[0], StaticParams.RELAY_PARAMS[StaticParams.RELAY_PARAMS.length - 1]
        );

    }

    private void addSetBurn() {

        Db.getInstance(context).addedEntitySetting(
                1, StaticParams.TYPE_BURN, context.getString(R.string.time_start_loading),
                context.getString(R.string.sec), context.getString(R.string.default_value),
                "01", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                2, StaticParams.TYPE_BURN, context.getString(R.string.time_burning_ten),
                context.getString(R.string.sec), context.getString(R.string.default_value),
                "02", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                3, StaticParams.TYPE_BURN, context.getString(R.string.time_burning_fuel),
                context.getString(R.string.sec), context.getString(R.string.default_value),
                "03", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                4, StaticParams.TYPE_BURN, context.getString(R.string.period_resucling_sneck),
                context.getString(R.string.sec), context.getString(R.string.default_value),
                "04", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                5, StaticParams.TYPE_BURN, context.getString(R.string.pause_on_disable),
                context.getString(R.string.cycle), context.getString(R.string.default_value),
                "05", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                6, StaticParams.TYPE_BURN, context.getString(R.string.recycling_clear_count),
                context.getString(R.string.percent), context.getString(R.string.default_value),
                "06", context.getString(R.string.default_value),
                "1", "99"
        );

        Db.getInstance(context).addedEntitySetting(
                7, StaticParams.TYPE_BURN, context.getString(R.string.speed_recycler_fun),
                context.getString(R.string.percent), context.getString(R.string.default_value),
                "07", context.getString(R.string.default_value),
                "1", "99"
        );

        Db.getInstance(context).addedEntitySetting(
                8, StaticParams.TYPE_BURN, context.getString(R.string.count_fuel_general_cycler),
                "", context.getString(R.string.default_value),
                "15", context.getString(R.string.default_value),
                "1", "20"
        );

        Db.getInstance(context).addedEntitySetting(
                9, StaticParams.TYPE_BURN, context.getString(R.string.pausa_in_work),
                context.getString(R.string.sec), context.getString(R.string.default_value),
                "16", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                10, StaticParams.TYPE_BURN, context.getString(R.string.сщгте_агуд_шт_ашшешд),
                "", context.getString(R.string.default_value),
                "17", context.getString(R.string.default_value),
                "1", "20"
        );

        Db.getInstance(context).addedEntitySetting(
                11, StaticParams.TYPE_BURN, context.getString(R.string.pausa_betwen_load),
                context.getString(R.string.sec), context.getString(R.string.default_value),
                "18", context.getString(R.string.default_value),
                "1", "400"
        );

        Db.getInstance(context).addedEntitySetting(
                12, StaticParams.TYPE_BURN, context.getString(R.string.air_recycling_burning),
                context.getString(R.string.percent), context.getString(R.string.default_value),
                "19", context.getString(R.string.default_value),
                "1", "99"
        );

        Db.getInstance(context).addedEntitySetting(
                13, StaticParams.TYPE_BURN, context.getString(R.string.air_in_fitil),
                context.getString(R.string.percent), context.getString(R.string.default_value),
                "20", context.getString(R.string.default_value),
                "1", "99"
        );

        Db.getInstance(context).addedEntitySetting(
                14, StaticParams.TYPE_BURN, context.getString(R.string.select_pellet),
                "", StaticParams.SELECT_PELET[0],
                "21", StaticParams.SELECT_PELET.length + "",
                StaticParams.SELECT_PELET[0], StaticParams.SELECT_PELET[StaticParams.SELECT_PELET.length - 1]
        );

    }

    private void addSetBoiler() {
        Db.getInstance(context).addedEntitySetting(
                1, StaticParams.TYPE_BOILER, context.getString(R.string.temperature_boiler_for_disable),
                context.getString(R.string.celsij), "5",
                "08", context.getString(R.string.default_value),
                "5", "90"
        );

        Db.getInstance(context).addedEntitySetting(
                2, StaticParams.TYPE_BOILER, context.getString(R.string.gisteresis),
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                "09", context.getString(R.string.default_value),
                "1", "10"
        );

        Db.getInstance(context).addedEntitySetting(
                3, StaticParams.TYPE_BOILER, context.getString(R.string.temperature_enable_pump),
                context.getString(R.string.celsij), "5",
                "10", context.getString(R.string.default_value),
                "5", "90"
        );

        Db.getInstance(context).addedEntitySetting(
                4, StaticParams.TYPE_BOILER, context.getString(R.string.cycle_clear_boiler),
                context.getString(R.string.cycle), context.getString(R.string.default_value),
                "12", context.getString(R.string.default_value),
                "1", "400"
        );
    }

}
