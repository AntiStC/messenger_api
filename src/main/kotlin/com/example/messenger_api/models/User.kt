package com.example.messenger_api.models

import com.example.messenger_api.listeners.UserListener
import jakarta.persistence.*
import jakarta.persistence.GenerationType.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
@EntityListeners(UserListener::class)
class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    var id: Long = 0

    @Column(unique = true)
    @Size(min = 2)
    var username: String = ""

    @Size(min = 11)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})$")
    var phoneNumber: String = ""

    @Size(min = 60, max = 60)
    var password: String = ""

    var status: String = ""

    @Pattern(regexp = "\\A(activated|diactivated)\\z")
    var accountStatus: String = "activated"

    @DateTimeFormat
    var createAt: Date = Date.from(Instant.now())

    @OneToMany(mappedBy = "sander", targetEntity = Message::class)
    private var sentMessages: Collection<Message>? = null

    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null
}