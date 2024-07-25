package com.yulia.informasikehamilan.INTERFACE;

import com.yulia.informasikehamilan.MODEL.Model_jam;
import com.yulia.informasikehamilan.MODEL.Model_response;
import com.yulia.informasikehamilan.MODEL.Model_user;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_user {
    /// API nya diarahkan ke akun
    /// nanti pas pencet login bakal manggil fungsi login
    @GET("akun/")
    Call<Model_response> login();
}
