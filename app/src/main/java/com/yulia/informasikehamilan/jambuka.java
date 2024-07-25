package com.yulia.informasikehamilan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.INTERFACE.Interface_berat;
import com.yulia.informasikehamilan.INTERFACE.Interface_jam;
import com.yulia.informasikehamilan.INTERFACE.Interface_profil;
import com.yulia.informasikehamilan.MODEL.Model_jam;
import com.yulia.informasikehamilan.MODEL.Model_profil;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_user;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class jambuka extends AppCompatActivity {
    ActionBar actionBar;
    TextView tsenin, tselasa, trabu, tkamis, tjumat, tsabtu, tminggu, txbidan, txalamat, txjenis, txtelp;
    ImageView mgfoto;
    Interface_jam interface_jam;
    Interface_profil interface_profil;


    @SuppressLint("MissingInflatedId")
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
        txbidan = findViewById(R.id.bidan);
        txalamat = findViewById(R.id.alamat);
        txjenis = findViewById(R.id.jenis);
        txtelp = findViewById(R.id.telp);
        mgfoto = findViewById(R.id.fotoprofil);

        interface_profil=KoneksiApi.RetKoneksiApi().create(Interface_profil.class);
        Call<Model_response> panggil = interface_profil.tampilProfil();
        panggil.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {
                System.out.println("asdasdasd");
                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if(res.getStatus().equals("berhasil")){



                    List<Model_profil> outputList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_profil>>() {
                    }.getType());
                    /// pasang data ke view
                    ambilDataprofil(outputList);

                }else{
                    Toast.makeText(jambuka.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {
                Toast.makeText(jambuka.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        interface_jam= KoneksiApi.RetKoneksiApi().create(Interface_jam.class);
        Call<Model_response> call = interface_jam.tampilJam();
        call.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {
                System.out.println("asdasdasd");
                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if(res.getStatus().equals("berhasil")){




                    List<Model_jam> outputList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_jam>>() {
                    }.getType());
                    /// pasang data ke view
                    ambilData(outputList);

                }else{
                    Toast.makeText(jambuka.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {
                Toast.makeText(jambuka.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ambilDataprofil(List<Model_profil> modelProfil) {
        for (Model_profil profil : modelProfil){
            txbidan.setText(profil.getNama());
            txalamat.setText(profil.getAlamat());
            txjenis.setText(profil.getJeniskelamin());
            txtelp.setText(profil.getTelp());
            Glide.with(jambuka.this).load(profil.getFoto()).into(mgfoto);
        }
    }

    private void ambilData(List<Model_jam> modelJam) {
        for (Model_jam jam : modelJam) {
            tsenin.setText(jam.getSenin());
            tselasa.setText(jam.getSelasa());
            trabu.setText(jam.getRabu());
            tkamis.setText(jam.getKamis());
            tjumat.setText(jam.getJumat());
            tsabtu.setText(jam.getSabtu());
            tminggu.setText(jam.getMinggu());
        }
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