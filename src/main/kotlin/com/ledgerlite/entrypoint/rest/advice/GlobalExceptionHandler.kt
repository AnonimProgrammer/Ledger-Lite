package com.ledgerlite.entrypoint.rest.advice

import com.ledgerlite.core.domain.error.InvalidInvoiceStateException
import com.ledgerlite.dataprovider.exception.InvoiceNotFoundException
import com.ledgerlite.dataprovider.exception.MissingAccountException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MissingAccountException::class)
    fun handleMissingAccountException(ex: MissingAccountException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Account not found.",
            errorCode = HttpStatus.NOT_FOUND.value(),
            errorType = HttpStatus.NOT_FOUND.name,
            LocalDateTime.now()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(InvoiceNotFoundException::class)
    fun handleInvoiceNotFoundException(ex: InvoiceNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Invoice not found.",
            errorCode = HttpStatus.NOT_FOUND.value(),
            errorType = HttpStatus.NOT_FOUND.name,
            LocalDateTime.now()
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(InvalidInvoiceStateException::class)
    fun handleInvalidInvoiceStateException(ex: InvalidInvoiceStateException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Invalid invoice state.",
            errorCode = HttpStatus.BAD_REQUEST.value(),
            errorType = HttpStatus.BAD_REQUEST.name,
            LocalDateTime.now()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Invalid JSON request.",
            errorCode = HttpStatus.BAD_REQUEST.value(),
            errorType = HttpStatus.BAD_REQUEST.name,
            LocalDateTime.now()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "An unexpected error occurred.",
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            errorType = HttpStatus.INTERNAL_SERVER_ERROR.name,
            LocalDateTime.now()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}