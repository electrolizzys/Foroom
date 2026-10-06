package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.imageAt
import com.example.foroom.Helper.imageSelectedAt
import com.example.foroom.Helper.imagesLoaded
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class CreateChatPage {
    private val createChatNavigationButton: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    private val chatNameInput: Matcher<View> = withId(R.id.chatNameInput)
    private val chatNameEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(chatNameInput))
    private val chatImageChooser: Matcher<View> = withId(R.id.chatImageChooser)
    private val createChatButton: Matcher<View> = withId(R.id.createChatButton)

    fun open() {
        createChatNavigationButton.waitUntilDisplayed().perform(click())
    }

    fun waitUntilDisplayed() {
        chatNameInput.waitUntilDisplayed()
        chatImageChooser.waitUntilDisplayed()
        createChatButton.waitUntilDisplayed()
    }

    fun enterChatName(name: String) {
        chatNameEditText.waitUntilDisplayed().perform(replaceText(name), closeSoftKeyboard())
    }

    fun waitForImagesLoaded() {
        chatImageChooser.waitUntil(allOf(isDisplayed(), imagesLoaded()), IMAGE_LOAD_TIMEOUT_MS)
    }

    fun selectImage(index: Int) {
        imageAt(R.id.chatImageChooser, index).waitUntilDisplayed().perform(click())
        chatImageChooser.waitUntil(imageSelectedAt(index))
    }

    fun tapCreateChat() {
        createChatButton.waitUntilDisplayed().perform(click())
    }

    companion object {
        private const val IMAGE_LOAD_TIMEOUT_MS = 15_000L
    }
}
