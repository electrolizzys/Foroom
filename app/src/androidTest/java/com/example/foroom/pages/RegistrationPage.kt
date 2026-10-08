package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.imageAt
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class RegistrationPage {
    val userNameInput: Matcher<View> = withId(R.id.userNameInput)
    val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)

    // Child IDs repeat inside every Input, so each child is scoped to its parent input.
    val userNameEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    val passwordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    val avatarList: Matcher<View> = withId(R.id.listView)

    // signUpButton also exists on the login screen, so scope it to the registration layout.
    val signUpButton: Matcher<View> = allOf(withId(R.id.signUpButton), hasSibling(avatarList))

    fun avatarAt(index: Int): Matcher<View> = imageAt(R.id.listView, index)
}
