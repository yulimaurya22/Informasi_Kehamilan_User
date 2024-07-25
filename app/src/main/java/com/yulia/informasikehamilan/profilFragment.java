package com.yulia.informasikehamilan;

import static com.yulia.informasikehamilan.R.layout.fragment_profil;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yulia.informasikehamilan.API.KoneksiApi;
import com.yulia.informasikehamilan.HELPER.SharedPreferencesUtils;
import com.yulia.informasikehamilan.INTERFACE.Interface_user;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_user;
import com.yulia.informasikehamilan.databinding.ActivityBerandaBinding;
import com.yulia.informasikehamilan.databinding.ActivityRiwayatBinding;
import com.yulia.informasikehamilan.databinding.FragmentProfilBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profilFragment extends Fragment {
    private Interface_user interfaceUser;
    FragmentProfilBinding mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //   View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        mView = FragmentProfilBinding.inflate(inflater, container, false);
        View rootView = mView.getRoot();


        Button btnEdit = (Button) rootView.findViewById(R.id.edit);
        Button btnLogout = (Button) rootView.findViewById(R.id.logout);


        interfaceUser = KoneksiApi.RetKoneksiApi().create(Interface_user.class);
        Call<Model_response> call = interfaceUser.login();
        call.enqueue(new Callback<Model_response>() {
            @Override
            public void onResponse(Call<Model_response> call, Response<Model_response> response) {
                // dapetin body
                Model_response res = response.body();
                // verif kalau berhasil statusnya
                if (res.getStatus().equals("berhasil")) {


                    // convert result ke Model_user, karena sebelumnya dia masih Object, caranya dijadikan json terus dikonversi ke Model
                    List<Model_user> outputList = new Gson().fromJson(new Gson().toJson(res.getResult()), new TypeToken<List<Model_user>>() {
                    }.getType());
                    /// pasang data ke view
                    ambilData(outputList);

                }

            }

            @Override
            public void onFailure(Call<Model_response> call, Throwable throwable) {

            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kosongi key username, terus buka splashcrenn
                SharedPreferencesUtils.setStringValue(getActivity(), "username", "");
                Intent intent = new Intent(getActivity(), splash_screen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), profil.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;

    }

    private void ambilData(List<Model_user> ModelUsers) {
        for (Model_user user : ModelUsers) {
            if (user.getUsername().equalsIgnoreCase(SharedPreferencesUtils.getStringValue(getContext().getApplicationContext(), "username"))) {
                mView.namauser.setText(user.getNamabunda());
                mView.username.setText(user.getUsername());
                mView.password.setText(user.getPassword());
            }
        }

    }
}