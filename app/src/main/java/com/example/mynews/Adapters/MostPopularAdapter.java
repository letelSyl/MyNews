package com.example.mynews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.Controllers.Activities.ArticleActivity;
import com.example.mynews.Models.MostPopular.MPResult;
import com.example.mynews.R;
import com.example.mynews.Views.MostPopularViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularViewHolder> {

    // FOR DATA
    private List<MPResult> results;
    private Context context;

    //CONSTRUCTOR
    public MostPopularAdapter(List<MPResult> results){
        this.results = results;
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_page_item, parent, false);

        return new MostPopularViewHolder(view);

    }

    // UPDATE VIEW HOLDER WITH A RESULT

    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder viewHolder, int position) {

        final MPResult result = this.results.get(position);

        viewHolder.updateWithMostPopular(result);

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
