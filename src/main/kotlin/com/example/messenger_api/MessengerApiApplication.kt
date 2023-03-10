package com.example.messenger_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class MessengerApiApplication

fun main(args: Array<String>) {
    runApplication<MessengerApiApplication>(*args)

}
