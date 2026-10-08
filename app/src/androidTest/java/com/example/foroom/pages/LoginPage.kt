package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import com.example.design_system.R as DesignR

class LoginPage {
    private val userNameInput: Matcher<View> = withId(R.id.userNameInput)
    private val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    private val userNameEditText = childOf(userNameInput, DesignR.id.inputEditText)
    private val passwordEditText = childOf(passwordInput, DesignR.id.inputEditText)
    private val userNameDescription = childOf(userNameInput, DesignR.id.descriptionTextView)
    private val passwordDescription = childOf(passwordInput, DesignR.id.descriptionTextView)

    private val logInButton: Matcher<View> = withId(R.id.logInButton)
    private val signUpButton: Matcher<View> =
        allOf(withId(R.id.signUpButton), hasSibling(logInButton))

    private val shownError: Matcher<View> = allOf(isDisplayed(), withText(not(equalTo(""))))

    fun waitUntilDisplayed() {
        userNameInput.waitUntilDisplayed()
        passwordInput.waitUntilDisplayed()
        logInButton.waitUntilDisplayed()
        signUpButton.waitUntilDisplayed()
    }

    fun enterUserName(userName: String) {
        userNameEditText.waitUntilDisplayed().perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        passwordEditText.waitUntilDisplayed().perform(replaceText(password), closeSoftKeyboard())
    }

    fun tapLogIn() {
        logInButton.waitUntilDisplayed().perform(click())
    }

    fun tapSignUp() {
        signUpButton.waitUntilDisplayed().perform(click())
    }

    fun waitForUserNameError() {
        userNameDescription.waitUntil(shownError)
    }

    fun waitForPasswordError() {
        passwordDescription.waitUntil(shownError)
    }
    private fun childOf(input: Matcher<View>, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(input))
}
