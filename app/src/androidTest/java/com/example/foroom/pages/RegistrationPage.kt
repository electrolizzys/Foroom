package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.util.TreeIterables
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilDisplayed
import com.example.shared.model.Image
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object RegistrationPage {
    private const val AVATAR_LOAD_TIMEOUT_MS = 15_000L

    private val userNameInput: Matcher<View> = withId(R.id.userNameInput)
    private val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    private val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)
    private val avatarList: Matcher<View> = withId(R.id.listView)
    private val userNameEditText = childOf(userNameInput, DesignR.id.inputEditText)
    private val passwordEditText = childOf(passwordInput, DesignR.id.inputEditText)
    private val repeatPasswordEditText = childOf(repeatPasswordInput, DesignR.id.inputEditText)
    private val signUpButton: Matcher<View> =
        allOf(withId(R.id.signUpButton), hasSibling(avatarList))

    fun waitUntilDisplayed() {
        userNameInput.waitUntilDisplayed()
        passwordInput.waitUntilDisplayed()
        repeatPasswordInput.waitUntilDisplayed()
        avatarList.waitUntilDisplayed()
        signUpButton.waitUntilDisplayed()
    }

    fun enterUserName(userName: String) {
        userNameEditText.waitUntilDisplayed().perform(replaceText(userName), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        passwordEditText.waitUntilDisplayed().perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String) {
        repeatPasswordEditText.waitUntilDisplayed()
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun waitForAvatarsLoaded() {
        avatarList.waitUntil(allOf(isDisplayed(), avatarsLoaded()), AVATAR_LOAD_TIMEOUT_MS)
    }

    fun selectAvatar(index: Int) {
        avatarAt(index).waitUntilDisplayed().perform(click())
        avatarList.waitUntil(avatarSelectedAt(index))
    }

    fun tapSignUp() {
        signUpButton.waitUntilDisplayed().perform(click())
    }

    private fun avatarAt(index: Int): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar at index $index in the avatar list")
            }

            override fun matchesSafely(item: ImageChooserItemView): Boolean {
                var parent = item.parent
                while (parent != null && parent !is ImageChooserListView) parent = parent.parent
                val list = parent as? ImageChooserListView ?: return false
                if (list.id != R.id.listView) return false

                val avatars = TreeIterables.breadthFirstViewTraversal(list)
                    .filterIsInstance<ImageChooserItemView>()
                return avatars.getOrNull(index) === item
            }
        }

    private fun childOf(input: Matcher<View>, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(input))

    private fun avatarsLoaded(): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with loaded, selectable images")
            }

            override fun matchesSafely(item: ImageChooserListView): Boolean =
                item.isChoosingEnabled && item.images.isNotEmpty() &&
                    item.images.none { image -> image.id == Image.BLANK_IMAGE_ID }
        }

    private fun avatarSelectedAt(index: Int): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with selected index $index")
            }

            override fun matchesSafely(item: ImageChooserListView): Boolean =
                item.selectedIndex == index
        }
}
