package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

const val DEFAULT_WAIT_TIMEOUT_MS = 10_000L
private const val POLL_INTERVAL_MS = 100L

fun Matcher<View>.waitUntil(
    condition: Matcher<View>,
    timeoutMs: Long = DEFAULT_WAIT_TIMEOUT_MS
): ViewInteraction {
    val interaction = onView(this)
    val endTime = System.currentTimeMillis() + timeoutMs
    while (true) {
        try {
            return interaction.check(ViewAssertions.matches(condition))
        } catch (error: Throwable) {
            if (System.currentTimeMillis() >= endTime) throw error
            Thread.sleep(POLL_INTERVAL_MS)
        }
    }
}


fun Matcher<View>.waitUntilDisplayed(timeoutMs: Long = DEFAULT_WAIT_TIMEOUT_MS): ViewInteraction =
    waitUntil(isDisplayed(), timeoutMs)
