package com.ledgerlite.core.usecase.dto.result

import java.math.BigDecimal
import java.time.LocalDate

data class ExpenseResult(
    val id: Long?,
    val vendor: String,
    val grossAmount: BigDecimal,
    val taxAmount: BigDecimal,
    val date: LocalDate
)