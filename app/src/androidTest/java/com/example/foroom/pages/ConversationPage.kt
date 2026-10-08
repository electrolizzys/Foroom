package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

/** An opened chat with its message history. */
class ConversationPage {
    val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)

    // The create-chat screen also has chatHeaderView and closeButton,
    // so both are scoped to this screen through the messagesRecyclerView sibling.
    val chatHeader: Matcher<View> =
        allOf(withId(R.id.chatHeaderView), hasSibling(messagesRecyclerView))
    val chatTitle: Matcher<View> =
        allOf(withId(DesignR.id.chatNameTextView), isDescendantOfA(chatHeader))
    val closeButton: Matcher<View> =
        allOf(withId(R.id.closeButton), hasSibling(messagesRecyclerView))

    val messageInput: Matcher<View> = withId(R.id.messageInput)
    val messageEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(messageInput))

    // Chat cards use the same sendMessageButton ID, so scope it to the message input.
    val sendButton: Matcher<View> =
        allOf(withId(R.id.sendMessageButton), isDescendantOfA(messageInput))

    fun messageText(text: String): Matcher<View> =
        allOf(withId(DesignR.id.messageTextView), withText(text), isDescendantOfA(messagesRecyclerView))

    /** The sender name shown in the same message bubble as [text]. */
    fun messageSender(text: String, sender: String): Matcher<View> = allOf(
        withId(DesignR.id.userNameTextView),
        withText(sender),
        isDescendantOfA(
            allOf(
                withId(DesignR.id.contentLinearLayout),
                hasDescendant(allOf(withId(DesignR.id.messageTextView), withText(text)))
            )
        )
    )
}
