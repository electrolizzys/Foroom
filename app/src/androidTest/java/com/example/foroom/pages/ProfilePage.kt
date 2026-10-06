package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class ProfilePage {
    private val profileNavigationButton: Matcher<View> = withId(R.id.homeNavigationProfile)

    private val changePasswordItem: Matcher<View> = withId(R.id.changePasswordItem)
    private val changeLanguageItem: Matcher<View> = withId(R.id.changeLanguageItem)
    private val signOutItem: Matcher<View> = withId(R.id.signOutItem)

    // Every profile list item has its own listItemTextView, so scope the title to its item.
    private val changeLanguageTitle = titleOf(changeLanguageItem)
    private val signOutTitle = titleOf(signOutItem)

    fun open() {
        profileNavigationButton.waitUntilDisplayed().perform(click())
    }

    fun waitUntilDisplayed() {
        changePasswordItem.waitUntilDisplayed()
        changeLanguageItem.waitUntilDisplayed()
        signOutItem.waitUntilDisplayed()
    }

    fun tapChangePassword() {
        changePasswordItem.waitUntilDisplayed().perform(click())
    }

    fun tapChangeLanguage() {
        changeLanguageItem.waitUntilDisplayed().perform(click())
    }

    // Changing the language recreates the activity, so these wait for the new screen's text.
    fun waitForChangeLanguageTitle(title: String) {
        changeLanguageTitle.waitUntil(allOf(isDisplayed(), withText(title)))
    }

    fun waitForSignOutTitle(title: String) {
        signOutTitle.waitUntil(allOf(isDisplayed(), withText(title)))
    }

    private fun titleOf(item: Matcher<View>): Matcher<View> =
        allOf(withId(DesignR.id.listItemTextView), isDescendantOfA(item))
}
