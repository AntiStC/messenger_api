package com.example.messenger_api.models

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*

@Entity
class Conversation(userA: User, userB: User) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    var sender: User? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    var recipient: User? = null

    @DateTimeFormat
    val createAt: Date = Date.from(Instant.now())

    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    private var message: Collection<Message>? = null
}