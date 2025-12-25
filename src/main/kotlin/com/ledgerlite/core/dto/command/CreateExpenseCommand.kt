package com.ledgerlite.core.dto.command

import com.ledgerlite.core.domain.vo.Money
import java.time.LocalDate

data class CreateExpenseCommand(
    val vendor: String,
    val grossAmount: Money,
    val taxAmount: Money,
    val date: LocalDate
)

