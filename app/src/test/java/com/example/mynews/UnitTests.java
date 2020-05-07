package com.example.mynews;

import com.example.mynews.Controllers.Activities.SearchActivity;
import com.example.mynews.Views.MostPopularViewHolder;
import com.example.mynews.Views.TopStoriesViewHolder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    @Test
    public void SearchActivityFormatDateTest() {

        String searchDate = "14/04/2020";
        String searchWrongDate = "2020-04-14";
        String searchWrongDate2 = "2020-03-20T22:05:12-05:36";


        assertEquals("20200414", SearchActivity.formatDate(searchDate));
        assertEquals("wrong date format!", SearchActivity.formatDate(searchWrongDate));
        assertEquals("wrong date format!", SearchActivity.formatDate(searchWrongDate2));
    }

    @Test
    public void MostPopularViewHolderFormatDateTest() {

        String mostPopDate = "2019-01-01";
        String mostPopWrongDate = "01/01/2019";
        String mostPopWrongDate2 = "2020-03-20T22:05:12-05:36";

        assertEquals("01/01/19", MostPopularViewHolder.formatDate(mostPopDate));
        assertEquals("wrong date format!", MostPopularViewHolder.formatDate(mostPopWrongDate));
        assertEquals("20/03/20", MostPopularViewHolder.formatDate(mostPopWrongDate2));
    }

    @Test
    public void TopStoriesViewHolderFormatDateTest(){

        String topStoriesDate = "2020-03-20T22:05:12-05:36";
        String topStoriesWrongDate = "20/03/2020";
        String topStoriesWrongDate2 = "2020-03-20";

        assertEquals("20/03/20", TopStoriesViewHolder.formatDate(topStoriesDate));
        assertEquals("wrong date format!", TopStoriesViewHolder.formatDate(topStoriesWrongDate));
        assertEquals("wrong date format!", TopStoriesViewHolder.formatDate(topStoriesWrongDate2));

    }

}