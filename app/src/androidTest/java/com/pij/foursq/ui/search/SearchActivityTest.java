package com.pij.foursq.ui.search;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.pij.foursq.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> activity = new ActivityTestRule<>(SearchActivity.class, false, false);

    @Test
    public void tInputFieldAvailable() {
        // given
        activity.launchActivity(null);

        // when

        // then
        onView(withId(R.id.input)).check(matches(allOf(isDisplayed(), isEnabled())));
    }

}