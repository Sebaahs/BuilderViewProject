package com.sebaahs.builderview.src.model.domain;

import com.google.gson.annotations.SerializedName;

public class Provider {

    // model class para los Proveedores

    @SerializedName("name") private String name;
    @SerializedName("adress") private String adress;
    @SerializedName("contactNum")private String cel;

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getCel() {
        return cel;
    }
}
