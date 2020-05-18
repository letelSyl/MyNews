package com.example.mynews.Controllers.Activities;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.mynews.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NotificationsActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void notificationsActivityTest() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_include),
                                        2),
                                1),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Notifications"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.notifications_tv),
                        childAtPosition(
                                allOf(withId(R.id.linear_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("Trump"), closeSoftKeyboard());

        ViewInteraction appCompatAutoCompleteTextView2 = onView(
                allOf(withId(R.id.notifications_tv), withText("Trump"),
                        childAtPosition(
                                allOf(withId(R.id.linear_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView2.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.politics_checkBox), withText("Politics"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(R.id.notifications_checkBoxes),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.travel_checkBox), withText("Travel"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout3),
                                        childAtPosition(
                                                withId(R.id.notifications_checkBoxes),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.business_checkBox), withText("Business"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout2),
                                        childAtPosition(
                                                withId(R.id.notifications_checkBoxes),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.notifications_switch), withText("Enable notifications (once per day)"),
                        childAtPosition(
                                allOf(withId(R.id.linear_layout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        switch_.perform(click());

        ViewInteraction switch_2 = onView(
                allOf(withId(R.id.notifications_switch),
                        childAtPosition(
                                allOf(withId(R.id.linear_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                2),
                        isDisplayed()));
        switch_2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
