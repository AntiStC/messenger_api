package com.example.messenger_api.repositories

import com.example.messenger_api.models.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {

    fun findByConversationId(conversationId: Long): List<Message>
}