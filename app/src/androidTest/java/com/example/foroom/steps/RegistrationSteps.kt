package com.example.foroom.steps

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {
    private val registrationPage = RegistrationPage()

    fun assertRegistrationScreenDisplayed() {
        registrationPage.waitUntilDisplayed()
    }

    fun register(userName: String, password: String, avatarIndex: Int) {
        registrationPage.enterUserName(userName)
        registrationPage.enterPassword(password)
        registrationPage.enterRepeatPassword(password)
        registrationPage.waitForAvatarsLoaded()
        registrationPage.selectAvatar(avatarIndex)
        registrationPage.tapSignUp()
    }
    fun assertRegistrationSucceeded() {
        withId(R.id.navBar).waitUntilDisplayed(HOME_SCREEN_TIMEOUT_MS)
        withId(R.id.homeContainer).waitUntilDisplayed()
    }

    companion object {
        private const val HOME_SCREEN_TIMEOUT_MS = 20_000L
    }
}
