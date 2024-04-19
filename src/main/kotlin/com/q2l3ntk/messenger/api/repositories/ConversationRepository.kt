package com.q2l3ntk.messenger.api.repositories

import com.q2l3ntk.messenger.api.models.Conversation
import org.springframework.data.repository.CrudRepository

interface ConversationRepository : CrudRepository<Conversation, Long> {
    fun findBySenderId(id: Long): List<Conversation>
    fun findByRecipientId(id: Long): List<Conversation>
    fun findBySenderIdAndRecipientId(senderId: Long, recipientId: Long): Conversation?
}