package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    private val clearSessionRule = object : ExternalResource() {
        override fun before() {
            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }
    private val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(clearSessionRule).around(activityRule)

    @Test
    fun validUserNameAndInvalidPasswordShowsPasswordError() {
        LoginSteps.assertLoginScreenDisplayed()
        LoginSteps.logIn(TestData.existingUserName, TestData.WRONG_PASSWORD)
        LoginSteps.assertPasswordErrorDisplayed()
    }
    @Test
    fun invalidUserNameAndInvalidPasswordShowsUserNameAndPasswordErrors() {
        LoginSteps.assertLoginScreenDisplayed()
        LoginSteps.logIn(TestData.nonExistingUserName(), TestData.WRONG_PASSWORD)
        LoginSteps.assertUserNameErrorDisplayed()
        LoginSteps.assertPasswordErrorDisplayed()
    }

    @Test
    fun registrationWithValidDataOpensHomeScreen() {
        LoginSteps.assertLoginScreenDisplayed()
        LoginSteps.openRegistration()
        RegistrationSteps.assertRegistrationScreenDisplayed()
        RegistrationSteps.register(
            userName = TestData.uniqueUserName(),
            password = TestData.VALID_PASSWORD,
            avatarIndex = TestData.AVATAR_INDEX
        )
        RegistrationSteps.assertRegistrationSucceeded()
    }

    private object TestData {
        private const val DEFAULT_EXISTING_USER_NAME = "student"

        const val WRONG_PASSWORD = "WrongPass1!"
        const val VALID_PASSWORD = "Test1234!"
        const val AVATAR_INDEX = 1

        val existingUserName: String
            get() = InstrumentationRegistry.getArguments()
                .getString("validUsername", DEFAULT_EXISTING_USER_NAME)

        fun nonExistingUserName() = "non_${uniqueSuffix()}"
        fun uniqueUserName() = "valid_${uniqueSuffix()}"
        private fun uniqueSuffix() = System.currentTimeMillis().toString(36)
    }
}
