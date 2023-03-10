package com.example.messenger_api.repositories

import com.example.messenger_api.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}