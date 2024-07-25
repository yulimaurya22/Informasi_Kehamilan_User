package com.yulia.informasikehamilan;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.INTERFACE.Interface_jam;
import com.yulia.informasikehamilan.INTERFACE.Interface_profil;
import com.yulia.informasikehamilan.MODEL.Model_jam;
import com.yulia.informasikehamilan.MODEL.Model_profil;
import com.yulia.informasikehamilan.MODEL.Model_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatFragment extends Fragment {
    TextView tsenin, tselasa, trabu, tkamis, tjumat, tsabtu, tminggu, txbidan, txalamat, txjenis, txtelp;
    ImageView mgfoto;
    Interface_jam interface_jam;
    Interface_profil interface_profil;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        tsenin = rootView.findViewById(R.id.senin);
        tselasa = rootView.findViewById(R.id.selasa);
        trabu = rootView.findViewById(R.id.rabu);
        tkamis = rootView.findViewById(R.id.kamis);
        tjumat = rootView.findViewById(R.id.jumat);
        tsabtu = rootView.findViewById(R.id.sabtu);
        tminggu = rootView.findViewById(R.id.minggu);
        txbidan = rootView.findViewById(R.id.bidan);
        txalamat = rootView.findViewById(R.id.alamat);
        txjenis = rootView.findViewById(R.id.jenis);
        txtelp = rootView.findViewById(R.id.telp);
        mgfoto = rootView.findViewById(R.id.fotoprofil);

        interface_profil= KoneksiApi.RetKoneksiApi().create(Interface_profil.class);
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

                }

            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {

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

                }

            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {

            }
        });
        return rootView;
    }

    private void ambilDataprofil(List<Model_profil> modelProfil) {
        for (Model_profil profil : modelProfil){
            txbidan.setText(profil.getNama());
            txalamat.setText(profil.getAlamat());
            txjenis.setText(profil.getJeniskelamin());
            txtelp.setText(profil.getTelp());
            Glide.with(getContext()).load(profil.getFoto()).into(mgfoto);
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


}
