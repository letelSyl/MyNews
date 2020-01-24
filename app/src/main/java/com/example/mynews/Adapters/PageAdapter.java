package com.example.mynews.Adapters;

import com.example.mynews.Controllers.Fragments.PageFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    // 1 - Array of titles that will be passed to PageFragment
    private String[] titles;

    // 2 - Default Constructor
    public PageAdapter(FragmentManager mgr, String[] titles) {
        super(mgr);

        this.titles = titles;
    }

    @Override
    public int getCount() {
        return (this.titles.length); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        // 4 - Page to return
        return (PageFragment.newInstance(position, this.titles[position]));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
