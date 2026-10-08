package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.isDisplayedNow
import com.example.foroom.Helper.screenBounds
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.foroom.constants.SwipeSettings
import com.example.foroom.constants.Timeouts
import com.example.foroom.pages.ConversationPage
import org.hamcrest.Matchers.allOf

class ConversationSteps {
    private val page = ConversationPage()

    fun checkConversationIsOpen(title: String): ConversationSteps = apply {
        page.messagesRecyclerView.waitUntilDisplayed(Timeouts.HOME_SCREEN_MS)
        page.chatTitle.waitUntil(allOf(isDisplayed(), withText(title)))
    }

    fun typeMessage(text: String): ConversationSteps = apply {
        page.messageEditText.waitUntilDisplayed()
            .perform(replaceText(text), closeSoftKeyboard())
    }

    /** The send button stays disabled until the chat connects and while a message is sending. */
    fun tapSend(): ConversationSteps = apply {
        page.sendButton.waitUntil(allOf(isDisplayed(), isEnabled())).perform(click())
    }

    fun checkMessageIsDisplayed(text: String): ConversationSteps = apply {
        page.messageText(text).waitUntilDisplayed()
    }

    fun checkMessageSender(text: String, sender: String): ConversationSteps = apply {
        page.messageSender(text, sender).waitUntilDisplayed()
    }

    /**
     * Swipes towards older history until [text] is on screen. The number of swipes is bounded,
     * so a missing message fails the test instead of looping forever.
     */
    fun swipeToOlderMessagesUntilDisplayed(text: String): ConversationSteps = apply {
        val message = page.messageText(text)
        var swipes = 0
        while (!message.isDisplayedNow() && swipes < SwipeSettings.MAX_SWIPES) {
            swipeTowardsOlderMessages()
            swipes++
        }
        message.waitUntilDisplayed()
    }

    fun closeConversation(): ConversationSteps = apply {
        page.closeButton.waitUntilDisplayed().perform(click())
    }

    /**
     * The header overlaps the top of the message list and the input overlaps its bottom, so the
     * swipe stays between them. Newest messages are at the bottom, so dragging down reveals older
     * ones. Coordinates come from the views on the current device, not fixed pixels.
     */
    private fun swipeTowardsOlderMessages() {
        val visibleTop = page.chatHeader.screenBounds().bottom
        val visibleBottom = page.messageInput.screenBounds().top
        val margin = ((visibleBottom - visibleTop) * SwipeSettings.EDGE_MARGIN_RATIO).toInt()
        swiper(visibleTop + margin, visibleBottom - margin, SwipeSettings.DURATION_MS)
    }
}
