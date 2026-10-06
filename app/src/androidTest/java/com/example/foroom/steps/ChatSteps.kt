package com.example.foroom.steps

import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()
    fun assertHomeScreenDisplayed() {
        chatsPage.waitForHomeScreen()
    }

    fun createChat(name: String, imageIndex: Int) {
        createChatPage.open()
        createChatPage.waitUntilDisplayed()
        createChatPage.enterChatName(name)
        createChatPage.waitForImagesLoaded()
        createChatPage.selectImage(imageIndex)
        createChatPage.tapCreateChat()
    }

    fun assertChatOpened(name: String) {
        chatsPage.waitForOpenedChat(name)
    }

    fun closeChat() {
        chatsPage.tapCloseChat()
        chatsPage.waitForChatList()
    }

    fun searchChat(name: String) {
        chatsPage.search(name)
    }

    fun assertChatInList(name: String) {
        chatsPage.waitForChatCard(name)
    }
}
