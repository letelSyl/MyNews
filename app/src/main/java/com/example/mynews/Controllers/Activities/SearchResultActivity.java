package com.example.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mynews.Adapters.SearchAdapter;
import com.example.mynews.Models.Search.Doc;
import com.example.mynews.Models.Search.SearchResult;
import com.example.mynews.R;
import com.example.mynews.Utils.NytStreams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.mynews.Utils.RetrofitBuilder.logging;


public class SearchResultActivity extends AppCompatActivity {


    @Bind(R.id.search_result_toolbar) Toolbar toolbar;
    @Bind(R.id.fragment_page_recycler_view) RecyclerView recyclerView;


    private static final String API_KEY = "56kl6ofJWQLLxl1hUvA7vWWLJGTC2z5p";

    private Disposable disposable;

    private List<Doc> docsList = new ArrayList<>();

    Map<String, Object> map = new HashMap<>();

    private SearchAdapter searchAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_search_result);

        ButterKnife.bind(this);

        this.configureToolbar();

        Intent intent = getIntent();

            if (intent.hasExtra("queryTerm")){
                String queryTerm = intent.getStringExtra("queryTerm");
                map.put("q", queryTerm);
            }
            if (!intent.getStringExtra("startDate").equals("")){
                String startDate = intent.getStringExtra("startDate");
                map.put("begin_date", startDate);
            }
            if (!intent.getStringExtra("endDate").equals("")){
                String endDate = intent.getStringExtra("endDate");
                map.put("end_date", endDate);
            }
            if (intent.hasExtra("categList")){
                String categList = "news_desk: (" + intent.getStringExtra("categList") + ")";
                map.put("fq", categList);
            }
            map.put("api-key", API_KEY);

        configureTSRecyclerView();
        executeHttpRequestSearch();





    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }


    private void configureToolbar() {
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureTSRecyclerView(){
        // Create tsAdapter passing the list of topStories
        this.searchAdapter = new SearchAdapter(this.docsList);
        //Attach the tsAdapter to the recyclerView to populate items
        this.recyclerView.setAdapter(this.searchAdapter);
        // Set LayoutManager to position the item
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void executeHttpRequestSearch() {



        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        this.disposable = NytStreams.streamFetchSearch(map).subscribeWith(new DisposableObserver<SearchResult>() {



            @Override
            public void onNext(SearchResult response) {
                    Log.e("TAG", "On Next");
                // 1.3 - Update UI with list of topStories
                updateUIWithSearch(response.getResponse().getDocs());
                if (response.getResponse().getDocs().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "No article found !",
                            Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error : " + Log.getStackTraceString(e));
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

    private void updateUIWithSearch(List<Doc> docs) {


        docsList.addAll(docs);
        searchAdapter.notifyDataSetChanged();
    }
}
