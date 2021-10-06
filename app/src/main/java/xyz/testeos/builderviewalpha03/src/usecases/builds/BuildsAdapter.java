package xyz.testeos.builderviewalpha03.src.usecases.builds;

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

public class BuildsAdapter extends RecyclerView.Adapter<BuildsAdapter.ViewHolder>
                           implements View.OnClickListener {

    //Definicion
    private List<Build> data;
    private View.OnClickListener listener;


    //Constructor
    public BuildsAdapter(List<Build> data) {
        this.data = data;
    }

    //Creating
    @NonNull
    @NotNull
    @Override
    public BuildsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        ////Definicion del inflater para cada elemento
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_build, parent , false);

        //Definicion del listener
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    //Binding
    @Override
    public void onBindViewHolder(@NonNull @NotNull BuildsAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImgBuild());
        holder.name.setText(data.get(position).getNameBuild());
    }

    //Cantidad de elementos
    @Override
    public int getItemCount() {
        return data.size();
    }

    //Set para el listener
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    //Evento click
    @Override
    public void onClick(View v) {
        if (listener != null)listener.onClick(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView img;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //Definicion
            name = (TextView)itemView.findViewById(R.id.tvNameBuild);
            img = (ImageView)itemView.findViewById(R.id.imgBuild);
        }
    }
}
