package edu.upc.login;



import java.util.List;

import edu.upc.login.Entidades.Ranking;
import edu.upc.login.Entidades.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
/*    @GET("tracks")
    Call<List<User>> getTracks();

    @POST("tracks")
    Call<User> addTrack (@Body User user);

    @PUT("tracks")
    Call<Void> updateTrack (@Body User user);

    @DELETE("tracks/{id}")
    Call<Void> deleteTrack (@Path("id") String id);
*/
@GET("game/ranking")
Call <RankingRespuesta> getRanking();

//Servicio que pasa un JSON con las credenciales del login y devuelve un token
//que se guardara en sharedPreferences
//Para ver sharedPreferences: device file explorer->data->data->edu.upc.login->shared_prefs
@POST("auth/login")
    Call<Token> login(@Body LoginCredentials loginCredentials);

@POST("auth/register")
    Call<Token> register(@Body RegisterCredentials registerCredentials);

}