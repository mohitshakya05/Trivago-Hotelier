package com.Hotelier.booking.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
@ControllerAdvice
class HotelierExceptionHandler {
    @ExceptionHandler()
    fun handleHotelierExceptionException(ex: HotelierException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_ACCEPTABLE.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}