package com.example.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mynews.Models.MostPopular.MPResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.mynews.R.drawable;
import static com.example.mynews.R.id;

public class MostPopularViewHolder extends RecyclerView.ViewHolder {
    @Bind(id.fragment_page_item_picture) ImageView picture;
    @Bind(id.fragment_page_item_title) TextView title;
    @Bind(id.fragment_page_item_section) TextView section;
    @Bind(id.fragment_page_item_date) TextView date;

    private String url;
    private String mSection;
    private  String mDate;

    public MostPopularViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);

    }



    public void updateWithMostPopular(MPResult result) {

        if (result.getMedia() !=  null) {
            this.url = result.getMedia().get(0).getMediaMetadata().get(0).getUrl();


                Glide.with(itemView.getContext()).load(url).centerCrop().override(200, 200).into(picture);

            }else{
                Glide.with(itemView.getContext()).load(drawable.ic_broken_image_black_24dp).into(picture);
            }
        this.mSection = result.getSection();
        this.section.setText(mSection);

        this.mDate = formatDate(result.getPublishedDate());
        this.date.setText(mDate);
        this.title.setText(result.getTitle());
    }



    public static String formatDate(String dateToFormat) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
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
