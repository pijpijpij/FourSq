package com.pij.foursq.ui.search;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.pij.foursq.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> activity = new ActivityTestRule<>(SearchActivity.class, false, false);

    @Test
    public void test_InputFieldAvailable() {
        // given
        activity.launchActivity(null);

        // when

        // then
        onView(withId(R.id.input)).check(matches(allOf(isDisplayed(), isEnabled())));
    }

    @Test
    public void test_EmptyTextAvailable() {
        // given
        activity.launchActivity(null);

        // when

        // then
        onView(withId(R.id.empty)).check(matches(isDisplayed()))
                                  .check(matches(allOf(withHint("Please enter a venue name."), withText(""))));
    }

    @Test
    public void test_SearchC_VenueNotFound() throws InterruptedException {
        // given
        activity.launchActivity(null);

        // when
        onView(withId(R.id.input)).perform(typeText("C"));
        // HACK
        Thread.sleep(2000);

        // then
        onView(withId(R.id.empty)).check(matches(isDisplayed()));
        onView(withId(R.id.empty)).check(matches(withText("No venue named 'C' can be found.")));
    }

    //    @Test
    //    public void test_SearchChicago_ShowsVenues() throws InterruptedException {
    //        // given
    //        activity.launchActivity(null);
    //
    //        // when
    //        onView(withId(R.id.input)).perform(typeText("chicago"));
    //        // HACK
    //        Thread.sleep(10000);
    //
    //        // then
    //        onView(withId(R.id.empty)).check(matches(not(isDisplayed())));
    //        onView(withId(R.id.search_list)).check(matches(isDisplayed()));
    //        onView(withId(R.id.search_list)).perform(scrollTo(withText("Live Wire Lounge")));
    ////        onView(withId(R.id.search_list)).perform(scrollTo(withText("The 9 to 5!")));
    ////        onView(withId(R.id.search_list)).perform(scrollToPosition(25));
    //    }
}