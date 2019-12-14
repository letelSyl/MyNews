package com.example.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynews.R;

import androidx.fragment.app.Fragment;
import butterknife.Bind;
import butterknife.ButterKnife;


public class PageFragment extends Fragment {

    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_TITLE ="title";
    private int position;

    @Bind(R.id.fragment_page_title) TextView textView;


    public PageFragment() { }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(int position, String title) {

        // 2.1 Create new fragment
        PageFragment frag = new PageFragment();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(KEY_TITLE, title);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        ButterKnife.bind(this,result);


        // 5 - Get data from Bundle (created in method newInstance)
        position = getArguments().getInt(KEY_POSITION, -1);
        String title = getArguments().getString(KEY_TITLE, null);

        // 6 - Update widgets with it
        textView.setText(title+"\n Work in progress ...");

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }

}