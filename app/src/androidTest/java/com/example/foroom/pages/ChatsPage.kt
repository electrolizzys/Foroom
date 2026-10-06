package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
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

/** The home chat list, plus the chat screen opened from it. */
class ChatsPage {
    // Home screen
    private val navBar: Matcher<View> = withId(R.id.navBar)
    private val homeContainer: Matcher<View> = withId(R.id.homeContainer)

    // Chat list
    private val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)
    private val searchChatInput: Matcher<View> = withId(R.id.searchChatInput)
    private val searchEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(searchChatInput))

    // Opened chat. The create-chat screen also has chatHeaderView and closeButton,
    // so both are scoped to the chat screen through its messagesRecyclerView sibling.
    private val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)
    private val openedChatHeader: Matcher<View> =
        allOf(withId(R.id.chatHeaderView), hasSibling(messagesRecyclerView))
    private val openedChatName: Matcher<View> =
        allOf(withId(DesignR.id.chatNameTextView), isDescendantOfA(openedChatHeader))
    private val closeChatButton: Matcher<View> =
        allOf(withId(R.id.closeButton), hasSibling(messagesRecyclerView))

    fun waitForHomeScreen() {
        navBar.waitUntilDisplayed(HOME_SCREEN_TIMEOUT_MS)
        homeContainer.waitUntilDisplayed()
    }

    fun waitForChatList() {
        chatsRecyclerView.waitUntilDisplayed()
        searchChatInput.waitUntilDisplayed()
    }

    fun search(text: String) {
        searchEditText.waitUntilDisplayed().perform(replaceText(text), closeSoftKeyboard())
    }

    fun waitForChatCard(name: String) {
        allOf(withId(DesignR.id.chatTitleTextView), withText(name), isDescendantOfA(chatsRecyclerView))
            .waitUntilDisplayed()
    }

    fun waitForOpenedChat(name: String) {
        messagesRecyclerView.waitUntilDisplayed(HOME_SCREEN_TIMEOUT_MS)
        openedChatName.waitUntil(allOf(isDisplayed(), withText(name)))
    }

    fun tapCloseChat() {
        closeChatButton.waitUntilDisplayed().perform(click())
    }

    companion object {
        private const val HOME_SCREEN_TIMEOUT_MS = 20_000L
    }
}
