package edu.upc.login.Adaptadores;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.upc.login.Entidades.Item;
import edu.upc.login.R;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Item> model;

    //listener
    private View.OnClickListener listener;

    public AdapterItem(Context context, ArrayList<Item> model){
        this.inflater= LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_items,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nombre=model.get(position).getNombre();
        String descripcion=model.get(position).getDescripcion();
        int imagen = model.get(position).getImagenid();
        holder.nombres.setText(nombre);
        holder.descripciones.setText(descripcion);
        holder.imagen.setImageResource(imagen);


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null)
            listener.onClick(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombres, descripciones;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres=itemView.findViewById(R.id.nombre_item);
            descripciones=itemView.findViewById(R.id.descripcion);
            imagen=itemView.findViewById(R.id.imagen_item);
        }
    }
}
