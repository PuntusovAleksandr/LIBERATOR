package com.example.aleksandr.liberator.data_base;

import android.content.Context;

import com.example.aleksandr.liberator.data_base.entity.EntitySettings;
import com.example.aleksandr.liberator.static_params.StaticParams;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


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
            setRealmConfig(context);
        }
    }

    /**
     * for first start
     * @return count entity if it exist
     */
    public long getAllCountSettingsValues() {
        return realm.where(EntitySettings.class).count();
    }

    /**
     * get all entity be type if it exist
     * @param typeContent
     * @return entity if it exist
     */
    public List<EntitySettings> getSettingsByContent(String typeContent) {
        RealmResults<EntitySettings> all = realm.where(EntitySettings.class)
                .equalTo("typeContent", typeContent)
                .findAll();
        all.sort("id");
        return all;
    }


    /**
     * for added entity by first start app
     * @param id
     * @param typeContent
     * @param title
     * @param param
     * @param values
     * @param addressDevice
     * @param count
     */
    public void addedEntitySetting(int id, String typeContent, String title, String param,
                                   String values, String addressDevice, String count,
                                   String minValue, String maxValue) {
        EntitySettings setting = new EntitySettings();
        setting.setId(id);
        setting.setTypeContent(typeContent);
        setting.setTitle(title);
        setting.setParam(param);
        setting.setValues(values);
        setting.setAddressDevice(addressDevice);
        setting.setCountParam(count);
        setting.setMinValue(minValue);
        setting.setMaxValue(maxValue);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(setting);
        realm.commitTransaction();
    }

    /**
     * save params from settings activity
     * @param param - set param
     * @param title - object in db
     */
    public void setParamByEntity(String param, String title) {
        realm.beginTransaction();

        EntitySettings entitySettings = realm.where(EntitySettings.class)
                .equalTo("title", title)
                .findFirst();
        entitySettings.setValues(param);

        realm.copyToRealmOrUpdate(entitySettings);
        realm.commitTransaction();
    }
}
