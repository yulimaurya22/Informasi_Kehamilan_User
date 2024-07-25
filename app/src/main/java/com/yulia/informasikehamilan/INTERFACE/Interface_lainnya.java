package com.yulia.informasikehamilan.INTERFACE;

import com.yulia.informasikehamilan.MODEL.Model_response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_lainnya {
    @GET("lainnya/")
    Call<Model_response> tampilLainnya();
}
