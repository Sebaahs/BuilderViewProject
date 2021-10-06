package xyz.testeos.builderviewalpha03.src.usecases.materialsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import xyz.testeos.builderviewalpha03.R;
import xyz.testeos.builderviewalpha03.src.model.domain.Material;

public class MaterialsAdapter extends RecyclerView.Adapter<MaterialsAdapter.ViewHolder> {


    //Declaracion
    private List<Material> data;
    private LayoutInflater inflater;


    //Constructor
    public MaterialsAdapter(Context context, List<Material> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    //set del listado
    public void setData(List<Material> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    //Creating
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Definicion del inflater para cada elemento
        View view = inflater.inflate(R.layout.list_element_material,parent,false);
        return new ViewHolder(view);
    }

    //Binding
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.material.setText(data.get(position).getMaterial());
        holder.unit.setText(data.get(position).getUnit());
        holder.cost.setText(String.valueOf(data.get(position).getAverage()));
    }

    //Cantidad de Elementos
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cost,material,unit;

        //Definicion
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            material = itemView.findViewById(R.id.tvNameMaterial);
            unit = itemView.findViewById(R.id.tvUnit);
            cost = itemView.findViewById(R.id.tvCost);
        }
    }
}
