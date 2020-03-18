package com.example.mynews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.Controllers.Activities.ArticleActivity;
import com.example.mynews.Controllers.Activities.SearchActivity;
import com.example.mynews.Controllers.Activities.SearchResultActivity;
import com.example.mynews.Models.TopStories.TSResult;
import com.example.mynews.R;
import com.example.mynews.Views.TopStoriesViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesViewHolder> {

    // FOR DATA
    private List<TSResult> results;
    private String url;
    private Context context;
    //CONSTRUCTOR
    public TopStoriesAdapter(List<TSResult> results){

        this.results = results;
    }

    @NonNull
    @Override
    public TopStoriesViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        context= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_page_item, parent, false);


        return new TopStoriesViewHolder(view);


    }

    // UPDATE VIEW HOLDER WITH A RESULT

    @Override
    public void onBindViewHolder(@NonNull TopStoriesViewHolder viewHolder, int position) {

       final TSResult result = this.results.get(position);

        viewHolder.updateWithTopStories(result);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent articleView = new Intent(context, ArticleActivity.class);

                articleView.putExtra("url", result.getUrl());

                context.startActivity(articleView);


            }
        });
    }

    // RETURN THE TOTAL COUNT OF THIS ITEMS IN THE LIST

    @Override
    public int getItemCount() {
        return this.results.size();
    }
}
