package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.Helper.uniqueSuffix
import com.example.foroom.constants.Accounts
import com.example.foroom.constants.ChatData
import com.example.foroom.constants.ProfileLabels
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.HomeSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val rules: RuleChain = RuleChain
        .outerRule(ClearSessionRule())
        .around(PrepareTestDataRule())
        .around(ActivityScenarioRule(ForoomActivity::class.java))

    private val loginSteps = LoginSteps()
    private val homeSteps = HomeSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()
    private val conversationSteps = ConversationSteps()

    @Test
    fun changePasswordAndLogInWithNewPassword() {
        signIn(Accounts.USER_A_PASSWORD)
        changePassword(Accounts.USER_A_NEW_PASSWORD)

        signIn(Accounts.USER_A_NEW_PASSWORD)

        // Cleanup: restore the known password so the test can run again.
        changePassword(Accounts.USER_A_PASSWORD)
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        signIn(Accounts.USER_A_PASSWORD)
        homeSteps.openProfile()

        profileSteps
            .checkProfileIsDisplayed()
            .tapChangeLanguage()
            .checkLanguageSheetIsDisplayed()
            .selectGeorgian()
            .checkChangeLanguageLabel(ProfileLabels.CHANGE_LANGUAGE_GEORGIAN)
            .checkSignOutLabel(ProfileLabels.SIGN_OUT_GEORGIAN)

            .tapChangeLanguage()
            .checkLanguageSheetIsDisplayed()
            .selectEnglish()
            .checkChangeLanguageLabel(ProfileLabels.CHANGE_LANGUAGE_ENGLISH)
            .checkSignOutLabel(ProfileLabels.SIGN_OUT_ENGLISH)

            .tapChangeLanguage()
            .checkLanguageSheetIsDisplayed()
            .selectGeorgian()
            .checkChangeLanguageLabel(ProfileLabels.CHANGE_LANGUAGE_GEORGIAN)
            .checkSignOutLabel(ProfileLabels.SIGN_OUT_GEORGIAN)
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = "${ChatData.STUDENT_FULL_NAME} ${uniqueSuffix()}"

        signIn(Accounts.USER_A_PASSWORD)
        homeSteps.openCreateChat()
        chatSteps
            .checkCreateChatScreenIsDisplayed()
            .enterChatName(chatName)
            .waitForChatImagesToLoad()
            .selectChatImage(ChatData.CHAT_IMAGE_INDEX)
            .tapCreateChat()
        conversationSteps
            .checkConversationIsOpen(chatName)
            .closeConversation()
        chatSteps
            .checkChatListIsDisplayed()
            .searchChat(chatName)
            .checkChatIsInList(chatName)
    }

    private fun signIn(password: String) {
        loginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(Accounts.USER_A_NAME)
            .enterPassword(password)
            .tapLogIn()
        homeSteps.checkHomeScreenIsDisplayed()
    }

    /** Changing the password signs the user out, so this ends on the login screen. */
    private fun changePassword(newPassword: String) {
        homeSteps.openProfile()
        profileSteps
            .checkProfileIsDisplayed()
            .tapChangePassword()
            .checkChangePasswordSheetIsDisplayed()
            .enterNewPassword(newPassword)
            .enterRepeatNewPassword(newPassword)
            .tapConfirm()
        loginSteps.checkLoginScreenIsDisplayed()
    }
}
