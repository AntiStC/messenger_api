package com.example.messenger_api.components

import com.example.messenger_api.constants.ErrorResponse
import com.example.messenger_api.constants.ResponseConstants
import com.example.messenger_api.exceptions.UserDeactivatedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(UserDeactivatedException::class)
    fun userDiactivated(userDeactivatedException: UserDeactivatedException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value, userDeactivatedException.message)

        return ResponseEntity.unprocessableEntity().body(res)
    }
}