package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.constants.Timeouts
import org.hamcrest.Matcher

/**
 * Polls until the view matching this matcher satisfies [condition], so asynchronous work
 * does not need fixed sleeps. Rethrows the last Espresso failure on timeout.
 */
fun Matcher<View>.waitUntil(
    condition: Matcher<View>,
    timeoutMs: Long = Timeouts.DEFAULT_MS
): ViewInteraction {
    val interaction = onView(this)
    val endTime = System.currentTimeMillis() + timeoutMs
    while (true) {
        try {
            return interaction.check(ViewAssertions.matches(condition))
        } catch (error: Throwable) {
            if (System.currentTimeMillis() >= endTime) throw error
            Thread.sleep(Timeouts.POLL_INTERVAL_MS)
        }
    }
}

fun Matcher<View>.waitUntilDisplayed(timeoutMs: Long = Timeouts.DEFAULT_MS): ViewInteraction =
    waitUntil(isDisplayed(), timeoutMs)

/** One immediate check without waiting; used where the caller does its own bounded retries. */
fun Matcher<View>.isDisplayedNow(): Boolean =
    try {
        onView(this).check(ViewAssertions.matches(isDisplayed()))
        true
    } catch (_: Throwable) {
        false
    }
