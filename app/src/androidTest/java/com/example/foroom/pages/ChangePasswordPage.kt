package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

/** The Change Password bottom sheet opened from the profile page. */
class ChangePasswordPage {
    val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)

    // Child IDs repeat inside every Input, so each child is scoped to its parent input.
    val passwordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    // The Confirm button belongs to the shared action bottom sheet in design_system.
    val confirmButton: Matcher<View> = withId(DesignR.id.actionButton)
}
