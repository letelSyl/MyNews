package com.example.mynews.Utils;

import com.example.mynews.Models.TimeWire;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NytStreams {

    public static Observable<TimeWire> streamFetchTimeWire() {
        NytServices nytServices = NytServices.retrofit.create(NytServices.class);
        return nytServices.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
