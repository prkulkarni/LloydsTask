package com.example.lloydstask.ui

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lloydstask.ui.activities.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
open class BaseActivityTest {

    @get:Rule
    val activityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    fun verifyViewWithIdIsDisplayed(resId: Int) {
        onView(withId(resId)).check(matches(isDisplayed()))
    }

    fun verifyViewWithIdIsClickable(resId: Int) {
        onView(withId(resId)).check(matches(isClickable()))
    }

    fun clickOnId(resId: Int) {
        onView(withId(resId)).perform(click())
    }

    fun verifyViewWithIdIsDisplayedAtPosition(resId: Int, position: Int) {
        onView(withIndex(withId(resId), position)).check(matches(isDisplayed()));
    }

    fun clickOnIdAtPosition(resId: Int, position: Int) {
        onView(withIndex(withId(resId), position)).perform(click());
    }

    private fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var currentIndex: Int = 0;

            override fun describeTo(description: Description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index;
            }
        }
    }
}