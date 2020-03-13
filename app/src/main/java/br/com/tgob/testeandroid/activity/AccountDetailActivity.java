package br.com.tgob.testeandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import br.com.tgob.testeandroid.R;
import br.com.tgob.testeandroid.adapter.UserDetailAdapter;
import br.com.tgob.testeandroid.entity.StatementList;
import br.com.tgob.testeandroid.entity.UserData;
import br.com.tgob.testeandroid.service.RetroFitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserDetailAdapter userDetailAdapter;
    private TextView name, bankAccount, agency, balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        String spName = pref.getString("name", null);
        String spAgency = pref.getString("agency", null);
        String spBankAcccount = pref.getString("bankAccount", null);
        String spBalance = pref.getString("balance", null);

        recyclerView = findViewById(R.id.rvList);
        name = findViewById(R.id.tvName);
        bankAccount = findViewById(R.id.tvAccount);
        agency = findViewById(R.id.tvaccountNumber);
        balance = findViewById(R.id.tvUserBalance);

        balance.setText(spBalance);
        name.setText(spName);
        agency.setText(spAgency);
        bankAccount.setText(spBankAcccount);

        Call<UserData> call = new RetroFitConfig().getBankService().getUserData(1);

        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                UserData userData = response.body();

                if (userData != null && userData.getStatementList() != null) {
                    userDetailAdapter = new UserDetailAdapter(AccountDetailActivity.this, userData.getStatementList());

                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AccountDetailActivity. this, 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(userDetailAdapter);
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e("erro:", "erro:" + t.getMessage());
            }
        });
    }
}
