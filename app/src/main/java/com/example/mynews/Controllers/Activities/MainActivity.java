package com.example.mynews.Controllers.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mynews.Adapters.PageAdapter;
import com.example.mynews.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.main_include)
    Toolbar toolbar;
    @Bind(R.id.activity_main_viewpager)
    ViewPager pager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    private static final int TOP_STORIES = 0;
    private static final int MOST_POPULAR = 1;
    private static final int BUSINESS = 2;


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //3 - Configure toolbar
        this.configureToolbar();

        //3 - Configure ViewPager
        this.configureViewPagerAndTabs();


        this.configureDrawerLayout();

        this.configureNavigationView();

    }

    private void configureToolbar() {
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
        if (itemSwitch(item.getItemId())) {

            return true;

        }else {
            return super.onOptionsItemSelected(item);
        }

    }

    private void configureViewPagerAndTabs() {
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titlesPagesViewPager)));

        //  Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onBackPressed() {
// 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        itemSwitch(item.getItemId());

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private boolean itemSwitch(int itemId) {

        switch (itemId) {
            case R.id.drawer_top_stories:
                pager.setCurrentItem(TOP_STORIES);
                return true;

            case R.id.drawer_most_popular:
                pager.setCurrentItem(MOST_POPULAR);
                return true;

            case R.id.drawer_Business:
                pager.setCurrentItem(BUSINESS);
                return true;

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
                return false;
        }
    }
}
