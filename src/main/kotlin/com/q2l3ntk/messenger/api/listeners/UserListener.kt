package com.q2l3ntk.messenger.api.listeners

import com.q2l3ntk.messenger.api.models.User
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