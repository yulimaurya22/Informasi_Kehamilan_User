package com.yulia.informasikehamilan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.ADAPTOR.Adapter_berat;
import com.yulia.informasikehamilan.ADAPTOR.Adapter_notif;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.INTERFACE.Interface_notif;
import com.yulia.informasikehamilan.INTERFACE.Interface_ringan;
import com.yulia.informasikehamilan.MODEL.Model_notif;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_ringan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class notif extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView_notif;
    ArrayList<Model_notif> notifArrayList=new ArrayList<>();
    Interface_notif interface_notif;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        getSupportActionBar().setTitle("Notifikasi");
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView_notif=findViewById(R.id.recycleviewNotif);

        interface_notif= KoneksiApi.RetKoneksiApi().create(Interface_notif.class);

        baca_data_notif();

        recyclerView_notif.setLayoutManager(new LinearLayoutManager(this));
    }

    private void baca_data_notif() {
        Toast.makeText(notif.this, "Loading....", Toast.LENGTH_LONG).show();
        Call<Model_response> call = interface_notif.tampilNotif();
        call.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {
                System.out.println("asdasdasd");
                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if(res.getStatus().equals("berhasil")){


                    // convert result ke Model_user, karena sebelumnya dia masih Object, caranya dijadikan json terus dikonversi ke Model
                    notifArrayList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_notif>>() {
                    }.getType());
                    Adapter_notif adapter_notif=new Adapter_notif(notifArrayList);
                    recyclerView_notif.setAdapter(adapter_notif);

                }else{
                    Toast.makeText(notif.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }


            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {
                System.out.println("error " +  throwable.getMessage());
                Toast.makeText(notif.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

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