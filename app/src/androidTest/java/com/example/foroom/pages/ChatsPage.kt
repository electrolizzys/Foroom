package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.components.chat.ForoomChatCardView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

/** The home chat list. */
class ChatsPage {
    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)
    val searchChatInput: Matcher<View> = withId(R.id.searchChatInput)
    val searchEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(searchChatInput))

    fun chatTitle(title: String): Matcher<View> =
        allOf(withId(DesignR.id.chatTitleTextView), withText(title), isDescendantOfA(chatsRecyclerView))

    fun chatCard(title: String): Matcher<View> = allOf(
        isAssignableFrom(ForoomChatCardView::class.java),
        hasDescendant(allOf(withId(DesignR.id.chatTitleTextView), withText(title)))
    )

    /** The card's message button opens the chat. */
    fun openChatButton(title: String): Matcher<View> =
        allOf(withId(DesignR.id.sendMessageButton), isDescendantOfA(chatCard(title)))
}
