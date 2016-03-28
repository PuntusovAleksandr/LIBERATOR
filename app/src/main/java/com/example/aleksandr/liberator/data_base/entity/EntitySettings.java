package com.example.aleksandr.liberator.data_base.entity;


import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Aleksandr on 24.03.2016.
 */
public class EntitySettings extends RealmObject{

    private String typeContent;
    private String title;
    private String param;
    private String values;
    private String addressDevice;
    private String countParam;
    private RealmList<ParamForSettings> massValues;
    private int id;


    public EntitySettings() {
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getAddressDevice() {
        return addressDevice;
    }

    public void setAddressDevice(String addressDevice) {
        this.addressDevice = addressDevice;
    }

    public String getCountParam() {
        return countParam;
    }

    public void setCountParam(String countParam) {
        this.countParam = countParam;
    }

    public RealmList<ParamForSettings> getMassValues() {
        return massValues;
    }

    public void setMassValues(RealmList<ParamForSettings> massValues) {
        this.massValues = massValues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
