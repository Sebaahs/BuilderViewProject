package com.sebaahs.builderview.src.usecases.materialsList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Material;


public class MaterialsFragment extends Fragment {

    private MaterialsAdapter adapter;
    private MaterialsViewModel viewModel;
    private List<Material> modelmaterialslist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_materials, container, false);

        //Instancia de la lista como nuevo ArrayList<>()
        modelmaterialslist = new ArrayList<>();

        //Enlazando recyclerview a la vista
        RecyclerView recyclerviewMaterials = view.findViewById(R.id.rvMaterials);

        //seteo del layoutManager del recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerviewMaterials.setLayoutManager(layoutManager);

        //Instancia del viewmodel
        viewModel = new ViewModelProvider(this).get(MaterialsViewModel.class);

        //Instancia y seteo del adapter
        adapter = new MaterialsAdapter(getContext(), modelmaterialslist);
        recyclerviewMaterials.setAdapter(adapter);

        //Observer
        viewModel.getLdMaterialsListObserver().observe((LifecycleOwner) getContext(), materials -> {
            if (materials != null){
                modelmaterialslist = materials;
                adapter.setData(materials);
            }
        });

        //llamada a la API
        viewModel.MakeCall();


        return view;
    }


}