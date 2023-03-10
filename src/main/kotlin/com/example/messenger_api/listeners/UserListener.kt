package com.example.messenger_api.listeners

import com.example.messenger_api.models.User
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserListener {

    @PrePersist
    @PreUpdate
    fun hashPassword(user: User) {
        user.password = BCryptPasswordEncoder().encode(user.password)
    }
}