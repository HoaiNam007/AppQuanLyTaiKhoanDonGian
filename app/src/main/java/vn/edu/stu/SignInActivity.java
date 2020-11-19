package vn.edu.stu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    EditText txtUsernameSignIn, txtPasswordSignIn;
    Button btnSignIn;
    ArrayList<Account> dsAcc;
    AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        String ip = new IpAddress().getIp();
        String url = "http://"+ip+"/phpmyadmin/Android/getData.php";
        addControls();
        JSONReadData jsonReadData = new JSONReadData(url, this, dsAcc, adapter);
        addEvents();


    }

    private void addEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String us = txtUsernameSignIn.getText().toString();
                String pw = txtPasswordSignIn.getText().toString();
                boolean check = false;

                for (Account a : dsAcc) {
                    if (us.equals(a.getUsername()) && pw.equals(a.getPassword())) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    Toast.makeText(SignInActivity.this, "Sign in completely", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(SignInActivity.this, "Wrong Username or Password", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                }
            }
        });
    }

    private void addControls() {
        txtUsernameSignIn = findViewById(R.id.txtUsernameSignIn);
        txtPasswordSignIn = findViewById(R.id.txtPasswordSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
        dsAcc = new ArrayList<>();
        adapter = new AccountAdapter(null, R.layout.activity_row_account, dsAcc);
    }
}