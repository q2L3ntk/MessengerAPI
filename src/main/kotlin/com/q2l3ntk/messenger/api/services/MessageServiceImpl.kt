package com.q2l3ntk.messenger.api.services

import com.q2l3ntk.messenger.api.exceptions.MessageEmptyException
import com.q2l3ntk.messenger.api.exceptions.MessageRecipientInvalidException
import com.q2l3ntk.messenger.api.models.Conversation
import com.q2l3ntk.messenger.api.models.Message
import com.q2l3ntk.messenger.api.models.User
import com.q2l3ntk.messenger.api.repositories.MessageRepository
import com.q2l3ntk.messenger.api.repositories.ConversationRepository
import com.q2l3ntk.messenger.api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(val repository: MessageRepository, val conversationRepository: ConversationRepository, val conversationService: ConversationService, val userRepository: UserRepository) : MessageService {
    @Throws(MessageEmptyException::class, MessageRecipientInvalidException::class)
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)
        if (optional.isPresent) {
            val recipient = optional.get()
            if (!messageText.isEmpty()) {
            val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                conversationService.getConversation(sender, recipient) as Conversation
            } else {
                conversationService.createConversation(sender, recipient)
            }
            conversationRepository.save(conversation)

            val message = Message()
            repository.save(message)
            return message
            }
        } else {
            throw MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
        }
        throw MessageEmptyException()
    }
}