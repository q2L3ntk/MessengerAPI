package com.q2l3ntk.messenger.api.components

import com.q2l3ntk.messenger.api.constants.ErrorResponse
import com.q2l3ntk.messenger.api.exceptions.ConversationInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {
    @ExceptionHandler(ConversationInvalidException::class)
    fun conversationInvalidException(conversationInvalidException: ConversationInvalidException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}