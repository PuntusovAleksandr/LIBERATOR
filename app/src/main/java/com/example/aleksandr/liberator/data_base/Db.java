package com.example.aleksandr.liberator.data_base;

import android.content.Context;

import com.example.aleksandr.liberator.data_base.entity.EntitySettings;
import com.example.aleksandr.liberator.data_base.entity.ParamForSettings;
import com.example.aleksandr.liberator.static_params.StaticParams;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;


/**
 * Created by Aleksandr on 27.03.2016.
 */
public class Db {

    private Context context;
    private Realm realm;

    private static Db db;

    public static Db getInstance(Context context) {
        if (db == null) {
            db = new Db(context);
        }
        return db;
    }

    private Db(Context context) {
        this.context = context;
        if (realm == null) {
            setRealmConfig(context);
        }
    }

    private void setRealmConfig(Context context) {
        realm = Realm.getInstance(
                new RealmConfiguration.Builder(context)
                        .name(StaticParams.DEFAULT_NAME_DB)
                        .schemaVersion(StaticParams.VERSION_DB)
                        .build()
        );
    }

    /**
     * for creating (or change) data base, need reopen Realm
     */
    public static void stopRealm(Context context) {
        if (db != null) {
            db.closeRealm(context);
        }
    }

    private void closeRealm(Context context) {
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }

    public long getAllCountSettingsValues() {
        return realm.where(EntitySettings.class).count();
    }


    public void addedEntitySetting(int id, String typeContent, String title, String param,
                                          String values, String addressDevice, String count,
                                          RealmList<ParamForSettings> massValues) {
        EntitySettings setting = new EntitySettings();
        setting.setId(id);
        setting.setTypeContent(typeContent);
        setting.setTitle(title);
        setting.setParam(param);
        setting.setValues(values);
        setting.setAddressDevice(addressDevice);
        setting.setCountParam(count);
        setting.setMassValues(massValues);

        realm.beginTransaction();
        realm.copyToRealm(setting);
        realm.commitTransaction();
    }


}
