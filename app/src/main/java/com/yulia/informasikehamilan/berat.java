package com.yulia.informasikehamilan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.ADAPTOR.Adapter_berat;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.INTERFACE.Interface_berat;
import com.yulia.informasikehamilan.MODEL.Model_berat;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_ringan;
import com.yulia.informasikehamilan.MODEL.Model_user;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class berat extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView_berat;
    ArrayList<Model_ringan> beratArrayList=new ArrayList<>();
    Interface_berat interface_berat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berat);
        getSupportActionBar().setTitle("Tanda bahaya Berat");
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView_berat=findViewById(R.id.recycleviewberat);

        interface_berat= KoneksiApi.RetKoneksiApi().create(Interface_berat.class);

        baca_data_berat();

        recyclerView_berat.setLayoutManager(new LinearLayoutManager(this));
    }

    private void baca_data_berat() {
//        Call <List<Model_berat>> tampildata=interface_berat.tampilBerat();
//        tampildata.enqueue(new Callback<List<Model_berat>>() {
//            @Override
//            public void onResponse(Call<List<Model_berat>> call, Response<List<Model_berat>> response) {
//                ArrayList<Model_berat> beratArrayList= (ArrayList<Model_berat>) response.body();
//                Adapter_berat adapter_berat=new Adapter_berat(beratArrayList);
//                recyclerView_berat.setAdapter(adapter_berat);
//            }
//
//            @Override
//            public void onFailure(Call<List<Model_berat>> call, Throwable throwable) {
//
//            }
//        });
        Toast.makeText(berat.this, "Mengambil data...", Toast.LENGTH_LONG).show();
        Call<Model_response> call = interface_berat.tampilBerat();
        call.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {


                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if(res.getStatus().equals("berhasil")){


                    // convert result ke Model_berat, karena sebelumnya dia masih Object, caranya dijadikan json terus dikonversi ke Model
                    beratArrayList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_ringan>>() {
                    }.getType());
                    Adapter_berat adapter_berat=new Adapter_berat(beratArrayList);
                    recyclerView_berat.setAdapter(adapter_berat);

                }else{
                    Toast.makeText(berat.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {
                // error
                System.out.println("error " +  throwable.getMessage());
                Toast.makeText(berat.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //tombol kembali
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    //tombol kembali
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}