package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage
import org.hamcrest.Matchers.allOf

/** The profile page and the password/language bottom sheets opened from it. */
class ProfileSteps {
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun checkProfileIsDisplayed(): ProfileSteps = apply {
        profilePage.changePasswordItem.waitUntilDisplayed()
        profilePage.changeLanguageItem.waitUntilDisplayed()
        profilePage.signOutItem.waitUntilDisplayed()
    }

    fun tapChangePassword(): ProfileSteps = apply {
        profilePage.changePasswordItem.waitUntilDisplayed().perform(click())
    }

    fun tapChangeLanguage(): ProfileSteps = apply {
        profilePage.changeLanguageItem.waitUntilDisplayed().perform(click())
    }

    fun tapSignOut(): ProfileSteps = apply {
        profilePage.signOutItem.waitUntilDisplayed().perform(click())
    }

    fun checkChangePasswordSheetIsDisplayed(): ProfileSteps = apply {
        changePasswordPage.passwordInput.waitUntilDisplayed()
        changePasswordPage.repeatPasswordInput.waitUntilDisplayed()
        changePasswordPage.confirmButton.waitUntilDisplayed()
    }

    fun enterNewPassword(password: String): ProfileSteps = apply {
        changePasswordPage.passwordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatNewPassword(password: String): ProfileSteps = apply {
        changePasswordPage.repeatPasswordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun tapConfirm(): ProfileSteps = apply {
        changePasswordPage.confirmButton.waitUntilDisplayed().perform(click())
    }

    fun checkLanguageSheetIsDisplayed(): ProfileSteps = apply {
        changeLanguagePage.georgianButton.waitUntilDisplayed()
        changeLanguagePage.englishButton.waitUntilDisplayed()
    }

    fun selectGeorgian(): ProfileSteps = apply {
        changeLanguagePage.georgianButton.waitUntilDisplayed().perform(click())
    }

    fun selectEnglish(): ProfileSteps = apply {
        changeLanguagePage.englishButton.waitUntilDisplayed().perform(click())
    }

    // Changing the language recreates the activity, so these wait for the new screen's text.
    fun checkChangeLanguageLabel(label: String): ProfileSteps = apply {
        profilePage.changeLanguageTitle.waitUntil(allOf(isDisplayed(), withText(label)))
    }

    fun checkSignOutLabel(label: String): ProfileSteps = apply {
        profilePage.signOutTitle.waitUntil(allOf(isDisplayed(), withText(label)))
    }
}
