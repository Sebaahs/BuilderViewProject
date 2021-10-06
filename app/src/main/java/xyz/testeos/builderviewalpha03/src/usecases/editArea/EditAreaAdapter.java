package xyz.testeos.builderviewalpha03.src.usecases.editArea;

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

import xyz.testeos.builderviewalpha03.R;
import xyz.testeos.builderviewalpha03.src.model.domain.Build;
import xyz.testeos.builderviewalpha03.src.model.domain.Material;

public class EditAreaAdapter extends RecyclerView.Adapter<EditAreaAdapter.ViewHolder> {
    //Definicion
    private List<Build> data;
    private LayoutInflater inflater;


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

        return new EditAreaAdapter.ViewHolder(view);
    }

    //Binding
    @Override
    public void onBindViewHolder(@NonNull @NotNull EditAreaAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImgBuild());
        holder.name.setText(data.get(position).getNameBuild());
        holder.ext.setText(data.get(position).getParamExt());
        holder.type.setText(data.get(position).getParamType());
    }

    //Cantidad de elementos
    @Override
    public int getItemCount() {
        return data.size();
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
