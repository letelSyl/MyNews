package com.example.mynews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.Controllers.Activities.ArticleActivity;
import com.example.mynews.Controllers.Activities.ArticleSearchActivity;
import com.example.mynews.Models.MostPopular.MPResult;
import com.example.mynews.Models.Search.Doc;
import com.example.mynews.R;
import com.example.mynews.Views.MostPopularViewHolder;
import com.example.mynews.Views.SearchViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    // FOR DATA
    private List<Doc> docs;
    private Context context;

    //CONSTRUCTOR
    public SearchAdapter(List<Doc> docs){
        this.docs = docs;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_page_item, parent, false);

        return new SearchViewHolder(view);

    }

    // UPDATE VIEW HOLDER WITH A RESULT

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder viewHolder, int position) {

        final Doc result = this.docs.get(position);

        viewHolder.updateWithSearch(result);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent articleView = new Intent(context, ArticleSearchActivity.class);

                articleView.putExtra("url", result.getWebUrl());

                context.startActivity(articleView);


            }
        });
    }

    // RETURN THE TOTAL COUNT OF THIS ITEMS IN THE LIST

    @Override
    public int getItemCount() {
        return this.docs.size();
    }
}
