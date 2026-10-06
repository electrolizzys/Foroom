package com.example.foroom.steps

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.RegistrationPage

object RegistrationSteps {
    private const val HOME_SCREEN_TIMEOUT_MS = 20_000L

    fun assertRegistrationScreenDisplayed() {
        RegistrationPage.waitUntilDisplayed()
    }

    fun register(userName: String, password: String, avatarIndex: Int) {
        RegistrationPage.enterUserName(userName)
        RegistrationPage.enterPassword(password)
        RegistrationPage.enterRepeatPassword(password)
        RegistrationPage.waitForAvatarsLoaded()
        RegistrationPage.selectAvatar(avatarIndex)
        RegistrationPage.tapSignUp()
    }
    fun assertRegistrationSucceeded() {
        withId(R.id.navBar).waitUntilDisplayed(HOME_SCREEN_TIMEOUT_MS)
        withId(R.id.homeContainer).waitUntilDisplayed()
    }
}
