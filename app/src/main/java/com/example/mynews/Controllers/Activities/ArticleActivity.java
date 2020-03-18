package com.example.mynews.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mynews.R;

public class ArticleActivity extends AppCompatActivity {

    @Bind(R.id.about_include) Toolbar toolbar;
    @Bind(R.id.webView) WebView webView;

    private String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ButterKnife.bind(this);

        //1 - Configuring Toolbar
        this.configureToolbar();

        Intent intent = getIntent();
        url = intent.getStringExtra("url");


        webView.setWebViewClient(new WebViewClient());
      //  webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

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
