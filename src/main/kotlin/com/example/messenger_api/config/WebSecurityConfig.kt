package com.example.messenger_api.config

import com.example.messenger_api.filters.JWTAuthenticationFilter
import com.example.messenger_api.filters.JWTLoginFilter
import com.example.messenger_api.services.AppUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(val userDetailsService: AppUserDetailsService) {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        val authenticationManagerBuilder: AuthenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder::class.java)

        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())

        val authenticationManager:AuthenticationManager=authenticationManagerBuilder.build()

        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/users/registrations").permitAll()
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest()
            .authenticated().and()
            .addFilterBefore(
                JWTLoginFilter("/login", authenticationManager),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}