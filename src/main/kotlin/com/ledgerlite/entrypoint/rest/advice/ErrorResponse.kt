package com.ledgerlite.entrypoint.rest.advice

import java.time.LocalDateTime

data class ErrorResponse(
    val message: String,
    val errorCode: Int,
    val errorType: String,
    val timestamp: LocalDateTime,
)
