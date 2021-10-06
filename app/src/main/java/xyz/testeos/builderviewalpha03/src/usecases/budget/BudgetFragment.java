package xyz.testeos.builderviewalpha03.src.usecases.budget;

import android.annotation.SuppressLint;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.PreciseDataConnectionState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import xyz.testeos.builderviewalpha03.R;
import xyz.testeos.builderviewalpha03.src.model.domain.Build;
import xyz.testeos.builderviewalpha03.src.model.domain.Calculation;
import xyz.testeos.builderviewalpha03.src.model.domain.Material;
import xyz.testeos.builderviewalpha03.src.usecases.common.SharedViewModel;

public class BudgetFragment extends Fragment {

    //--> Definicion

    private TextView tvTotal;
    private RecyclerView recyclerView;
    private CalculationAdapter adapter;
    private List<Calculation> calculationList;
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

            }

        });

        return view;
    }
}