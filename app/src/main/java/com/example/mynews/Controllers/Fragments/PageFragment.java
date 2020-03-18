package com.example.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.Adapters.MostPopularAdapter;
import com.example.mynews.Adapters.TopStoriesAdapter;
import com.example.mynews.Models.MostPopular.MPResult;
import com.example.mynews.Models.MostPopular.MostPopular;
import com.example.mynews.Models.TopStories.TSResult;
import com.example.mynews.Models.TopStories.TopStories;
import com.example.mynews.R;
import com.example.mynews.Utils.NytStreams;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.mynews.Utils.RetrofitBuilder.logging;


// 1 - Implement Callbacks

public class PageFragment extends Fragment {

    //FOR DESIGN
    // Declare recycler view
    @Bind(R.id.fragment_page_recycler_view) RecyclerView recyclerView;


    // Create keys for our Bundle
    private static final String KEY_POSITION = "position";
    private int position;

    private static final int TOP_STORIES = 0;
    private static final int MOST_POPULAR = 1;
    private static final int BUSINESS = 2;

    // FOR DATA
    private Disposable disposable;
    // Declare list of tsResults & Adapter
    private List<TSResult> tsResults = new ArrayList<>();
    private List<MPResult> mpResults = new ArrayList<>();
    private TopStoriesAdapter tsAdapter;
    private MostPopularAdapter mpAdapter;


    public PageFragment() {
    }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int position) {

        // 2.1 Create new fragment
        PageFragment frag = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return (frag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Get layout of PageFragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, view);

        // 5 - Get data from Bundle (created in method newInstance)
        //
        position = getArguments().getInt(KEY_POSITION, -1);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

           switch (position) {

               case TOP_STORIES:
                   this.configureTSRecyclerView();
                   this.executeHttpRequestTopStories();
                   break;

               case MOST_POPULAR:
                   this.configureMPRecyclerView();
                   this.executeHttpRequestMostPopular();
                   break;

               case BUSINESS:
                   this.configureTSRecyclerView();
                   this.executeHttpRequestBusiness();
                   break;
          }
        return view;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // Configure RecyclerView, tsAdapter, LayoutManager & glue it together
    private void configureTSRecyclerView(){
        // Create tsAdapter passing the list of topStories
        this.tsAdapter = new TopStoriesAdapter(this.tsResults);
        //Attach the tsAdapter to the recyclerView to populate items
        this.recyclerView.setAdapter(this.tsAdapter);
        // Set LayoutManager to position the item
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureMPRecyclerView(){
        // Create tsAdapter passing the list of mostPopular
        this.mpAdapter = new MostPopularAdapter(this.mpResults);
        //Attach the tsAdapter to the recyclerView to populate items
        this.recyclerView.setAdapter(this.mpAdapter);
        // Set LayoutManager to position the item
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    // ------------------------------
    //  HTTP REQUEST (RxJava)
    // ------------------------------

    private void executeHttpRequestTopStories() {


       // logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        this.disposable = NytStreams.streamFetchTopstories()
                .subscribeWith(new DisposableObserver<TopStories>() {



            @Override
            public void onNext(TopStories topStories) {

                Log.e("TAG", "On Next");
                // 1.3 - Update UI with list of topStories
                updateUIWithTopStories(topStories.getResults());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }
            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    private void executeHttpRequestBusiness() {
        this.disposable = NytStreams.streamFetchBusiness()
                .subscribeWith(new DisposableObserver<TopStories>() {



            @Override
            public void onNext(TopStories topStories) {
                Log.e("TAG", "On Next");
                // 1.3 - Update UI with list of topStories
                updateUIWithTopStories(topStories.getResults());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }
            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    private void executeHttpRequestMostPopular() {
        this.disposable = NytStreams.streamFetchMostPopular().subscribeWith(new DisposableObserver<MostPopular>() {



            @Override
            public void onNext(MostPopular mostPopular) {
                Log.e("TAG", "On Next");
                // 1.3 - Update UI with list of mostPopular
                updateUIWithTMostPopular(mostPopular.getResults());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }
            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }




    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------



    private void updateUIWithTopStories(List<TSResult> resultsTS) {

        tsResults.addAll(resultsTS);
        tsAdapter.notifyDataSetChanged();
    }

    private void updateUIWithTMostPopular(List<MPResult> resultsMP) {

        mpResults.addAll(resultsMP);
        mpAdapter.notifyDataSetChanged();
    }
}