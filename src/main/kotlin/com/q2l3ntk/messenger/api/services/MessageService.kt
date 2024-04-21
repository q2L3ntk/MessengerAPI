package com.q2l3ntk.messenger.api.services

import com.q2l3ntk.messenger.api.models.Message
import com.q2l3ntk.messenger.api.models.User

interface MessageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}