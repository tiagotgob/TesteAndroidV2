package br.com.tgob.testeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText password = findViewById(R.id.edtPassword);
        final EditText user = findViewById(R.id.edtUser);
        Button login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isValidPassword(password.getText().toString().trim()) && password.getText().toString().length() < 8 ) {
                    Toast.makeText(MainActivity.this, "Password Invalid", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Password Valid", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, AccountDetails.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
