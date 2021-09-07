package com.android.distilled

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.distilled.ui.ShowsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShowsActivityEspressoTest {

    @get:Rule
    var activityRule: ActivityTestRule<ShowsActivity> = ActivityTestRule(ShowsActivity::class.java)

    @Test
    fun validEmailPassword_Case() {
        Thread.sleep(3000)
        onView(withId(R.id.rvShows))
            .check(matches(isDisplayed()))
        Thread.sleep(3000)
    }


    @Test
    fun menuClickText() {
        onView(withId(R.id.sortBy))
            .perform(click())
        Thread.sleep(3000)
    }

    @Test
    fun optionClick_Alphabetical_Descending() {
        onView(withId(R.id.sortBy))
            .perform(click())

        Thread.sleep(1500)

        onView(withText("Alphabetical descending"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())
        Thread.sleep(1500)

        onView(withId(R.id.rvShows))
            .perform(swipeUp())

        Thread.sleep(1500)

        onView(withId(R.id.rvShows))
            .perform(swipeDown())

        Thread.sleep(3000)
    }
}