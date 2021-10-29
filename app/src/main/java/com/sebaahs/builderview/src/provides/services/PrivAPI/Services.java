package com.sebaahs.builderview.src.provides.services.PrivAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import com.sebaahs.builderview.src.model.domain.Material;
import com.sebaahs.builderview.src.model.domain.Provider;

public interface Services {
    @GET("materials")
    Call<List<Material>> getMaterials();

    @GET("providers")
    Call<List<Provider>> getProviders();
}
