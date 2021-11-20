package com.sebaahs.builderview.src.usecases.providerList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Provider;

import java.util.ArrayList;
import java.util.List;

public class ProviderListActivity extends AppCompatActivity {

    private ProviderListAdapter adapter;
    private ProviderListViewModel viewModel;
    private List<Provider> modelproviderslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_list);


        //Instancia de la lista como nuevo ArrayList<>()
        modelproviderslist = new ArrayList<>();

        //Enlazando recyclerview a la vista
        RecyclerView recyclerviewProviders = findViewById(R.id.rvProviders);

        //seteo del layoutManager del recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewProviders.setLayoutManager(layoutManager);

        //Instancia del viewmodel
        viewModel = new ViewModelProvider(this).get(ProviderListViewModel.class);

        //Instancia y seteo del adapter
        adapter = new ProviderListAdapter(this, modelproviderslist);
        recyclerviewProviders.setAdapter(adapter);

        //Observer
        viewModel.getLdProviderListObserver().observe((LifecycleOwner) this, new Observer<List<Provider>>() {
            @Override
            public void onChanged(List<Provider> providers) {
                if (providers != null){
                    modelproviderslist = providers;
                    adapter.setData(providers);
                }
            }
        });

        //llamada a la API
        viewModel.MakeCall();


    }
}