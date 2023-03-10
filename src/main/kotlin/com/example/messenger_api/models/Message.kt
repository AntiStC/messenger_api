package com.example.messenger_api.models

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*

@Entity
class Message(sander: User, recipient: User, messageText: String, conversation: Conversation) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @ManyToOne(optional = false)
    @JoinColumn(name = "sander_id", referencedColumnName = "id")
    var sander: User? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    var recipient: User? = null

    var body: String? = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    var conversation: Conversation? = null

    @DateTimeFormat
    var createAt: Date = Date.from(Instant.now())
}