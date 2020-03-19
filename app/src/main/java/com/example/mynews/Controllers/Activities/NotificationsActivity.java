package com.example.mynews.Controllers.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mynews.Models.Search.SearchResult;
import com.example.mynews.R;
import com.example.mynews.Utils.NytStreams;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.mynews.Utils.RetrofitBuilder.logging;

public class NotificationsActivity extends AppCompatActivity {


    @Bind(R.id.notifications_toolbar) Toolbar toolbar;
    @Bind(R.id.notifications_tv) AutoCompleteTextView query_term;
    @Bind(R.id.notifications_switch) Switch notifications_switch;

    String queryTerm ="";

    String categList ="";
    //static Calendar yesterday;

    String apiKey = "56kl6ofJWQLLxl1hUvA7vWWLJGTC2z5p";

    private Disposable disposable;


   // private List<Doc> docsList = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ButterKnife.bind(this);

        //1 - Configuring Toolbar
        this.configureToolbar();

        notifications_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    queryTerm = String.valueOf(query_term.getText());

                    if (queryTerm.toString().isEmpty() || categList.toString().isEmpty()) {

                        Toast.makeText(getApplicationContext(),
                                "Please, indicate a query term and at least one category",
                                Toast.LENGTH_LONG).show();
                        notifications_switch.setChecked(false);

                    }else{

                        categList = "news_desk: (" + categList + ")";

                        map.put("q", queryTerm);
                        map.put("fq", categList);
                        map.put("api-key", apiKey);

                        executeHttpRequestSearch();


                        Toast.makeText(getApplicationContext(), "notifications ON ! ",  Toast.LENGTH_LONG).show();

                    }


                }else{

                    Toast.makeText(getApplicationContext(), "notifications OFF ! ",  Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }
    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
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

    private void executeHttpRequestSearch() {



        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        this.disposable = NytStreams.streamFetchSearch(
                // queryTerm,startDate, endDate, categList, apiKey
                map
        )
                .subscribeWith(new DisposableObserver<SearchResult>() {



                    @Override
                    public void onNext(SearchResult response) {
                        Log.e("TAG", "On Next");


                        Toast.makeText(getApplicationContext(), "There are " + response.getResponse().getDocs().size() + " articles that may interest you !", Toast.LENGTH_LONG).show();

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


      public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.search_art_checkBox:
                if (checked)
                    categList = categList + "\"art\" ";
                break;
            case R.id.search_politics_checkBox:
                if (checked)
                    categList = categList + "\"politics\" ";
                break;
            case R.id.search_business_checkBox:
                if (checked)
                    categList = categList + "\"business\" ";
                break;
            case R.id.search_sport_checkBox:
                if (checked)
                    categList = categList + "\"sport\" ";
                break;
            case R.id.search_entrepreneur_checkBox:
                if (checked)
                    categList = categList + "\"entrepreneur\" ";
                break;
            case R.id.search_travel_checkBox:
                if (checked)
                    categList = categList + "\"travel\"";
                break;
        }
    }

}
