package com.yulia.informasikehamilan.INTERFACE;

import com.yulia.informasikehamilan.MODEL.Model_jam;
import com.yulia.informasikehamilan.MODEL.Model_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_jam {
    @GET("jadwal/")
    Call<Model_response> tampilJam();
}
