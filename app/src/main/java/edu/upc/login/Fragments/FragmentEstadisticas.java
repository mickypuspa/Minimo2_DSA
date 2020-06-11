package edu.upc.login.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.upc.login.API;
import edu.upc.login.Adaptadores.AdapterRanking;
import edu.upc.login.Entidades.Ranking;
import edu.upc.login.R;
import edu.upc.login.RankingRespuesta;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentEstadisticas extends Fragment {
    AdapterRanking adapterRanking;
    RecyclerView recyclerViewRanking;
    ArrayList<Ranking> listaRanking;
    private API api;
    private static String TAG ="Ranking";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        recyclerViewRanking = view.findViewById(R.id.recyclerId);
        listaRanking = new ArrayList<Ranking>();
        //cargar la lista
        cargarLista();




        return view;
    }

    public void cargarLista() {
        //Creamos interceptor
       /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Creamos cliente
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //Crear retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.203:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();*/

        //Llamamos a servicios que hemos definido en la API
        //api = retrofit.create(API.class);

        Call<RankingRespuesta> dameRanking = api.getRanking();
        dameRanking.enqueue(new Callback<RankingRespuesta>() {
            @Override
            public void onResponse(Call<RankingRespuesta> call, Response<RankingRespuesta> response) {
                if(response.isSuccessful()) {
                    RankingRespuesta rankingRespuesta = response.body();
                    ArrayList<Ranking> lista = rankingRespuesta.getResults();

                    for (int i = 0; i < lista.size(); i++) {
                        Ranking r = lista.get(i);
                        r.getUsername();
                        r.getPuntos();
                        //r.getImagen();

                    }


                    }
                else{
                    Log.e(TAG,"onResponse: "    +   response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RankingRespuesta> call, Throwable t) {

            }
        });
        }
    }






