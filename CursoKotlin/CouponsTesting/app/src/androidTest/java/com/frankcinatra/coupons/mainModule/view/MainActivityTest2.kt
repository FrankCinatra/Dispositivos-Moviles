package com.frankcinatra.coupons.mainModule.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.frankcinatra.coupons.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val materialButton = Espresso.onView(
            Matchers.allOf(
                withId(R.id.btnConsult), ViewMatchers.withText("Consultar"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton.perform(ViewActions.click())

        val materialButton2 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.btnCreate), ViewMatchers.withText("Crear cupón"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton2.perform(ViewActions.click())

        val snackbar =
            Espresso.onView(ViewMatchers.withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(ViewAssertions.matches(ViewMatchers.withText("El cupón debe tener entre 5 y 10 caracteres.")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}