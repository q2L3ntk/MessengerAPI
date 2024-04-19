package com.q2l3ntk.messenger.api.repositories

import com.q2l3ntk.messenger.api.models.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {
    fun findByConversationId(conversationId: Long): List<Message>
}