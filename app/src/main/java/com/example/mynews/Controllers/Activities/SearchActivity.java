package com.example.mynews.Controllers.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {


    @Bind(R.id.search_toolbar) Toolbar toolbar;
    @Bind(R.id.search_tv) AutoCompleteTextView query_term;
    @Bind(R.id.tv_date_start) TextView tvDateStart;
    @Bind(R.id.tv_date_end) TextView tvDateEnd;
    @Bind(R.id.search_button) Button searchButton;

    String queryTerm ="";

    String startDate ="";
    String endDate ="";

    String categList ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        //1 - Configuring Toolbar
        this.configureToolbar();

        dateSelector(tvDateStart);
        dateSelector(tvDateEnd);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryTerm = String.valueOf(query_term.getText());

                if (!tvDateStart.getText().toString().isEmpty()) {
                    startDate = formatDate(String.valueOf(tvDateStart.getText()));
                }

                if (!tvDateEnd.getText().toString().isEmpty()) {
                    endDate = formatDate(String.valueOf(tvDateEnd.getText()));
                }

                if (queryTerm.toString().isEmpty() || categList.toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(),
                            "Please, indicate a query term and at least one category",
                            Toast.LENGTH_LONG).show();

                }else{

                    Intent searchResult = new Intent(SearchActivity.this, SearchResultActivity.class);
                    searchResult.putExtra("queryTerm", queryTerm);
                    searchResult.putExtra("startDate", startDate);
                    searchResult.putExtra("endDate", endDate);
                    searchResult.putExtra("categList", categList);

                    startActivity(searchResult);
                }


            }
        });
    }


    private void configureToolbar() {
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void dateSelector(final TextView tv) {

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month = month + 1;
                        String mDay =""+day;
                        if(day<10){
                            mDay = "0"+day;
                        }
                        String mMonth =""+month;
                        if(month<10){
                            mMonth = "0"+month;
                        }
                        String date = mDay + "/" + mMonth + "/" + year;
                        tv.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();

            }


        });

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.search_art_checkBox:
                if (checked)
                    //categs.add("art");
                    categList = categList + "\"art\" ";
                break;
            case R.id.search_politics_checkBox:
                if (checked)
                    //categs.add("politics");
                    categList = categList + "\"politics\" ";
                break;
            case R.id.search_business_checkBox:
                if (checked)
                   // categs.add("business");
                    categList = categList + "\"business\" ";
                break;
            case R.id.search_sport_checkBox:
                if (checked)
                    //categs.add("sport");
                    categList = categList + "\"sport\" ";
                break;
            case R.id.search_entrepreneur_checkBox:
                if (checked)
                   // categs.add("entrepreneur");
                    categList = categList + "\"entrepreneur\" ";
                break;
            case R.id.search_travel_checkBox:
                if (checked)
                    //categs.add("travel");
                    categList = categList + "\"travel\"";
                break;
        }
    }
    private static String formatDate(String dateToFormat) {
        SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = null;
        try {
            date = inFormat.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");

        return outFormat.format(date);
    }






}
