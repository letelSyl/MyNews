package com.example.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynews.Models.TimeWire;
import com.example.mynews.R;
import com.example.mynews.Utils.NytStreams;

import androidx.fragment.app.Fragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

// 1 - Implement Callbacks

public class PageFragment extends Fragment {


    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION = "position";
    private static final String KEY_TITLE = "title";
    private int position;

    private static final int TOP_STORIES = 0;
    @Bind(R.id.fragment_page_title)
    TextView textView;

    private Disposable disposable;


    public PageFragment() {
    }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int position, String title) {

        // 2.1 Create new fragment
        PageFragment frag = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);
        args.putString(KEY_TITLE, title);
        frag.setArguments(args);

        return (frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // 3 - Get layout of PageFragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ButterKnife.bind(this, view);


        // 5 - Get data from Bundle (created in method newInstance)
        //
        position = getArguments().getInt(KEY_POSITION, -1);
        String title = getArguments().getString(KEY_TITLE, null);
        switch (position) {

            case TOP_STORIES:
                this.executeHttpRequestWithRetrofit();
                break;
            default:
                this.textView.setText(title + "\n Work in progress...");

        }

        return view;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTIONS
    // -----------------
 /*   @OnClick(R.id.fragment_page_title)
    public void submit(View view) {
        // 2 - call the stream
    }*/

    // ------------------------------
    //  HTTP REQUEST (RxJava)
    // ------------------------------

    // 4 - Execute HTTP request and update UI
    private void executeHttpRequestWithRetrofit() {
        //1.1 - Update UI
        this.updateUIWhenStartingHTTPRequest();
        this.disposable = NytStreams.streamFetchTimeWire().subscribeWith(new
                                                                                 DisposableObserver<TimeWire>() {


                                                                                     @Override
                                                                                     public void onNext(TimeWire timeWire) {
                                                                                         Log.e("TAG", "On Next");
                                                                                         // 1.3 - Update UI with list of users
                                                                                         updateUIWithListOfTimeWire(timeWire);
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

    private void updateUIWhenStartingHTTPRequest() {

        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response) {

        this.textView.setText(response);
    }

    private void updateUIWithListOfTimeWire(TimeWire timeWire) {

        String stringBuilder = timeWire.getResults().toString();
        updateUIWhenStopingHTTPRequest(stringBuilder);
    }
}