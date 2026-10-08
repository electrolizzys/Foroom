package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.Helper.uniqueSuffix
import com.example.foroom.constants.Accounts
import com.example.foroom.constants.ChatData
import com.example.foroom.constants.MessageData
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
class ConversationTests {

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
    fun drinkInvitationInJohnWeekStaysVisibleAfterReopeningChat() {
        val invitation = "${MessageData.DRINK_INVITATION} ${uniqueSuffix()}"

        signIn(Accounts.USER_A_NAME, Accounts.USER_A_PASSWORD)
        openChat(ChatData.JOHN_WEEK_TITLE)

        conversationSteps
            .typeMessage(invitation)
            .tapSend()
            .checkMessageIsDisplayed(invitation)
            .closeConversation()

        openChat(ChatData.JOHN_WEEK_TITLE)
        conversationSteps.checkMessageIsDisplayed(invitation)
    }

    @Test
    fun questionAboutFavouriteModuleIsShownInOwnChat() {
        val question = "${MessageData.MODULE_QUESTION} ${uniqueSuffix()}"

        signIn(Accounts.USER_A_NAME, Accounts.USER_A_PASSWORD)
        openChat(ChatData.FULL_NAME_CHAT_TITLE)

        conversationSteps
            .typeMessage(question)
            .tapSend()
            .checkMessageIsDisplayed(question)
    }

    @Test
    fun secondAccountFindsOlderGreetingAndRepliesInSharedChat() {
        val suffix = uniqueSuffix()
        val greeting = "${MessageData.GREETING} $suffix"
        val reply = "${MessageData.REPLY} $suffix"

        // User A greets, then pushes the greeting into older history.
        signIn(Accounts.USER_A_NAME, Accounts.USER_A_PASSWORD)
        openChat(ChatData.SHARED_CHAT_TITLE)
        conversationSteps
            .typeMessage(greeting)
            .tapSend()
            .checkMessageIsDisplayed(greeting)
        for (number in 1..MessageData.ADDITIONAL_MESSAGE_COUNT) {
            val filler = "${MessageData.ADDITIONAL_MESSAGE} $number $suffix"
            conversationSteps
                .typeMessage(filler)
                .tapSend()
                .checkMessageIsDisplayed(filler)
        }
        conversationSteps.closeConversation()
        signOut()

        // User B swipes back to the greeting and replies.
        signIn(Accounts.USER_B_NAME, Accounts.USER_B_PASSWORD)
        openChat(ChatData.SHARED_CHAT_TITLE)
        conversationSteps
            .swipeToOlderMessagesUntilDisplayed(greeting)
            .checkMessageSender(greeting, Accounts.USER_A_NAME)
            .typeMessage(reply)
            .tapSend()
            .checkMessageIsDisplayed(reply)
            .closeConversation()
        signOut()

        // User A sees the reply from User B.
        signIn(Accounts.USER_A_NAME, Accounts.USER_A_PASSWORD)
        openChat(ChatData.SHARED_CHAT_TITLE)
        conversationSteps
            .checkMessageIsDisplayed(reply)
            .checkMessageSender(reply, Accounts.USER_B_NAME)
    }

    private fun signIn(userName: String, password: String) {
        loginSteps
            .checkLoginScreenIsDisplayed()
            .enterUserName(userName)
            .enterPassword(password)
            .tapLogIn()
        homeSteps.checkHomeScreenIsDisplayed()
    }

    private fun openChat(title: String) {
        chatSteps
            .checkChatListIsDisplayed()
            .searchChat(title)
            .checkChatIsInList(title)
            .openChat(title)
        conversationSteps.checkConversationIsOpen(title)
    }

    private fun signOut() {
        homeSteps.openProfile()
        profileSteps
            .checkProfileIsDisplayed()
            .tapSignOut()
        loginSteps.checkLoginScreenIsDisplayed()
    }
}
