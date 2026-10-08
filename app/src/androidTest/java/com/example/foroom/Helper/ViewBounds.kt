package com.example.foroom.Helper

import android.graphics.Rect
import android.view.View
import androidx.test.espresso.Espresso.onView
import org.hamcrest.Matcher

/** The view's current position on screen, so gestures adapt to each device's layout. */
fun Matcher<View>.screenBounds(): Rect {
    val bounds = Rect()
    onView(this).check { view, noViewFound ->
        if (view == null) throw noViewFound
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        bounds.set(location[0], location[1], location[0] + view.width, location[1] + view.height)
    }
    return bounds
}
