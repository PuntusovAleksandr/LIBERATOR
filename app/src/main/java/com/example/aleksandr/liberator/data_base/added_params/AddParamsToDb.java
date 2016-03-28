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
                context.getString(R.string.celsij), StaticParams.AUTO_START_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));
        Db.getInstance(context).addedEntitySetting(
                2, StaticParams.TYPE_MENU, context.getString(R.string.mode),
                context.getString(R.string.celsij), StaticParams.MODE_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));
        Db.getInstance(context).addedEntitySetting(
                3, StaticParams.TYPE_MENU, context.getString(R.string.fuel_loading),
                context.getString(R.string.celsij), StaticParams.LOAD_FUEL_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));
        Db.getInstance(context).addedEntitySetting(
                4, StaticParams.TYPE_MENU, context.getString(R.string.system_burn),
                context.getString(R.string.celsij), StaticParams.SYSTEM_BURNING_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));
        Db.getInstance(context).addedEntitySetting(
                5, StaticParams.TYPE_MENU, context.getString(R.string.control_relay),
                context.getString(R.string.celsij), StaticParams.RELAY_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                6, StaticParams.TYPE_MENU, context.getString(R.string.settings_module),
                context.getString(R.string.celsij), StaticParams.SETTINGS_MODEL_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));

    }

    private void addSetBurn() {

        Db.getInstance(context).addedEntitySetting(
                1, StaticParams.TYPE_BURN, "Время стартовой загрузки топлива",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                2, StaticParams.TYPE_BURN, "Время нагрева тэна",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                3, StaticParams.TYPE_BURN, "Время розжига топлива",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                4, StaticParams.TYPE_BURN, "Период вращения щнека топливного бункера",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                5, StaticParams.TYPE_BURN, "Пауза на выкл. шнека горелки после вращения шнека бункера",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                6, StaticParams.TYPE_BURN, "Цикл очистки на количество порций топлива рабочего цикла",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                7, StaticParams.TYPE_BURN, "Нижний аварийный порог. Порог перехода при розжиге",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                8, StaticParams.TYPE_BURN, "Скорость вращения вентилятора при розжиге в %",
                context.getString(R.string.percent), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                9, StaticParams.TYPE_BURN, "Количество подаваемого топлива в основном цикле работы",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                10, StaticParams.TYPE_BURN, "Количество подаваемого топлива в цикле Фитиль",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                11, StaticParams.TYPE_BURN, "Воздух рабочего цикла",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                12, StaticParams.TYPE_BURN, "Воздух цикла Фитиль",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                13, StaticParams.TYPE_BURN, "Выбор пелеты",
                context.getString(R.string.celsij), StaticParams.CHECK_PELLET_PARAMS[0],
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                14, StaticParams.TYPE_BURN, "Воздух рабочего цикла вторичного воздуха",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                15, StaticParams.TYPE_BURN, "Воздух цикла Фитиль вторичного воздуха",
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

    }

    private void addSetBoiler() {
        Db.getInstance(context).addedEntitySetting(
                1, StaticParams.TYPE_BOILER, context.getString(R.string.temperature_boiler_for_disable),
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                2, StaticParams.TYPE_BOILER, context.getString(R.string.delta_boiler),
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                3, StaticParams.TYPE_BOILER, context.getString(R.string.temperature_smoke),
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                4, StaticParams.TYPE_BOILER, context.getString(R.string.temperature_enable_pump),
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));

        Db.getInstance(context).addedEntitySetting(
                5, StaticParams.TYPE_BOILER, context.getString(R.string.cicle_clear_boiler),
                context.getString(R.string.celsij), context.getString(R.string.default_value),
                context.getString(R.string.default_value), context.getString(R.string.default_value));
    }

}
