package com.example.mynews.Utils;

import com.example.mynews.Models.TopStories.TopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface BusinessServices {
    @GET("topstories/v2/business.json?api-key=eZx2oaUq16FEABHgoikJrObx8mE5i4X6")
    Observable<TopStories> getResults();

    Retrofit retrofit = RetrofitBuilder.retrofit;
}

