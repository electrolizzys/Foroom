package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilDisplayed
import org.hamcrest.Matcher

class ChangeLanguagePage {
    private val georgianButton: Matcher<View> = withId(R.id.languageButtonGeo)
    private val englishButton: Matcher<View> = withId(R.id.languageButtonEng)

    fun waitUntilDisplayed() {
        georgianButton.waitUntilDisplayed()
        englishButton.waitUntilDisplayed()
    }
    fun tapGeorgian() {
        georgianButton.waitUntilDisplayed().perform(click())
    }

    fun tapEnglish() {
        englishButton.waitUntilDisplayed().perform(click())
    }
}
