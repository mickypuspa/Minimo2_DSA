package edu.upc.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.login.Entidades.Token;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private API api;

    private void guardarToken(String token){
        //Creamos objeto preferences que se guardara en un XML llamado tokenUsuario y es privado porque
        //solo podremos acceder mediante nuestra app. Creamos un editor y guardamos el valor que le pasamos
        //como parametro con la llave "token"

        //Para ver sharedPreferences: device file explorer->data->data->edu.upc.login->shared_prefs

        SharedPreferences preferences = getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.commit();
        //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Creamos interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Creamos cliente
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //Crear retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.203:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        //Llamamos a servicios que hemos definido en la API
        api = retrofit.create(API.class);

        final TextView nombre = findViewById(R.id.nombreText);
        final TextView mail = findViewById(R.id.correoText);
        final TextView password = findViewById(R.id.contraseñaText);
        final TextView confPassword = findViewById(R.id.confContraseñaText);
        Button registrarseButton = findViewById(R.id.regButton);
            registrarseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   RegisterCredentials registerC = new RegisterCredentials();
                   registerC.setNombre((String) nombre.getText().toString());
                   registerC.setMail((String) mail.getText().toString());
                   registerC.setPassword((String) password.getText().toString());
                   registerC.setConfirm((String) confPassword.getText().toString());

                   Call<Token> call = api.register(registerC);
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            if(response.isSuccessful()) {
                                String token = response.body().getToken();
                                guardarToken(token);
                                Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(i);
                            }

                            else {
                                Toast.makeText(getApplicationContext(), "Error " + response.code() + ": " +response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Error al acceder a la API", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });


                }
            });
    }

}
