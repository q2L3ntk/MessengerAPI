package com.q2l3ntk.messenger.api.services

import com.q2l3ntk.messenger.api.exceptions.ConversationInvalidException
import com.q2l3ntk.messenger.api.models.Conversation
import com.q2l3ntk.messenger.api.models.User
import com.q2l3ntk.messenger.api.repositories.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConservationServiceImpl(val repository: ConversationRepository) : ConversationService {
    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation()
        repository.save(conversation)
        return conversation
    }

    override fun conversationExists(userA: User, userB: User): Boolean {
        return if (repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null) true
        else repository.findBySenderIdAndRecipientId(userB.id, userA.id) != null
    }

    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findBySenderIdAndRecipientId(userA.id, userB.id) != null -> repository.findBySenderIdAndRecipientId(userA.id, userB.id)
            repository.findBySenderIdAndRecipientId(userB.id, userA.id) != null -> repository.findBySenderIdAndRecipientId(userB.id, userA.id)
            else -> null
        }
    }

    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)

        if (conversation.isPresent) {
            return conversation.get()
        }
        throw ConversationInvalidException("Invalid conversation id '$conversationId'")
    }

    override fun listUserConversations(userId: Long): ArrayList<Conversation> {
        val conversationList: ArrayList<Conversation> = ArrayList()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecipientId(userId))

        return conversationList
    }

    override fun nameSecondParty(conversation: Conversation, userId: Long): String {
        return if (conversation.sender?.id == userId) {
            conversation.recipient?.username as String
        } else {
            conversation.sender?.username as String
        }
    }
}