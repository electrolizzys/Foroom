package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher

/** The home container with its bottom navigation, shown after a successful login. */
class HomePage {
    val navBar: Matcher<View> = withId(R.id.navBar)
    val homeContainer: Matcher<View> = withId(R.id.homeContainer)

    val chatsNavigationButton: Matcher<View> = withId(R.id.homeNavigationChats)
    val createChatNavigationButton: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val profileNavigationButton: Matcher<View> = withId(R.id.homeNavigationProfile)
}
