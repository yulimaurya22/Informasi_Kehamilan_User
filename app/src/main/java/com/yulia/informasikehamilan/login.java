package com.yulia.informasikehamilan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.HELPER.SharedPreferencesUtils;
import com.yulia.informasikehamilan.INTERFACE.Interface_user;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_user;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    private EditText username, password;
    private  Button loginbtn;
    private ProgressBar loadingBar;

    private  String Username = "yuliamp";
    private  String Password = "user";


    private Interface_user interfaceUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        loadingBar = findViewById(R.id.loadingBar);

        interfaceUser = KoneksiApi.RetKoneksiApi().create(Interface_user.class);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * loading, sembunyi in tombol, terus muncul in loadingBar
                 * pas selesai koneksi API tombol di muncul in lagi, loading nya disembunyi in
                 **/

                loginbtn.setVisibility(View.INVISIBLE);
                loadingBar.setVisibility(View.VISIBLE);

                Call<Model_response> call = interfaceUser.login();
                call.enqueue(new Callback<Model_response>() {
                    @Override
                    public void onResponse(Call<Model_response> call, Response<Model_response> response) {

                        loginbtn.setVisibility(View.VISIBLE);
                        loadingBar.setVisibility(View.INVISIBLE);

                        // dapetin body
                        Model_response res = response.body();
                        // verif kalau berhasil statusnya
                        if(res.getStatus().equals("berhasil")){


                            // convert result ke Model_user, karena sebelumnya dia masih Object, caranya dijadikan json terus dikonversi ke Model
                            List<Model_user> outputList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_user>>() {
                            }.getType());
                            // cek login berhasil ?
                            if(cekLogin(outputList)){
                                Toast.makeText(login.this, "Login Berhasil!!", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(login.this, beranda.class);
                                startActivity(login);

                            }else{
                                Toast.makeText(login.this, "Username atau password salah!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(login.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onFailure(Call<Model_response> call, Throwable throwable) {
                        // error
                        loginbtn.setVisibility(View.VISIBLE);
                        loadingBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(login.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                // Fungsi lama di bawah
                /*
                if(username.getText().toString().equalsIgnoreCase(Username) && password.getText().toString().equalsIgnoreCase(Password)){
                    Intent login = new Intent(login.this, beranda.class);
                    startActivity(login);

                    Toast.makeText(login.this, "Login Berhasil!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(login.this, "Username atau password salah!!", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    /**
     * fungsi untuk cek login
     * cari dari List ada ngga username dan password nya
     * looping List<Model_user> dan cek satu"
     * kalau ada langsung return true, jadi ga usah nunggu looping nya selesai
     * **/
    private boolean cekLogin(List<Model_user> modelUsers){
        // loop data
        for (Model_user user: modelUsers) {
            // cek ada ga username dan pass nya
            if(user.getUsername().equalsIgnoreCase(username.getText().toString())){
                if(user.getPassword().equalsIgnoreCase(password.getText().toString()))
                //simpen data username nya
                {
                    SharedPreferencesUtils.setStringValue(getApplicationContext(),"username",user.getUsername());
                }
                return true;
            }
        }

        return false;
    }
}