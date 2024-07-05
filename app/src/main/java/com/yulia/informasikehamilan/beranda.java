package com.yulia.informasikehamilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.yulia.informasikehamilan.databinding.ActivityBerandaBinding;

public class beranda extends AppCompatActivity {
    ActivityBerandaBinding binding;

    CardView Satu;
    CardView Dua;
    CardView Tiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
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