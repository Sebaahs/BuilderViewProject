package com.sebaahs.builderview.src.usecases.editArea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.model.domain.Build;

public class EditAreaAdapter extends RecyclerView.Adapter<EditAreaAdapter.ViewHolder>
                             implements View.OnClickListener {
    //Definicion
    private List<Build> data;
    private LayoutInflater inflater;
    private View.OnClickListener listener;

    public EditAreaAdapter(Context context, List<Build> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Build> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    //Creating
    @NonNull
    @NotNull
    @Override
    public EditAreaAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        ////Definicion del inflater para cada elemento
        View view = inflater.inflate(R.layout.list_element_edit_area, parent , false);

        view.setOnClickListener(this);

        return new EditAreaAdapter.ViewHolder(view);
    }

    //Binding
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull EditAreaAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImgBuild());
        holder.name.setText(data.get(position).getNameBuild());
        holder.ext.setText(data.get(position).getParamExt());

        if (data.get(position).getNameBuild().equals("Pared")){
            if (data.get(position).getParamType().equals("0")) {
                holder.type.setText("Ladrillo hueco 8cm");
            }
            if (data.get(position).getParamType().equals("1")) {
                holder.type.setText("Ladrillo hueco 12cm");
            }
            if (data.get(position).getParamType().equals("2")) {
                holder.type.setText("Ladrillo 15cm");
            }
            if (data.get(position).getParamType().equals("3")) {
                holder.type.setText("Ladrillo hueco 18cm");
            }
        }
        if (data.get(position).getNameBuild().equals("Encadenado / Viga") || data.get(position).getNameBuild().equals("Columna")) {
            if (data.get(position).getParamType().equals("0")){
                holder.type.setText("hierro de 8mm");
            }
            if (data.get(position).getParamType().equals("1")){
                holder.type.setText("hierro de 10mm");
            }
        }
        if (data.get(position).getNameBuild().equals("Zapata")) {
            if (data.get(position).getParamType().equals("0")){
                holder.type.setText("hierro de 8mm");
            }
            if (data.get(position).getParamType().equals("1")){
                holder.type.setText("hierro de 10mm");
            }
        }
        if (data.get(position).getNameBuild().equals("Piso")) {
            if (data.get(position).getParamType().equals("0")){
                holder.type.setText("ceramica 25x25cm");
            }
            if (data.get(position).getParamType().equals("1")){
                holder.type.setText("ceramica 40x40cm");
            }
            if (data.get(position).getParamType().equals("2")){
                holder.type.setText("porcelanato 60x60cm");
            }
            if (data.get(position).getParamType().equals("3")){
                holder.type.setText("porcelanato 60x120cm");
            }
        }
        if (data.get(position).getNameBuild().equals("Techo de teja")) {
            holder.type.setText("teja francesa");
        }
        if (data.get(position).getNameBuild().equals("Techo de chapa")) {
            if (data.get(position).getParamType().equals("0")){
                holder.type.setText("chapa acanalada");
            }
            if (data.get(position).getParamType().equals("1")){
                holder.type.setText("chapa trapezoidal");
            }
        }
        if (data.get(position).getNameBuild().equals("Revoque")) {
            if (data.get(position).getParamType().equals("0")){
                holder.type.setText("grueso");
            }
            if (data.get(position).getParamType().equals("1")){
                holder.type.setText("fino");
            }
        }
    }

    //Cantidad de elementos
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClicListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, ext, type;
        private ImageView img;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //Definicion
            name = (TextView)itemView.findViewById(R.id.edit_area_build_tvName);
            img = (ImageView)itemView.findViewById(R.id.edit_area_build_imgBuild);
            ext = (TextView)itemView.findViewById(R.id.edit_area_build_tvExt);
            type = (TextView)itemView.findViewById(R.id.edit_area_build_tvType);
        }
    }

}
