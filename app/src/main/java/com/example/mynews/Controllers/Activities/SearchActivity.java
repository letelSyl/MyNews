package com.example.mynews.Controllers.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mynews.R;

import java.util.Calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {


    @Bind(R.id.search_Include) Toolbar toolbar;

    @Bind(R.id.tv_date_start) TextView tvDateStart;
    @Bind(R.id.tv_date_end) TextView tvDateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        //1 - Configuring Toolbar
        this.configureToolbar();

        dateSelector(tvDateStart);
        dateSelector(tvDateEnd);




    }

    private void configureToolbar(){
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void dateSelector(final TextView tv){

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        tv.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                DatePickerDialog datePickerDialog= new DatePickerDialog(
                        SearchActivity.this,new DatePickerDialog.OnDateSetListener(){
                    @Override
                            public void onDateSet(DatePicker view, int year, int month, int day){

                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        tv.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }


        });

    }


}
