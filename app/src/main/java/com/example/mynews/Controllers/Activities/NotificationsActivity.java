package com.example.mynews.Controllers.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mynews.R;
import com.example.mynews.Utils.MyWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class NotificationsActivity extends AppCompatActivity {


    @Bind(R.id.notifications_toolbar) Toolbar toolbar;
    @Bind(R.id.notifications_tv) AutoCompleteTextView query_term;
    @Bind(R.id.notifications_switch) Switch notifications_switch;

    @Bind(R.id.art_checkBox) CheckBox art_checkBox;
    @Bind(R.id.politics_checkBox) CheckBox politics_checkBox;
    @Bind(R.id.business_checkBox) CheckBox business_checkBox;
    @Bind(R.id.entrepreneur_checkBox) CheckBox entrepreneur_checkBox;
    @Bind(R.id.sport_checkBox) CheckBox sport_checkBox;
    @Bind(R.id.travel_checkBox) CheckBox travel_checkBox;




    private Disposable disposable;

    SharedPreferences prefs;

    // private List<Doc> docsList = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ButterKnife.bind(this);

        //1 - Configuring Toolbar
        this.configureToolbar();

        art_checkBox.setChecked(prefs.getBoolean("art", false));
        business_checkBox.setChecked(prefs.getBoolean("business", false));
        politics_checkBox.setChecked(prefs.getBoolean("politics", false));
        sport_checkBox.setChecked(prefs.getBoolean("sport", false));
        entrepreneur_checkBox.setChecked(prefs.getBoolean("entrepreneur", false));
        travel_checkBox.setChecked(prefs.getBoolean("travel", false));


        query_term.setText(prefs.getString("query_terms", ""));

        notifications_switch.setChecked(prefs.getBoolean("switch_checked", false));

        notifications_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WorkManager mWorkManager = WorkManager.getInstance();
                if (isChecked){


                    prefs.edit().putString("query_terms", String.valueOf(query_term.getText())).apply();




                        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 1, TimeUnit.DAYS).build();

                        mWorkManager.enqueue(workRequest);

                        Toast.makeText(getApplicationContext(), "notifications ON ! ",  Toast.LENGTH_LONG).show();

                        prefs.edit().putBoolean("switch_checked",true).apply();





                }else{
                    mWorkManager.cancelAllWork();

                    prefs.edit().putBoolean("switch_checked",false).apply();
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




      public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.art_checkBox:
                if (checked){
                    prefs.edit().putString("desk_art","\"art\" ").apply();
                    prefs.edit().putBoolean("art", true).apply();
                }else{
                    prefs.edit().putBoolean("art", false).apply();
                    prefs.edit().remove("desk_art").apply();
                }

                break;
            case R.id.politics_checkBox:
                if (checked) {
                    prefs.edit().putString("desk_politics","\"politics\" ").apply();
                    prefs.edit().putBoolean("politics", true).apply();
                }else{
                    prefs.edit().putBoolean("politics", false).apply();
                    prefs.edit().remove("desk_politics").apply();
                }

                break;
            case R.id.business_checkBox:
                if (checked) {
                    prefs.edit().putString("desk_business","\"business\" ").apply();
                    prefs.edit().putBoolean("business", true).apply();
                }else{

                    prefs.edit().putBoolean("business", false).apply();
                    prefs.edit().remove("desk_business").apply();
                }
                break;
            case R.id.sport_checkBox:
                if (checked) {
                    prefs.edit().putString("desk_sport","\"sport\" ").apply();
                    prefs.edit().putBoolean("sport", true).apply();
                }else{
                    prefs.edit().putBoolean("sport", false).apply();
                    prefs.edit().remove("desk_sport").apply();
                }
                break;
            case R.id.entrepreneur_checkBox:
                if (checked){
                    prefs.edit().putString("desk_entrepreneur","\"entrepreneur\" ").apply();
                    prefs.edit().putBoolean("entrepreneur", true).apply();
                }else{
                    prefs.edit().putBoolean("entrepreneur", false).apply();
                    prefs.edit().remove("desk_entrepreneur").apply();
                }
                break;
            case R.id.travel_checkBox:
                if (checked){
                    prefs.edit().putString("desk_travel","\"travel\"").apply();
                    prefs.edit().putBoolean("travel", true).apply();
                }else{
                    prefs.edit().putBoolean("travel", false).apply();
                    prefs.edit().remove("desk_travel").apply();
                }
                break;
            default:
                break;
        }
    }


}
