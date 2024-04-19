package com.q2l3ntk.messenger.api.models

import com.q2l3ntk.messenger.api.listeners.UserListener
import java.time.Instant
import jakarta.persistence.*
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@Entity
@Table(name = "`user`")
@EntityListeners(UserListener::class)
class User {
    @Column(unique = true)
    @Size(min = 2)
    var username : String = ""
    @Size(min = 11)
    @Pattern(regexp = "\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$")
    var phoneNumber: String = ""
    @Size(min = 60, max = 60)
    var password: String = ""
    var status: String = ""
    @Pattern(regexp = "\\A(activted|deactivated)\\z")
    var accountStatus: String = "activated"
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())

    // коллекция отправленных сообщений
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessages: Collection<Message>? = null

    // коллекция полученных сообщений
    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null
}