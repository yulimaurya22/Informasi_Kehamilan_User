package com.yulia.informasikehamilan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.INTERFACE.Interface_berat;
import com.yulia.informasikehamilan.INTERFACE.Interface_jam;
import com.yulia.informasikehamilan.MODEL.Model_jam;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class jambuka extends AppCompatActivity {
    ActionBar actionBar;
    TextView tsenin, tselasa, trabu, tkamis, tjumat, tsabtu, tminggu;

    Interface_jam interface_jam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jambuka);
        getSupportActionBar().setTitle("Jam Praktek Bidan");
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tsenin = findViewById(R.id.senin);
        tselasa = findViewById(R.id.selasa);
        trabu = findViewById(R.id.rabu);
        tkamis = findViewById(R.id.kamis);
        tjumat = findViewById(R.id.jumat);
        tsabtu = findViewById(R.id.sabtu);
        tminggu = findViewById(R.id.minggu);

        interface_jam= KoneksiApi.RetKoneksiApi().create(Interface_jam.class);

        Call<List<Model_jam>> call = interface_jam.tampiljam();
        call.enqueue(new Callback<List<Model_jam>>() {
            @Override
            public void onResponse(Call<List<Model_jam>> call, Response<List<Model_jam>> response) {
                tsenin.setText(response.body().get(0).getSenin());
                tselasa.setText(response.body().get(0).getSelasa());
                trabu.setText(response.body().get(0).getRabu());
                tkamis.setText(response.body().get(0).getKamis());
                tjumat.setText(response.body().get(0).getJumat());
                tsabtu.setText(response.body().get(0).getSabtu());
                tminggu.setText(response.body().get(0).getMinggu());

            }

            @Override
            public void onFailure(Call<List<Model_jam>> call, Throwable throwable) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

    }
}