package com.example.messenger_api.services

import com.example.messenger_api.exceptions.MessageEmptyException
import com.example.messenger_api.exceptions.MessageRecipientInvalidException
import com.example.messenger_api.models.Conversation
import com.example.messenger_api.models.Message
import com.example.messenger_api.models.User
import com.example.messenger_api.repositories.ConversationRepository
import com.example.messenger_api.repositories.MessageRepository
import com.example.messenger_api.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    val repository: MessageRepository,
    val conversationRepository: ConversationRepository,
    val conversationService: ConversationService,
    val userRepository: UserRepository
) : MessageService {

    @Throws(MessageEmptyException::class)
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)

        if (optional.isPresent) {
            val recipient = optional.get()

            if (messageText.isNotEmpty()) {
                val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {

                    conversationService.getConversation(sender, recipient) as Conversation
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message = Message(sender, recipient, messageText, conversation)
                repository.save(message)
                return message
            }
        } else {
            throw MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
        }
        throw MessageEmptyException()
    }
}