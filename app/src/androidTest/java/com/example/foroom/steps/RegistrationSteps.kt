package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.imageSelectedAt
import com.example.foroom.Helper.imagesLoaded
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.constants.Timeouts
import com.example.foroom.pages.RegistrationPage
import org.hamcrest.Matchers.allOf

class RegistrationSteps {
    private val page = RegistrationPage()

    fun checkRegistrationScreenIsDisplayed(): RegistrationSteps = apply {
        page.userNameInput.waitUntilDisplayed()
        page.passwordInput.waitUntilDisplayed()
        page.repeatPasswordInput.waitUntilDisplayed()
        page.avatarList.waitUntilDisplayed()
        page.signUpButton.waitUntilDisplayed()
    }

    fun enterUserName(userName: String): RegistrationSteps = apply {
        page.userNameEditText.waitUntilDisplayed()
            .perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String): RegistrationSteps = apply {
        page.passwordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String): RegistrationSteps = apply {
        page.repeatPasswordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    /** While loading, the list shows placeholders that ignore taps. */
    fun waitForAvatarsToLoad(): RegistrationSteps = apply {
        page.avatarList.waitUntil(allOf(isDisplayed(), imagesLoaded()), Timeouts.IMAGE_LOAD_MS)
    }

    fun selectAvatar(index: Int): RegistrationSteps = apply {
        page.avatarAt(index).waitUntilDisplayed().perform(click())
        page.avatarList.waitUntil(imageSelectedAt(index))
    }

    fun tapSignUp(): RegistrationSteps = apply {
        page.signUpButton.waitUntilDisplayed().perform(click())
    }
}
