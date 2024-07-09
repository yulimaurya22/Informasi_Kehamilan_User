package com.yulia.informasikehamilan.INTERFACE;

import com.yulia.informasikehamilan.MODEL.Model_jam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_jam {
@GET("jadwal")
    Call<List<Model_jam>> tampiljam();
}
