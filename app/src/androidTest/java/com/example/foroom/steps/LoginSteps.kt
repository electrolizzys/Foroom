package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

class LoginSteps {
    private val loginPage = LoginPage()

    fun assertLoginScreenDisplayed() {
        loginPage.waitUntilDisplayed()
    }

    fun logIn(userName: String, password: String) {
        loginPage.enterUserName(userName)
        loginPage.enterPassword(password)
        loginPage.tapLogIn()
    }

    fun openRegistration() {
        loginPage.tapSignUp()
    }

    fun assertUserNameErrorDisplayed() {
        loginPage.waitForUserNameError()
    }

    fun assertPasswordErrorDisplayed() {
        loginPage.waitForPasswordError()
    }
}
