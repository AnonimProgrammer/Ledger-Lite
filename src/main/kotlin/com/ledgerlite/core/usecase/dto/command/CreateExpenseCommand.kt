package com.ledgerlite.core.usecase.dto.command

import java.math.BigDecimal
import java.time.LocalDate

data class CreateExpenseCommand(
    val vendor: String,
    val grossAmount: BigDecimal,
    val taxAmount: BigDecimal,
    val date: LocalDate
)

