package com.example.mynews.Controllers.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mynews.R;
import com.example.mynews.Adapters.PageAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    /*regarder butterknife
    *      api:retrofit + rxjava  */
    private Toolbar toolbar;
    private ViewPager pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3 - Configure toolbar
        this.configureToolbar();

        //3 - Configure ViewPager
        this.configureViewPagerAndTabs();

    }

    private void configureToolbar(){
        //1 - Get the toolbar view inside the activity layout
        this.toolbar = findViewById(R.id.include);
        //2 - Set the toolbar

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //1 - inflate the menu and add it to the toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //2 - Handle actions on menu items
        switch (item.getItemId()){
            case R.id.search:
                Intent Search = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(Search);
                return true;
            case R.id.notifications:
                Intent Notifications = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(Notifications);
                return true;
            case R.id.help:
                Intent Help = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(Help);
                return true;
            case R.id.about:
                Intent About = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(About);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        this.pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titlesPagesViewPager)));

        // 1 - Get TabLayout from layout
        this.tabs= findViewById(R.id.tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
