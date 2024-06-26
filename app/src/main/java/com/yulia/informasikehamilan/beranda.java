package com.yulia.informasikehamilan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class beranda extends AppCompatActivity {
    CardView Tombolsatu;
    CardView Tomboldua;
    CardView Tomboltiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        getSupportActionBar().setTitle("Beranda");

        Tombolsatu = findViewById(R.id.cdringan);
        Tombolsatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(beranda.this, ringan.class);
                startActivity(open);
            }
        });
        Tomboldua = findViewById(R.id.cdberat);
        Tomboldua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(beranda.this, berat.class);
                startActivity(open);
            }
        });
        Tomboltiga = findViewById(R.id.cdlainnya);
        Tomboltiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(beranda.this, lainnya.class);
                startActivity(open);
            }
        });
    }
}