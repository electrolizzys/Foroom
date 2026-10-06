package com.example.foroom.steps

import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    enum class Language(val changeLanguageLabel: String, val signOutLabel: String) {
        GEORGIAN("ენის შეცვლა", "გამოსვლა"),
        ENGLISH("Change Language", "Sign Out")
    }

    fun openProfile() {
        profilePage.open()
        profilePage.waitUntilDisplayed()
    }
    fun changePassword(newPassword: String) {
        profilePage.tapChangePassword()
        changePasswordPage.waitUntilDisplayed()
        changePasswordPage.enterPassword(newPassword)
        changePasswordPage.enterRepeatPassword(newPassword)
        changePasswordPage.tapConfirm()
    }

    fun selectLanguage(language: Language) {
        profilePage.tapChangeLanguage()
        changeLanguagePage.waitUntilDisplayed()
        when (language) {
            Language.GEORGIAN -> changeLanguagePage.tapGeorgian()
            Language.ENGLISH -> changeLanguagePage.tapEnglish()
        }
    }

    fun assertProfileShownIn(language: Language) {
        profilePage.waitForChangeLanguageTitle(language.changeLanguageLabel)
        profilePage.waitForSignOutTitle(language.signOutLabel)
    }
}
