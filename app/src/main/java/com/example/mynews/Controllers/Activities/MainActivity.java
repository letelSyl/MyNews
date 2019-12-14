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
import butterknife.ButterKnife;
import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

//    retrofit + java rx


    @Bind(R.id.main_include) Toolbar toolbar;
    @Bind(R.id.activity_main_viewpager) ViewPager pager;
    @Bind(R.id.tabs) TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //3 - Configure toolbar
        this.configureToolbar();

        //3 - Configure ViewPager
        this.configureViewPagerAndTabs();

    }

    private void configureToolbar(){
        // Set the toolbar
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
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titlesPagesViewPager)));

        //  Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
