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

import edu.upc.login.Entidades.Ranking;
import edu.upc.login.R;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Ranking> model;

    //listener
    private View.OnClickListener listener;

    public AdapterRanking(Context context, ArrayList<Ranking> model){
        this.inflater= LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ranking_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nombre=model.get(position).getUsername();
        int puntos=model.get(position).getPuntos();
        //int imagen=model.get(position).getImagen();
        holder.nombre.setText(nombre);
        holder.puntos.setText(puntos);
        //holder.imagen.setImageResource(imagen);


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

        TextView nombre, puntos;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.username);
            puntos=itemView.findViewById(R.id.puntos);
            //imagen=itemView.findViewById(R.id.foto);
        }
    }
}