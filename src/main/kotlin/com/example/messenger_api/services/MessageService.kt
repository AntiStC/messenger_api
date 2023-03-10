package com.example.messenger_api.services

import com.example.messenger_api.models.Message
import com.example.messenger_api.models.User

interface MessageService {

    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}