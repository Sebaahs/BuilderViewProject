package xyz.testeos.builderviewalpha03.src.core;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.testeos.builderviewalpha03.src.provides.services.PrivAPI.Services;

public class ApiAdapter {
    private static Services API_SERVICE;

    public static Services getApiService() {

        String baseUrl = "https://builderview.herokuapp.com/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API_SERVICE = retrofit.create(Services.class);
        }

        return API_SERVICE;
    }

}
