package com.sebaahs.builderview.src.usecases.providerList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Provider;

public class ProviderListAdapter extends RecyclerView.Adapter<ProviderListAdapter.ViewHolder> {


    //Declaracion
    private List<Provider> data;
    private LayoutInflater inflater;
    private Context context;

    //Constructor
    public ProviderListAdapter(Context context, List<Provider> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    //set del listado
    public void setData(List<Provider> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    //Creating
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Definicion del inflater para cada elemento
        View view = inflater.inflate(R.layout.list_element_provider, parent, false);

        return new ViewHolder(view);
    }

    //Binding
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.nameProvider.setText(data.get(position).getName());
        holder.adress.setText(data.get(position).getAdress());
    }

    //Cantidad de Elementos
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameProvider, adress;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            //Definicion
            nameProvider = itemView.findViewById(R.id.tvNameProvider);
            adress = itemView.findViewById(R.id.tvAdress);
        }

    }
}
