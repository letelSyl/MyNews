package com.example.mynews.Utils;

import com.example.mynews.Models.TimeWire;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface NytServices {
    @GET("/svc/news/v3/content/all/all.json?api-key=RsuNSlDaajE8Y1WOkVP2S17DDZdOqhHE")
    Observable<TimeWire> getResults();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

