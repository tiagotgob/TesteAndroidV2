        package br.com.tgob.testeandroid.activity;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import br.com.tgob.testeandroid.R;
        import br.com.tgob.testeandroid.helper.Validator;

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

                        if(!validator.validatePassword(password.getText().toString().trim()) || !validator.isValidEmail(user.getText().toString().trim())) {
                            Toast.makeText(MainActivity.this, "Password or User Invalid", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, AccountDetailActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }

        }
