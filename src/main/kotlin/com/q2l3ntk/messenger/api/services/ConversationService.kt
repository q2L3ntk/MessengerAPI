package com.q2l3ntk.messenger.api.services

import com.q2l3ntk.messenger.api.models.Conversation
import com.q2l3ntk.messenger.api.models.User

interface ConversationService {
    fun createConversation(userA: User, userB: User): Conversation
    fun conversationExists(userA: User, userB: User): Boolean
    fun getConversation(userA: User, userB: User): Conversation?
    fun retrieveThread(conversationId: Long): Conversation
    fun listUserConversations(userId: Long): List<Conversation>
    fun nameSecondParty(conversation: Conversation, userId: Long): String
}