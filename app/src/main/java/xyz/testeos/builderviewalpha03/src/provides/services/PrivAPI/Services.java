package xyz.testeos.builderviewalpha03.src.provides.services.PrivAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.testeos.builderviewalpha03.src.model.domain.Material;
import xyz.testeos.builderviewalpha03.src.model.domain.Provider;

public interface Services {
    @GET("materials")
    Call<List<Material>> getMaterials();

    @GET("providers")
    Call<List<Provider>> getProviders();
}
