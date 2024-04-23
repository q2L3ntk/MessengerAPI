package com.q2l3ntk.messenger.api.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.q2l3ntk.messenger.api.security.AccountCredentials
import com.q2l3ntk.messenger.api.services.TokenAuthenticationService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException

class JWTLoginFilter(url: String, authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {
    init {
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(request.inputStream, AccountCredentials::class.java)
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(credentials.username, credentials.password, emptyList()))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        TokenAuthenticationService.addAuthentication(response, authResult.name)
    }
}