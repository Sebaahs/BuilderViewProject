package com.sebaahs.builderview.src.usecases.materialsList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.sebaahs.builderview.src.provides.services.PrivAPI.ApiAdapter;
import com.sebaahs.builderview.src.model.domain.Material;

public class MaterialsViewModel extends ViewModel {

    //Init Mutable Live Data
    private final MutableLiveData<List<Material>> liveData;


    public MaterialsViewModel(){
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Material>> getLdMaterialsListObserver() {
        return liveData;
    }

    //call with Callback

    public void MakeCall() {

        Call<List<Material>> call = ApiAdapter.getApiService().getMaterials();
        call.enqueue(new Callback<List<Material>>() {

            @Override
            public void onResponse(@NotNull Call<List<Material>> call, @NotNull Response<List<Material>> response) {

                if (!response.isSuccessful()){

                    return;
                }

                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Material>> call, @NotNull Throwable t) {
                liveData.postValue(null);
                call.cancel();
            }
        });
    }
}
