package com.example.mynews.Utils;

import android.util.Log;

import com.example.mynews.Models.MostPopular.MostPopular;
import com.example.mynews.Models.Search.Doc;
import com.example.mynews.Models.Search.Response;
import com.example.mynews.Models.Search.SearchResult;
import com.example.mynews.Models.TopStories.TopStories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.logging.HttpLoggingInterceptor;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NytStreams {



    public static Observable<TopStories> streamFetchTopstories() {


        TopStoriesServices nytServices = TopStoriesServices.retrofit.create(TopStoriesServices.class);
        return nytServices.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<TopStories> streamFetchBusiness() {

       BusinessServices nytServices = BusinessServices.retrofit.create(BusinessServices.class);
        return nytServices.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<MostPopular> streamFetchMostPopular() {

        MostPopularServices nytServices = MostPopularServices.retrofit.create(MostPopularServices.class);
        return nytServices.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

   // Map<String, Object> map = new HashMap<>();

    public static Observable<SearchResult> streamFetchSearch(Map<String, Object> map) {

        SearchServices nytServices = SearchServices.retrofit.create(SearchServices.class);
        return nytServices.getResults(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);




    }

}
