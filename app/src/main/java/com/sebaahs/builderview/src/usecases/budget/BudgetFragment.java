package com.sebaahs.builderview.src.usecases.budget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Build;
import com.sebaahs.builderview.src.model.domain.Calculation;
import com.sebaahs.builderview.src.model.domain.Material;
import com.sebaahs.builderview.src.usecases.common.SharedViewModel;
import com.sebaahs.builderview.src.usecases.ganeratePdf.GeneratePdfActivity;
import com.sebaahs.builderview.src.usecases.ganeratePdf.TemplatePDF;

public class BudgetFragment extends Fragment {

    //--> Definicion

    private Button btnPdf;
    private TextView tvTotal;
    private RecyclerView recyclerView;
    private CalculationAdapter adapter;
    private List<Calculation> calculationList = new ArrayList<>();
    private List<Material> materialList = new ArrayList<>();
    private List<Build> completeList;
    private SharedViewModel sharedViewModel;

    //--> Empty Constructor
    public BudgetFragment() {    }

    //--> Create

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instancia del Budget con argumentos
        Bundle objArrived = getArguments();

        //Validacion de argumentos
        if (objArrived != null) {
            //Extraccion de argumentos
            materialList = objArrived.getParcelableArrayList("key");
        }
    }

    //--> Inflate

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        //Enlace
        btnPdf = view.findViewById(R.id.budget_btn_pdf);
        tvTotal = view.findViewById(R.id.Budget_tvTotalCost);
        recyclerView = view.findViewById(R.id.Budget_rvCalculation);

        //Inicializacion de listas vacias
        calculationList = new ArrayList<>();
        completeList = new ArrayList<>();

        //instancia Shared ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //LayoutManager Instancia y set
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Adapter Instancia y set
        adapter = new CalculationAdapter(getContext(), calculationList);
        recyclerView.setAdapter(adapter);

        //Observer
        sharedViewModel.getCompleteList().observe(getViewLifecycleOwner(), completeList ->{

            if (completeList != null){
                calculationList = sharedViewModel.makeCalculation(materialList, completeList);

                adapter.setData(calculationList);
                tvTotal.setText("$" + Math.round(sharedViewModel.getTotal()));

                tvTotal.setVisibility(View.VISIBLE);
                btnPdf.setVisibility(View.VISIBLE);
            }

        });

        btnPdf.setOnClickListener(v -> {
            if(!calculationList.isEmpty()){

                Intent intent = new Intent(getActivity().getApplicationContext(), GeneratePdfActivity.class)
                        .putParcelableArrayListExtra("remittance", (ArrayList<? extends Parcelable>) calculationList);

                startActivity(intent);
            }
        });

        return view;
    }
}