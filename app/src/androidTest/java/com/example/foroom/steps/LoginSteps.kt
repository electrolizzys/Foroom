package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

object LoginSteps {

    fun assertLoginScreenDisplayed() {
        LoginPage.waitUntilDisplayed()
    }

    fun logIn(userName: String, password: String) {
        LoginPage.enterUserName(userName)
        LoginPage.enterPassword(password)
        LoginPage.tapLogIn()
    }

    fun openRegistration() {
        LoginPage.tapSignUp()
    }

    fun assertUserNameErrorDisplayed() {
        LoginPage.waitForUserNameError()
    }

    fun assertPasswordErrorDisplayed() {
        LoginPage.waitForPasswordError()
    }
}
