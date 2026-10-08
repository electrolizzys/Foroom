package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class ProfilePage {
    val changePasswordItem: Matcher<View> = withId(R.id.changePasswordItem)
    val changeLanguageItem: Matcher<View> = withId(R.id.changeLanguageItem)
    val signOutItem: Matcher<View> = withId(R.id.signOutItem)

    // Every profile list item has its own listItemTextView, so scope each title to its item.
    val changeLanguageTitle: Matcher<View> =
        allOf(withId(DesignR.id.listItemTextView), isDescendantOfA(changeLanguageItem))
    val signOutTitle: Matcher<View> =
        allOf(withId(DesignR.id.listItemTextView), isDescendantOfA(signOutItem))
}
