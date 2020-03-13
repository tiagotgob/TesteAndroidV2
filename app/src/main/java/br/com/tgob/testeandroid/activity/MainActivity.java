        package br.com.tgob.testeandroid.activity;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.DefaultItemAnimator;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import br.com.tgob.testeandroid.R;
        import br.com.tgob.testeandroid.adapter.UserDetailAdapter;
        import br.com.tgob.testeandroid.entity.Login;
        import br.com.tgob.testeandroid.entity.UserAccount;
        import br.com.tgob.testeandroid.entity.UserData;
        import br.com.tgob.testeandroid.helper.Validator;
        import br.com.tgob.testeandroid.service.RetroFitConfig;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        public class MainActivity extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                final EditText password = findViewById(R.id.edtPassword);
                final EditText user = findViewById(R.id.edtUser);
                Button login = findViewById(R.id.btnLogin);
                final Validator validator = new Validator();

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //validate if the user or password are different from the function.
                        if(!validator.validatePassword(password.getText().toString().trim()) || !validator.isValidEmail(user.getText().toString().trim())) {
                            Toast.makeText(MainActivity.this, "Password or User Invalid", Toast.LENGTH_LONG).show();
                        }else {

                            Call<Login> call = new RetroFitConfig().getBankService().login(password.getText().toString().trim(), user.getText().toString().trim());

                            call.enqueue(new Callback<Login>() {
                                @Override
                                public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                                    Login login = response.body();
                                    Log.d("resposta:", login.toString());

                                    if (login != null && login.getUserAccount() != null) {

                                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                                        SharedPreferences.Editor editor = pref.edit();

                                        editor.putString("name", login.getUserAccount().getBankAccount().trim());
                                        editor.putString("bankAccount", login.getUserAccount().getName().trim());
                                        editor.putString("agency", login.getUserAccount().getAgency().trim());
                                        editor.putString("balance", login.getUserAccount().getBalance().toString().trim());

                                        editor.commit();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Login> call, Throwable t) {
                                    Log.e("erro:", "erro:" + t.getMessage());
                                }
                            });

                            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, AccountDetailActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }

        }
