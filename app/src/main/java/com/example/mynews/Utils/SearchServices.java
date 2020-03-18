package com.example.mynews.Utils;

import com.example.mynews.Models.Search.Response;
import com.example.mynews.Models.Search.SearchResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SearchServices {



    @GET("search/v2/articlesearch.json")
    Observable<SearchResult> getResults(
            @QueryMap Map<String, Object> map
    );



    Retrofit retrofit = RetrofitBuilder.retrofit;


}
