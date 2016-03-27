package com.example.aleksandr.liberator.data_base.entity;

import io.realm.RealmObject;

/**
 * Created by Aleksandr on 28.03.2016.
 */
public class ParamForSettings extends RealmObject {

    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public ParamForSettings() {
    }
}
