package com.yulia.informasikehamilan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText username, password;
    private  Button loginbtn;

    private  String Username = "yuliamp";
    private  String Password = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equalsIgnoreCase(Username) && password.getText().toString().equalsIgnoreCase(Password)){
                    Intent login = new Intent(login.this, beranda.class);
                    startActivity(login);

                    Toast.makeText(login.this, "Login Berhasil!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(login.this, "Username atau password salah!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}