package xyz.testeos.builderviewalpha03.src.model.domain;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

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
