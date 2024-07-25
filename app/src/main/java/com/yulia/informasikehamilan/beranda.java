package com.yulia.informasikehamilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.HELPER.SharedPreferencesUtils;
import com.yulia.informasikehamilan.INTERFACE.Interface_user;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_user;
import com.yulia.informasikehamilan.databinding.ActivityBerandaBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class beranda extends AppCompatActivity {
    ActivityBerandaBinding binding;
    TextView tnamaakun;
    private Interface_user interfaceUser;

    CardView Satu;
    CardView Dua;
    CardView Tiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityBerandaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navbar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new homeFragment());
                    break;

                case R.id.chat:
                    replaceFragment(new chatFragment());
                    break;

                case R.id.history:
                    replaceFragment(new historyFragment());
                    break;
                case R.id.profil:
                    replaceFragment(new profilFragment());
                    break;
            }
            return true;
        });


        Satu = findViewById(R.id.cardringan);
        Satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beranda.this, ringan.class);
                startActivity(intent);
            }
        });

        Dua = findViewById(R.id.cdberat);
        Dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beranda.this, berat.class);
                startActivity(intent);
            }
        });

        Tiga = findViewById(R.id.cdlainnya);
        Tiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beranda.this, lainnya.class);
                startActivity(intent);
            }
        });

        tnamaakun = findViewById(R.id.namaakun);

        interfaceUser = KoneksiApi.RetKoneksiApi().create(Interface_user.class);
        Call<Model_response> call = interfaceUser.login();
        call.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {
                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if(res.getStatus().equals("berhasil")){


                    // convert result ke Model_user, karena sebelumnya dia masih Object, caranya dijadikan json terus dikonversi ke Model
                    List<Model_user> outputList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_user>>() {
                    }.getType());
                    /// pasang data ke view
                    ambilData(outputList);

                }else{
                    Toast.makeText(beranda.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {

            }
        });
    }

    private void ambilData(List<Model_user> modelUsers) {
        // loop data
        for (Model_user user: modelUsers) {
            // cek ada ga username dan pass ny
            if (user.getUsername().equalsIgnoreCase(SharedPreferencesUtils.getStringValue(getApplicationContext(), "username"))) {
                tnamaakun.setText(user.getNamabunda());
            }
        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fg_bar,fragment);
        fragmentTransaction.commit();
    }


    public void notif(View view) {
        Intent intent = new Intent(beranda.this, notif.class);
        startActivity(intent);

    }
}