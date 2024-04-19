package com.q2l3ntk.messenger.api.repositories

import com.q2l3ntk.messenger.api.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(userName: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}
