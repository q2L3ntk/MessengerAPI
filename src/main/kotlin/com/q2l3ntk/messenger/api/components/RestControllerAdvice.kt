package com.q2l3ntk.messenger.api.components

import com.q2l3ntk.messenger.api.constants.ErrorResponse
import com.q2l3ntk.messenger.api.constants.ResponseConstants
import com.q2l3ntk.messenger.api.exceptions.UserDeactivatedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice {
    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeactivated(userDeactivatedException: UserDeactivatedException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value, userDeactivatedException.message)
        return ResponseEntity(res, HttpStatus.UNAUTHORIZED)
    }
}