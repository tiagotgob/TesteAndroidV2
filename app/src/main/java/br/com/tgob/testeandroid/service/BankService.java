package br.com.tgob.testeandroid.service;

import br.com.tgob.testeandroid.entity.Login;
import br.com.tgob.testeandroid.entity.UserData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BankService {

    @GET("statements/{userId}")
    Call<UserData> getUserData(@Path("userId") int userId);

    @FormUrlEncoded
    @POST("login")
    Call<Login> login(@Field("user") String user, @Field("password") String password);
}
