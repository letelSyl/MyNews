package com.example.mynews.Utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.*;

public interface RetrofitBuilder{




    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();


    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();



}
