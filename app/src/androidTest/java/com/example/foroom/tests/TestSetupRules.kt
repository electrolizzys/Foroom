package com.example.foroom.tests

import com.example.foroom.constants.Accounts
import com.example.foroom.constants.ChatData
import com.example.foroom.domain.model.request.LogInRequest
import com.example.foroom.domain.model.request.RegistrationRequest
import com.example.foroom.domain.usecase.CreateChatUseCase
import com.example.foroom.domain.usecase.GetChatsUseCase
import com.example.foroom.domain.usecase.LogInUserUseCase
import com.example.foroom.domain.usecase.RegisterUserUseCase
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.shared.util.runtime.user_token.UserTokenRuntimeHolder
import kotlinx.coroutines.runBlocking
import org.junit.rules.ExternalResource
import org.koin.core.context.GlobalContext

/**
 * Removes the saved login before the activity starts, so every test begins on the login
 * screen no matter how the previous test ended.
 */
class ClearSessionRule : ExternalResource() {
    override fun before() {
        runBlocking { GlobalContext.get().get<ForoomUserDataStore>().clearUserData() }
    }
}

/**
 * Makes sure User A, User B and the three conversation chats exist on this device. Only missing
 * data is created, so it is safe to run before every test and never relies on another test.
 */
class PrepareTestDataRule : ExternalResource() {
    private val koin get() = GlobalContext.get()
    private val tokens get() = koin.get<UserTokenRuntimeHolder>()

    override fun before() = runBlocking {
        try {
            ensureAccount(Accounts.USER_B_NAME, Accounts.USER_B_PASSWORD)
            tokens.setUserToken(ensureAccount(Accounts.USER_A_NAME, Accounts.USER_A_PASSWORD))
            ensureChat(ChatData.JOHN_WEEK_TITLE)
            ensureChat(ChatData.FULL_NAME_CHAT_TITLE)
            ensureChat(ChatData.SHARED_CHAT_TITLE)
        } finally {
            // The test itself logs in through the UI, so leave no session behind.
            tokens.setUserToken("")
        }
    }

    /** Logs in, or registers the account if it does not exist yet. Returns its session token. */
    private suspend fun ensureAccount(userName: String, password: String): String {
        runCatching { koin.get<LogInUserUseCase>()(LogInRequest(userName, password)) }
            .onSuccess { return it.token }

        return runCatching {
            koin.get<RegisterUserUseCase>()(
                RegistrationRequest(userName, password, Accounts.SETUP_AVATAR_ID)
            ).token
        }.getOrElse { error ->
            throw IllegalStateException(
                "Could not log in or register '$userName'. If it exists, check its password.", error
            )
        }
    }

    private suspend fun ensureChat(title: String) {
        val existing = koin.get<GetChatsUseCase>()(page = 0, limit = 100, name = title).chats
        if (existing.none { chat -> chat.name == title }) {
            koin.get<CreateChatUseCase>()(title, ChatData.SETUP_EMOJI_ID)
        }
    }
}
