package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR
class ChangePasswordPage {
    private val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    private val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)

    private val passwordEditText = childOf(passwordInput, DesignR.id.inputEditText)
    private val repeatPasswordEditText = childOf(repeatPasswordInput, DesignR.id.inputEditText)

    private val confirmButton: Matcher<View> = withId(DesignR.id.actionButton)

    fun waitUntilDisplayed() {
        passwordInput.waitUntilDisplayed()
        repeatPasswordInput.waitUntilDisplayed()
        confirmButton.waitUntilDisplayed()
    }

    fun enterPassword(password: String) {
        passwordEditText.waitUntilDisplayed().perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String) {
        repeatPasswordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun tapConfirm() {
        confirmButton.waitUntilDisplayed().perform(click())
    }
    private fun childOf(input: Matcher<View>, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(input))
}
