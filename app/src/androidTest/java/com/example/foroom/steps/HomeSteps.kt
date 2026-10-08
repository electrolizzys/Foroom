package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.constants.Timeouts
import com.example.foroom.pages.HomePage

class HomeSteps {
    private val page = HomePage()

    /** Login and registration finish asynchronously, so this waits longer than usual. */
    fun checkHomeScreenIsDisplayed(): HomeSteps = apply {
        page.navBar.waitUntilDisplayed(Timeouts.HOME_SCREEN_MS)
        page.homeContainer.waitUntilDisplayed()
    }

    fun openChats(): HomeSteps = apply {
        page.chatsNavigationButton.waitUntilDisplayed().perform(click())
    }

    fun openCreateChat(): HomeSteps = apply {
        page.createChatNavigationButton.waitUntilDisplayed().perform(click())
    }

    fun openProfile(): HomeSteps = apply {
        page.profileNavigationButton.waitUntilDisplayed().perform(click())
    }
}
