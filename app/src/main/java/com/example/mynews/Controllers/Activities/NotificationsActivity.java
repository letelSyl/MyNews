package com.example.mynews.Controllers.Activities;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.mynews.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {


    @Bind(R.id.notifications_Include)
    Toolbar toolbar;
    @Bind(R.id.notifications_tv)
    AutoCompleteTextView query_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ButterKnife.bind(this);

        //1 - Configuring Toolbar
        this.configureToolbar();
    }

    private void configureToolbar() {
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
