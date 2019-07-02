package com.example.week4test;


import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RestrofitExample {
    public static final String BASE_URL="https://demo6983184.mockable.io/";
    public static final String PATH="coffees";
    public static String path="coffees";

    public Retrofit getRetrofitInstance(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        Retrofit retro= new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        return retro;
    }
    public CoffeApiService getService(){

        return getRetrofitInstance().create(CoffeApiService.class);
    }
    public interface CoffeApiService{

        @GET(PATH)
        Call<Coffe[]> getCoffes();


    }
}
