package com.sebaahs.builderview.src.usecases.editArea;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import com.airbnb.lottie.LottieAnimationView;
import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Build;
import com.sebaahs.builderview.src.usecases.common.SharedViewModel;

public class EditAreaFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private EditAreaAdapter editAreaAdapter;
    private RecyclerView recyclerViewEditArea;
    private List<Build> buildList;

    private LottieAnimationView btnDelete;

    private int position;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_area, container, false);


        //Instancia de la lista como nuevo ArrayList<>()
        buildList = new ArrayList<>();
        //Enlace a la vista
        recyclerViewEditArea = view.findViewById(R.id.rvEditArea);

        //seteo del layoutManager del recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEditArea.setLayoutManager(layoutManager);

        //Instancia del viewmodel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //Instancia y seteo del adapter
        editAreaAdapter = new EditAreaAdapter(getContext(), buildList);
        recyclerViewEditArea.setAdapter(editAreaAdapter);


        //evento Click para eliminar un item
        editAreaAdapter.setOnClicListener(v -> {
            position = recyclerViewEditArea.getChildAdapterPosition(v);
            showDialog();
        });

        //Observer
        sharedViewModel.getSelectedBuild().observe(getViewLifecycleOwner(), build ->{
            if (build != null){
                buildList.add(build);
                editAreaAdapter.setData(buildList);
                sharedViewModel.setCompleteList(buildList);
            }
        });

        return view;
    }

    //Borra el item seleccionado del listado
    public void DeleteItem (){

        buildList.remove(position);

        editAreaAdapter.setData(buildList);
        sharedViewModel.setCompleteList(buildList);
    }

    //--> Metodo de inicio del Configurador
    @SuppressLint("SetTextI18n")
    private void showDialog() {

        //Instancia Configurador
        final Dialog dialogBtnDelete = new Dialog(getActivity());
        dialogBtnDelete.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBtnDelete.setContentView(R.layout.layout_dialog_btn_floating);

        btnDelete = dialogBtnDelete.findViewById(R.id.dialog_btn_delete);

        //Boton eliminar OnClick
        btnDelete.setOnClickListener(v -> {
            DeleteItem();
            dialogBtnDelete.dismiss();
        });

        //Ligar a la vista
        dialogBtnDelete.show();
        dialogBtnDelete.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogBtnDelete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBtnDelete.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialogBtnDelete.getWindow().setGravity(Gravity.BOTTOM);

    }

}