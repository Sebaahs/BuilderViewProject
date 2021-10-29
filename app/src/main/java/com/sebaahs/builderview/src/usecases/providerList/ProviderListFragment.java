package com.sebaahs.builderview.src.usecases.providerList;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Provider;

public class ProviderListFragment extends Fragment {

    private ProviderListAdapter adapter;
    private ProviderListViewModel viewModel;
    private List<Provider> modelproviderslist;

    public static ProviderListFragment newInstance() {
        return new ProviderListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.provider_list_fragment, container, false);

        //Instancia de la lista como nuevo ArrayList<>()
        modelproviderslist = new ArrayList<>();

        //Enlazando recyclerview a la vista
        RecyclerView recyclerviewProviders = view.findViewById(R.id.rvProviders);

        //seteo del layoutManager del recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerviewProviders.setLayoutManager(layoutManager);

        //Instancia del viewmodel
        viewModel = new ViewModelProvider(this).get(ProviderListViewModel.class);

        //Instancia y seteo del adapter
        adapter = new ProviderListAdapter(getContext(), modelproviderslist);
        recyclerviewProviders.setAdapter(adapter);

        //Observer
        viewModel.getLdProviderListObserver().observe((LifecycleOwner) getContext(), new Observer<List<Provider>>() {
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



        return view;
    }
}