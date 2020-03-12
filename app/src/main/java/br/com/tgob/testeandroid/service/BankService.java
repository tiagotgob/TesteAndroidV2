package br.com.tgob.testeandroid.service;

import br.com.tgob.testeandroid.entity.UserData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BankService {

    @GET("{userId}")
    Call<UserData> getUserData(@Path("userId") int userId);
}
