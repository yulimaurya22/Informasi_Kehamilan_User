package com.yulia.informasikehamilan.INTERFACE;

import com.yulia.informasikehamilan.MODEL.Model_berat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_berat {
    @GET("berat/")
    Call<List<Model_berat>> tampilBerat();
}
