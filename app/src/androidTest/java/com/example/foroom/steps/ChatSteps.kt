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
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import org.hamcrest.Matchers.allOf

/** The chat list and the create-chat screen. */
class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    fun checkChatListIsDisplayed(): ChatSteps = apply {
        chatsPage.chatsRecyclerView.waitUntilDisplayed()
        chatsPage.searchChatInput.waitUntilDisplayed()
    }

    fun searchChat(title: String): ChatSteps = apply {
        chatsPage.searchEditText.waitUntilDisplayed()
            .perform(replaceText(title), closeSoftKeyboard())
    }

    fun checkChatIsInList(title: String): ChatSteps = apply {
        chatsPage.chatTitle(title).waitUntilDisplayed()
    }

    fun openChat(title: String): ChatSteps = apply {
        chatsPage.openChatButton(title).waitUntilDisplayed().perform(click())
    }

    fun checkCreateChatScreenIsDisplayed(): ChatSteps = apply {
        createChatPage.chatNameInput.waitUntilDisplayed()
        createChatPage.chatImageChooser.waitUntilDisplayed()
        createChatPage.createChatButton.waitUntilDisplayed()
    }

    fun enterChatName(name: String): ChatSteps = apply {
        createChatPage.chatNameEditText.waitUntilDisplayed()
            .perform(replaceText(name), closeSoftKeyboard())
    }

    /** While loading, the chooser shows placeholders that ignore taps. */
    fun waitForChatImagesToLoad(): ChatSteps = apply {
        createChatPage.chatImageChooser
            .waitUntil(allOf(isDisplayed(), imagesLoaded()), Timeouts.IMAGE_LOAD_MS)
    }

    fun selectChatImage(index: Int): ChatSteps = apply {
        createChatPage.chatImageAt(index).waitUntilDisplayed().perform(click())
        createChatPage.chatImageChooser.waitUntil(imageSelectedAt(index))
    }

    fun tapCreateChat(): ChatSteps = apply {
        createChatPage.createChatButton.waitUntilDisplayed().perform(click())
    }
}
