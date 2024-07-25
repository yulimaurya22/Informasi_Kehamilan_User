package com.yulia.informasikehamilan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.HELPER.SharedPreferencesUtils;
import com.yulia.informasikehamilan.INTERFACE.Interface_user;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_user;
import com.yulia.informasikehamilan.databinding.ActivityRiwayatBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class riwayat extends AppCompatActivity {
    ActionBar actionBar;
    private Interface_user interfaceUser;
    ActivityRiwayatBinding mView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pakai databinding
        mView = DataBindingUtil.setContentView(this,R.layout.activity_riwayat);
//        setContentView(R.layout.activity_riwayat);
        getSupportActionBar().setTitle("Riwayat Periksa Pasien");
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mengampil data dari koneksi api
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
                    Toast.makeText(riwayat.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {
                // error

                Toast.makeText(riwayat.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ambilData(List<Model_user> modelUsers){
        // loop data
        for (Model_user user: modelUsers) {
            // cek ada ga username dan pass ny
            if(user.getUsername().equalsIgnoreCase(SharedPreferencesUtils.getStringValue(getApplicationContext(),"username"))){
                mView.nama.setText(user.getNamabunda());
                mView.usia.setText(user.getUsiakehamilan());
                mView.bb.setText(user.getBeratbadanbunda());
                mView.tb.setText(user.getTinggibadanbunda());
                mView.tekanan.setText(user.getTekanandarah());
                mView.beratjanin.setText(user.getBeratjanin());
                mView.panjangjanin.setText(user.getPanjangjanin());
                mView.detak.setText(user.getDetakjantungjanin());
                mView.obat.setText(user.getObatyangdikonsumsi());

            }
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