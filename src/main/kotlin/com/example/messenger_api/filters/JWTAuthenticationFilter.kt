package com.example.messenger_api.filters

import com.example.messenger_api.services.TokenAuthenticationService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JWTAuthenticationFilter : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val authintication = TokenAuthenticationService().getAuthentication(request as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = authintication
        chain.doFilter(request, response)
    }
}