package com.mtallon.qukt.delivery.rest.imp

import com.mtallon.qukt.delivery.rest.api.ErrorCodeDto
import com.mtallon.qukt.delivery.rest.api.ErrorDto
import com.mtallon.qukt.usecases.exceptions.NotFoundException
import com.mtallon.qukt.usecases.exceptions.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException) =
        ResponseEntity(ErrorDto(ErrorCodeDto.NOT_FOUND, "Resource not found"), HttpStatus.NOT_FOUND)
}
