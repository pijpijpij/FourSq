package com.pij.foursq.ui.search;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.pij.foursq.Application;
import com.pij.foursq.DaggerApplicationComponent;
import com.pij.foursq.R;
import com.pij.foursq.net.EspressoThreadingModule;
import com.pij.foursq.net.MockNetConfigModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.utils.QueryParam;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.pij.espresso.Matchers.hasQueryParameters;
import static io.appflate.restmock.RESTMockServer.whenGET;
import static io.appflate.restmock.utils.RequestMatchers.pathContains;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> activity = new ActivityTestRule<>(SearchActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        Application application = (Application)targetContext.getApplicationContext();
        application.setComponent(DaggerApplicationComponent.builder()
                                                           .netConfig(new MockNetConfigModule(RESTMockServer.getUrl()))
                                                           .threadingConfig(new EspressoThreadingModule())
                                                           .build());
    }

    @After
    public void tearDown() throws Exception {
        RESTMockServer.reset();
    }

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
    public void test_SearchVenueChicagoNotFound_DisplaysVenueNotFound() throws InterruptedException {
        // given
        whenGET(allOf(pathContains("venues/search"),
                      hasQueryParameters(new QueryParam("near", "chicago")))).thenReturnFile(400,
                                                                                             "search/venue_chicago_not_found.json");
        activity.launchActivity(null);

        // when
        onView(withId(R.id.input)).perform(typeText("chicago"));
        // HACK
        Thread.sleep(2000);

        // then
        onView(withId(R.id.empty)).check(matches(isDisplayed()));
        onView(withId(R.id.empty)).check(matches(withText("No venue named 'chicago' can be found.")));
    }

    @Test
    public void test_SearchChicago_ShowsAtLeast25Venues() throws InterruptedException {
        // given
        whenGET(allOf(pathContains("venues/search"),
                      hasQueryParameters(new QueryParam("near", "chicago")))).thenReturnFile(200,
                                                                                             "search/venue_chicago_found.json");
        activity.launchActivity(null);

        // when
        onView(withId(R.id.input)).perform(typeText("chicago"));
        // HACK
        Thread.sleep(2000);

        // then
        onView(withId(R.id.empty)).check(matches(not(isDisplayed())));
        onView(withId(R.id.search_list)).check(matches(isDisplayed()));
        onView(withId(R.id.search_list)).perform(scrollTo(hasDescendant(withText("Live Wire Lounge"))));
        onView(withId(R.id.search_list)).perform(scrollTo(hasDescendant(withText("The 9 to 5!"))));
        onView(withId(R.id.search_list)).perform(scrollToPosition(25));
    }

}