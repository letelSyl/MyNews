package com.example.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mynews.Models.Search.Doc;
import com.example.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.fragment_page_item_picture) ImageView picture;
    @Bind(R.id.fragment_page_item_title) TextView title;
    @Bind(R.id.fragment_page_item_section) TextView section;
    @Bind(R.id.fragment_page_item_date) TextView date;

    private String url;
    private String mSection;
    private  String mDate;

    public SearchViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void updateWithSearch(Doc doc) {

        if (doc.getMultimedia()!= null && doc.getMultimedia().size() != 0) {
            this.url = doc.getMultimedia().get(0).getUrl();

            Glide.with(itemView.getContext()).load(url).centerCrop().override(250, 250).into(picture);
        }else {
            Glide.with(itemView.getContext()).load(R.drawable.ic_broken_image_black_24dp).centerCrop().override(250, 250).into(picture);
        }

        this.mSection = doc.getSectionName();
        this.section.setText(mSection);

        this.mDate = formatDate(doc.getPubDate());
        this.date.setText(mDate);
        this.title.setText(doc.getHeadline().getMain());
    }





    private static String formatDate(String dateToFormat) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSS");
        Date date = null;
        try {
            date = inFormat.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("dd/MM/yy");

        return outFormat.format(date);
    }
}
