package xyz.testeos.builderviewalpha03.src.usecases.providerList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.testeos.builderviewalpha03.src.core.ApiAdapter;
import xyz.testeos.builderviewalpha03.src.model.domain.Provider;

public class ProviderListViewModel extends ViewModel {

    private MutableLiveData<List<Provider>> liveData;

    public ProviderListViewModel(){ this.liveData = new MutableLiveData<>(); }
    public MutableLiveData<List<Provider>> getLdProviderListObserver() { return liveData; }

    //API Call

    public void MakeCall(){
        Call<List<Provider>> call = ApiAdapter.getApiService().getProviders();
        call.enqueue(new Callback<List<Provider>>() {

            @Override
            public void onResponse(@NotNull Call<List<Provider>> call, @NotNull Response<List<Provider>> response) {

                if (!response.isSuccessful()){

                    return;
                }

                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Provider>> call, @NotNull Throwable t) {
                liveData.postValue(null);
                call.cancel();
            }
        });
    }
}