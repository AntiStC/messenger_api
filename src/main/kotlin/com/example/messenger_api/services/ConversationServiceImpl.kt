package com.example.messenger_api.services

import com.example.messenger_api.exceptions.ConversationIdInvalidException
import com.example.messenger_api.models.Conversation
import com.example.messenger_api.models.User
import com.example.messenger_api.repositories.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationServiceImpl(val repository: ConversationRepository) : ConversationService {
    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation(userA, userB)
        repository.save(conversation)
        return conversation
    }

    override fun conversationExists(userA: User, userB: User): Boolean {
        return if (repository.findBySenderAndRecipientId(userA.id, userB.id) != null)
            true
        else repository.findBySenderAndRecipientId(userB.id, userA.id) != null
    }

    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findBySenderAndRecipientId(userA.id, userB.id) != null ->
                repository.findBySenderAndRecipientId(userA.id, userB.id)

            repository.findBySenderAndRecipientId(userB.id, userA.id) != null ->
                repository.findBySenderAndRecipientId(userB.id, userA.id)

            else -> null
        }
    }

    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)

        if (conversation.isPresent) {
            return conversation.get()
        }
        throw ConversationIdInvalidException("Invalid conversation id '$conversationId'")
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