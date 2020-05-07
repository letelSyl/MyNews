package com.example.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mynews.Models.TopStories.TSResult;
import com.example.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.fragment_page_item_picture) ImageView picture;
    @Bind(R.id.fragment_page_item_title) TextView title;
    @Bind(R.id.fragment_page_item_section) TextView section;
    @Bind(R.id.fragment_page_item_date) TextView date;

    private String picUrl;
    private String mSection;
    private  String mDate;


    public TopStoriesViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void updateWithTopStories(TSResult result) {


        if (result.getMultimedia().size() != 0){
            this.picUrl = result.getMultimedia().get(0).getUrl();

            Glide.with(itemView.getContext()).load(picUrl).centerCrop().override(250, 250).into(picture);
        }else{
            Glide.with(itemView.getContext()).load(R.drawable.ic_broken_image_black_24dp).into(picture);
        }

        this.mSection = result.getSection() + " > " + result.getSubsection();
        this.section.setText(mSection);

        this.mDate = formatDate(result.getUpdatedDate());
        this.date.setText(mDate);
        this.title.setText(result.getTitle());

    }





    public static String formatDate(String dateToFormat) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SS:SS");
        Date date = null;
        try {
            date = inFormat.parse(dateToFormat);
        } catch (ParseException e) {
            return "wrong date format!";
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("dd/MM/yy");

        return outFormat.format(date);
    }
}
