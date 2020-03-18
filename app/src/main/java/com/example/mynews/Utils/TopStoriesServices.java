package com.example.mynews.Utils;

import com.example.mynews.Models.TopStories.TopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface TopStoriesServices {
    @GET("topstories/v2/home.json?api-key=eZx2oaUq16FEABHgoikJrObx8mE5i4X6")
    Observable<TopStories> getResults();

    Retrofit retrofit = RetrofitBuilder.retrofit;


}

// see query map = search
// work(er) manager instead alarm manager? = notif
// travis = integ continu