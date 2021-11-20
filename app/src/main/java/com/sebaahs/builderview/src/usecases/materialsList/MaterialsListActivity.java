package com.sebaahs.builderview.src.usecases.materialsList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Material;
import com.sebaahs.builderview.src.usecases.materialsList.MaterialsAdapter;
import com.sebaahs.builderview.src.usecases.materialsList.MaterialsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MaterialsListActivity extends AppCompatActivity {

    private MaterialsAdapter adapter;
    private MaterialsViewModel viewModel;
    private List<Material> modelmaterialslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials_list);

        //Instancia de la lista como nuevo ArrayList<>()
        modelmaterialslist = new ArrayList<>();

        //Enlazando recyclerview a la vista
        RecyclerView recyclerviewMaterials = findViewById(R.id.rvMaterials);

        //seteo del layoutManager del recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewMaterials.setLayoutManager(layoutManager);

        //Instancia del viewmodel
        viewModel = new ViewModelProvider(this).get(MaterialsViewModel.class);

        //Instancia y seteo del adapter
        adapter = new MaterialsAdapter(this, modelmaterialslist);
        recyclerviewMaterials.setAdapter(adapter);

        //Observer
        viewModel.getLdMaterialsListObserver().observe((LifecycleOwner) this, materials -> {
            if (materials != null){
                modelmaterialslist = materials;
                adapter.setData(materials);
            }
        });

        //llamada a la API
        viewModel.MakeCall();

    }
}