package br.com.tgob.testeandroid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private TextView name, bankAccount, balance;
    private Button btngoBack;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        String spName = pref.getString("name", null);
        String spAgency = pref.getString("agency", null);
        String spBankAcccount = pref.getString("bankAccount", null);
        String spBalance = pref.getString("balance", null);
        int userId = pref.getInt("userId", -1);

        recyclerView = findViewById(R.id.rvList);
        btngoBack = findViewById(R.id.btnBack);
        name = findViewById(R.id.tvName);
        bankAccount = findViewById(R.id.tvaccountNumber);
        balance = findViewById(R.id.tvUserBalance);

        balance.setText("R$ " + spBalance);
        name.setText(spName);
        bankAccount.setText(spBankAcccount + " "+ "/ " + spAgency);

        Call<UserData> call = new RetroFitConfig().getBankService().getUserData(userId);

        call.enqueue(new Callback<UserData>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                UserData userData = response.body();

                if (userData != null && userData.getStatementList() != null) {
                    userDetailAdapter = new UserDetailAdapter(AccountDetailActivity.this, userData.getStatementList());

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AccountDetailActivity.this);
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

        btngoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
