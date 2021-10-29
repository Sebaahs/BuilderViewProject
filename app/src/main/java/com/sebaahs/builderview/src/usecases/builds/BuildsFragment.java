package com.sebaahs.builderview.src.usecases.builds;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Build;
import com.sebaahs.builderview.src.usecases.common.SharedViewModel;

public class BuildsFragment extends Fragment {


    //--> Definicion

    //View
    private Spinner type;
    private EditText ext;
    private Button btnSubmit;
    private TextView tvUnit, tvName, tvType;
    //Adapter
    private ArrayAdapter<CharSequence> arrayAdapter;
    private BuildsAdapter buildsAdapter;
    //RecyclerView
    private RecyclerView recyclerViewBuilds;
    //Aux
    private Build auxBuild;
    private List<Build> buildList;
    private int position;
    //ViewModel
    private SharedViewModel sharedViewModel;

    //--> Inflate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_builds, container, false);

        //definicion del buildlist
        buildList = new ArrayList<>();

        //Definicion RecyclerView
        recyclerViewBuilds = view.findViewById(R.id.rvBuilds);
        recyclerViewBuilds.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //Set Adapter
        buildsAdapter = new BuildsAdapter(buildsProvider());

        //Construccion OnClick
        buildsAdapter.setOnClickListener(v -> {
            position = recyclerViewBuilds.getChildAdapterPosition(v);
            showDialog();
        });


        //Set RecyclerView Adapter
        recyclerViewBuilds.setAdapter(buildsAdapter);

        return view;
    }

    //--> Metodo de inicio del Configurador
    @SuppressLint("SetTextI18n")
    private void showDialog() {

        //Instancia Configurador
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_bottom_sheet);

        //Definicion general
        ext = dialog.findViewById(R.id.etExtSelected);
        btnSubmit = dialog.findViewById(R.id.btnSubmit);
        tvUnit = dialog.findViewById(R.id.tvUnitSelected);
        tvName = dialog.findViewById(R.id.tvNameSelected);
        tvType = dialog.findViewById(R.id.tvTypeSelected);
        //Definicion del Spinner
        type = dialog.findViewById(R.id.spType);

        //Set texto general
        tvName.setText(buildList.get(position).getNameBuild());
        tvUnit.setText(buildList.get(position).getUnit());
        tvType.setText("Tipo:");


        //Rellena con el contenido correcto
        setContent(position);


        //Boton agregar OnClick
        btnSubmit.setOnClickListener(v -> {

            if (ext.getText() == null){
                Toast.makeText(getActivity(),"Complete los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            dialog.dismiss();

            auxBuild = new Build(buildList.get(position).getNameBuild(),
                    buildList.get(position).getImgBuild(),
                    buildList.get(position).getUnit(),
                    type.getSelectedItemPosition(), Float.parseFloat(ext.getText().toString()));

            sharedViewModel.setSelectedBuild(auxBuild);

            hideDialogInterface();

            Toast.makeText(getActivity(),"Elemento agregado a la lista", Toast.LENGTH_SHORT).show();
        });

        //Ligar a la vista
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }


    //--> define el tipo de Configurador
    public void setContent(int position){

        if(buildList.get(position).getNameBuild().equals("Pared")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.wall_array, R.layout.layout_item_spinner);
        }
        if(buildList.get(position).getNameBuild().equals("Encadenado / Viga")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.structure_array, R.layout.layout_item_spinner);
        }
        if(buildList.get(position).getNameBuild().equals("Columna")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.structure_array, R.layout.layout_item_spinner);
        }
        if(buildList.get(position).getNameBuild().equals("Zapata")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.structure_array, R.layout.layout_item_spinner);
        }
        if(buildList.get(position).getNameBuild().equals("Piso")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.flat_array, R.layout.layout_item_spinner);
        }
        if(buildList.get(position).getNameBuild().equals("Techo de teja")){

            tvUnit.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);
        }
        if(buildList.get(position).getNameBuild().equals("Techo de chapa")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.sheet_array, R.layout.layout_item_spinner);
        }
        if(buildList.get(position).getNameBuild().equals("Revoque")){

            tvType.setVisibility(View.VISIBLE);
            tvUnit.setVisibility(View.VISIBLE);
            type.setVisibility(View.VISIBLE);
            ext.setVisibility(View.VISIBLE);

            arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.plaster_array, R.layout.layout_item_spinner);
        }


        type.setAdapter(arrayAdapter);
    }

    //--> Lista definida de construcciones
    public List<Build> buildsProvider(){
        buildList = new ArrayList<>();

        buildList.add(new Build("Pared", R.drawable.img_pared, "m²"));
        buildList.add(new Build("Encadenado / Viga", R.drawable.img_encadenado, "m"));
        buildList.add(new Build("Columna", R.drawable.img_default, "m"));
        buildList.add(new Build("Zapata", R.drawable.img_default, "Unidad"));
        buildList.add(new Build("Piso", R.drawable.img_default, "m²"));
        buildList.add(new Build("Techo de teja", R.drawable.img_default, "m²"));
        buildList.add(new Build("Techo de chapa", R.drawable.img_default, "m²"));
        buildList.add(new Build("Revoque", R.drawable.img_default, "m²"));

        return buildList;
    }

    //--> Oculta la interface del Configurador
    private void hideDialogInterface() {
        tvType.setVisibility(View.GONE);
        tvUnit.setVisibility(View.GONE);
        type.setVisibility(View.GONE);
        ext.setVisibility(View.GONE);
    }

}