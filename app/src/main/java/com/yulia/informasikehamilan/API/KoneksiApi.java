package com.yulia.informasikehamilan.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KoneksiApi {
    public final static String url_base="https://tandabahayakehamilan.000webhostapp.com/Api/";
    private  static Retrofit retrofit;

    public  static  Retrofit RetKoneksiApi(){
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(url_base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
