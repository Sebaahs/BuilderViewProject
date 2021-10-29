package com.sebaahs.builderview.src.usecases.budget;

import android.annotation.SuppressLint;
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
import com.sebaahs.builderview.src.model.domain.Calculation;

public class CalculationAdapter extends RecyclerView.Adapter<CalculationAdapter.ViewHolder> {

    private List<Calculation> data;
    private LayoutInflater inflater;
    private Context context;

    public CalculationAdapter(Context context, List<Calculation> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Calculation> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public CalculationAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_element_calculation,parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull CalculationAdapter.ViewHolder holder, int position) {

            holder.material.setText(data.get(position).getName());
            holder.amount.setText(String.valueOf(Math.round(data.get(position).getAmount() * 100.0)/100.0));
            holder.cost.setText("$" + Math.round(data.get(position).getCost()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView material, amount, cost;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            material = itemView.findViewById(R.id.Budget_material_tvName);
            amount = itemView.findViewById(R.id.Budget_material_tvAmount);
            cost = itemView.findViewById(R.id.Budget_material_tvCost);
        }
    }
}
