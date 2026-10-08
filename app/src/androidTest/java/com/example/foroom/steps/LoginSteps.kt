package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.LoginPage
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not

class LoginSteps {
    private val page = LoginPage()

    fun checkLoginScreenIsDisplayed(): LoginSteps = apply {
        page.userNameInput.waitUntilDisplayed()
        page.passwordInput.waitUntilDisplayed()
        page.logInButton.waitUntilDisplayed()
        page.signUpButton.waitUntilDisplayed()
    }

    fun enterUserName(userName: String): LoginSteps = apply {
        page.userNameEditText.waitUntilDisplayed()
            .perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String): LoginSteps = apply {
        page.passwordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun tapLogIn(): LoginSteps = apply {
        page.logInButton.waitUntilDisplayed().perform(click())
    }

    fun tapSignUp(): LoginSteps = apply {
        page.signUpButton.waitUntilDisplayed().perform(click())
    }

    fun checkUserNameErrorIsDisplayed(): LoginSteps = apply {
        page.userNameError.waitUntil(allOf(isDisplayed(), withText(not(equalTo("")))))
    }

    fun checkPasswordErrorIsDisplayed(): LoginSteps = apply {
        page.passwordError.waitUntil(allOf(isDisplayed(), withText(not(equalTo("")))))
    }
}
