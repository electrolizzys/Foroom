package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.steps.ProfileSteps.Language
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    private val clearSessionRule = object : ExternalResource() {
        override fun before() {
            runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
        }
    }
    private val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(clearSessionRule).around(activityRule)

    @Test
    fun changePasswordAndLogInWithNewPassword() {
        logInToHome(TestData.password)
        profileSteps.openProfile()
        profileSteps.changePassword(TestData.NEW_PASSWORD)
        loginSteps.assertLoginScreenDisplayed()

        logInToHome(TestData.NEW_PASSWORD)
        profileSteps.openProfile()
        profileSteps.changePassword(TestData.password)
        loginSteps.assertLoginScreenDisplayed()
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        logInToHome(TestData.password)
        profileSteps.openProfile()

        profileSteps.selectLanguage(Language.GEORGIAN)
        profileSteps.assertProfileShownIn(Language.GEORGIAN)
        profileSteps.selectLanguage(Language.ENGLISH)
        profileSteps.assertProfileShownIn(Language.ENGLISH)
        profileSteps.selectLanguage(Language.GEORGIAN)
        profileSteps.assertProfileShownIn(Language.GEORGIAN)
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = TestData.uniqueChatName()

        logInToHome(TestData.password)
        chatSteps.createChat(chatName, TestData.CHAT_IMAGE_INDEX)
        chatSteps.assertChatOpened(chatName)
        chatSteps.closeChat()
        chatSteps.searchChat(chatName)
        chatSteps.assertChatInList(chatName)
    }

    private fun logInToHome(password: String) {
        loginSteps.assertLoginScreenDisplayed()
        loginSteps.logIn(TestData.userName, password)
        chatSteps.assertHomeScreenDisplayed()
    }

    private object TestData {
        private const val DEFAULT_USER_NAME = "lizi_test"
        private const val DEFAULT_PASSWORD = "Lizi123!"

        const val NEW_PASSWORD = "Lizi456!"
        const val CHAT_IMAGE_INDEX = 1
        private const val STUDENT_FULL_NAME = "Lizi Kutateladze"

        val userName: String
            get() = InstrumentationRegistry.getArguments()
                .getString("profileUsername", DEFAULT_USER_NAME)

        val password: String
            get() = InstrumentationRegistry.getArguments()
                .getString("profilePassword", DEFAULT_PASSWORD)

        fun uniqueChatName() = "$STUDENT_FULL_NAME ${System.currentTimeMillis().toString(36)}"
    }
}
