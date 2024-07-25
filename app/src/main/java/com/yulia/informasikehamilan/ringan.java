package com.yulia.informasikehamilan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.ADAPTOR.Adapter_berat;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.INTERFACE.Interface_berat;
import com.yulia.informasikehamilan.INTERFACE.Interface_ringan;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_ringan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ringan extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView_ringan;
    ArrayList<Model_ringan> ringanArrayList=new ArrayList<>();
    Interface_ringan interface_ringan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringan);
        getSupportActionBar().setTitle("Tanda Bahaya Ringan");
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView_ringan=findViewById(R.id.recycleviewringan);

        interface_ringan= KoneksiApi.RetKoneksiApi().create(Interface_ringan.class);

        baca_data_ringan();

        recyclerView_ringan.setLayoutManager(new LinearLayoutManager(this));
    }

    private void baca_data_ringan() {

        Toast.makeText(ringan.this, "Mengambil data...", Toast.LENGTH_LONG).show();
        Call<Model_response> call = interface_ringan.tampilRingan();
        call.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {

                System.out.println("asdasdasd");
                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if(res.getStatus().equals("berhasil")){


                    // convert result ke Model_user, karena sebelumnya dia masih Object, caranya dijadikan json terus dikonversi ke Model
                    ringanArrayList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_ringan>>() {
                    }.getType());
                    Adapter_berat adapter_berat=new Adapter_berat(ringanArrayList);
                    recyclerView_ringan.setAdapter(adapter_berat);

                }else{
                    Toast.makeText(ringan.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {
                // error
                System.out.println("error " +  throwable.getMessage());
                Toast.makeText(ringan.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
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