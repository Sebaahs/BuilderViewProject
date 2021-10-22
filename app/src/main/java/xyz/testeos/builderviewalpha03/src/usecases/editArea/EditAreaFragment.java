package xyz.testeos.builderviewalpha03.src.usecases.editArea;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import xyz.testeos.builderviewalpha03.R;
import xyz.testeos.builderviewalpha03.src.model.domain.Build;
import xyz.testeos.builderviewalpha03.src.usecases.common.SharedViewModel;
import xyz.testeos.builderviewalpha03.src.usecases.materialsList.MaterialsAdapter;

public class EditAreaFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private EditAreaAdapter editAreaAdapter;
    private RecyclerView recyclerViewEditArea;
    private List<Build> buildList;
    private int position;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_area_fragment, container, false);


        //Instancia de la lista como nuevo ArrayList<>()
        buildList = new ArrayList<>();
        //Enlazando recyclerview a la vista
        recyclerViewEditArea = view.findViewById(R.id.rvEditArea);

        //seteo del layoutManager del recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEditArea.setLayoutManager(layoutManager);

        //Instancia del viewmodel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //Instancia y seteo del adapter
        editAreaAdapter = new EditAreaAdapter(getContext(), buildList);
        recyclerViewEditArea.setAdapter(editAreaAdapter);


        //set Click
        editAreaAdapter.setOnClickListener(v -> {
            position = recyclerViewEditArea.getChildAdapterPosition(v);
            DeleteItem();
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

    public void DeleteItem (){

        //BORRAR ITEM SELECCIONADO DEL LISTADO

    }


}