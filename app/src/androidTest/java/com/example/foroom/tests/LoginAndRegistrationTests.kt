package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.Helper.uniqueSuffix
import com.example.foroom.constants.Accounts
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.HomeSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule
    val rules: RuleChain = RuleChain
        .outerRule(ClearSessionRule())
        .around(ActivityScenarioRule(ForoomActivity::class.java))

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()
    private val homeSteps = HomeSteps()

    @Test
    fun validUserNameAndInvalidPasswordShowsPasswordError() {
        loginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(Accounts.DEMO_USER_NAME)
            .enterPassword(Accounts.WRONG_PASSWORD)
            .tapLogIn()
            .checkPasswordErrorIsDisplayed()
    }

    @Test
    fun invalidUserNameAndInvalidPasswordShowsUserNameAndPasswordErrors() {
        loginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(Accounts.MISSING_USER_PREFIX + uniqueSuffix())
            .enterPassword(Accounts.WRONG_PASSWORD)
            .tapLogIn()
            .checkUserNameErrorIsDisplayed()
            .checkPasswordErrorIsDisplayed()
    }

    @Test
    fun registrationWithValidDataOpensHomeScreen() {
        loginSteps
            .checkLoginScreenIsDisplayed()
            .tapSignUp()
        registrationSteps
            .checkRegistrationScreenIsDisplayed()
            .enterUserName(Accounts.NEW_USER_PREFIX + uniqueSuffix())
            .enterPassword(Accounts.NEW_USER_PASSWORD)
            .enterRepeatPassword(Accounts.NEW_USER_PASSWORD)
            .waitForAvatarsToLoad()
            .selectAvatar(Accounts.AVATAR_INDEX)
            .tapSignUp()
        homeSteps.checkHomeScreenIsDisplayed()
    }
}
