package com.example.mynews.Utils;

import com.example.mynews.Models.MostPopular.MostPopular;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface MostPopularServices {
    @GET("mostpopular/v2/viewed/7.json?api-key=bYYunrl1W9G0u7HivUKGfVtTGXrqaVQy")
    Observable<MostPopular> getResults();

    Retrofit retrofit = RetrofitBuilder.retrofit;

}

