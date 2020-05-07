package com.example.mynews.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.mynews.Models.Search.SearchResult;
import com.example.mynews.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.mynews.Utils.RetrofitBuilder.logging;

public class MyWorker extends Worker {


    private Disposable disposable;

    private static final String API_KEY = "56kl6ofJWQLLxl1hUvA7vWWLJGTC2z5p";

    private String queryTerm ="";

    private String categList ="";

    Map<String, Object> map = new HashMap<>();

    SharedPreferences prefs;

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        queryTerm = prefs.getString("query_terms", "");
        categList = prefs.getString("desk_art", "")
                + prefs.getString("desk_politics", "")
                + prefs.getString("desk_business", "")
                + prefs.getString("desk_sport", "")
                + prefs.getString("desk_entrepreneur", "")
                + prefs.getString("desk_travel", "");

        if (queryTerm.toString().isEmpty() || categList.toString().isEmpty()) {

            Toast.makeText(getApplicationContext(),
                    "Please, indicate a query term and at least one category",
                    Toast.LENGTH_LONG).show();

        } else {

            categList = "news_desk: ("
                    + categList
                    + ")";

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            month = month + 1;
            String mDay = "" + day;
            if (day < 10) {
                mDay = "0" + day;
            }
            String mMonth = "" + month;
            if (month < 10) {
                mMonth = "0" + month;
            }

            String yesterday = year + mMonth + mDay;

            map.put("q", queryTerm);
            map.put("begin_date", yesterday);
            map.put("end_date", yesterday);
            map.put("fq", categList);
            map.put("api-key", API_KEY);


            executeHttpRequestSearch();
        }

            return Result.success();

    }

        public void executeHttpRequestSearch() {



            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            this.disposable = NytStreams.streamFetchSearch(
                    map
            )
                    .subscribeWith(new DisposableObserver<SearchResult>() {



                        @Override
                        public void onNext(SearchResult response) {
                            Log.e("TAG", "On Next");

                            String message = "There are " + response.getResponse().getDocs()
                                    .size() + " articles that may interest you !";

                            sendNotif(message);

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



    private void sendNotif(String message){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("chanel_id","my chanel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notify=new NotificationCompat.Builder(getApplicationContext(),"chanel_id");
        notify.setContentTitle("My News");
        notify.setContentText(message);
        notify.setSmallIcon(R.drawable.ic_launcher_foreground);
        notify.setPriority(NotificationCompat.PRIORITY_MAX);


        Notification notif = notify.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(101,notif);



    }
}

