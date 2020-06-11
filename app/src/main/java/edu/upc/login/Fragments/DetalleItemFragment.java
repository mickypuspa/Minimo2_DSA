package edu.upc.login.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.upc.login.Entidades.Item;
import edu.upc.login.R;



public class DetalleItemFragment extends Fragment {
    TextView nombreDetalle;
    ImageView imagenDetalle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.detalle_item_fragment,container,false);
        nombreDetalle=view.findViewById(R.id.nombre_detalle);
        imagenDetalle=view.findViewById(R.id.imagen_detalle);
        //Crear objeto bundle para recibir el objeto enviado por argumentos
        Bundle objetoItem = getArguments();
        Item item =null;
        //validacion para verificar si existen argumentos enviados para mostrar
        if(objetoItem!=null){
            item = (Item)objetoItem.getSerializable("objeto");
            //Establecer los datos en las vistas
            nombreDetalle.setText(item.getNombre());
            imagenDetalle.setImageResource(item.getImagenid());
        }
        return view;
    }

}
