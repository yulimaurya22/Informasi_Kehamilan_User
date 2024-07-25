package com.yulia.informasikehamilan.INTERFACE;

import com.yulia.informasikehamilan.MODEL.Model_berat;
import com.yulia.informasikehamilan.MODEL.Model_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_ringan {
    @GET("ringan/")
    Call<Model_response> tampilRingan();
}
