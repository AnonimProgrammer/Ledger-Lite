package com.ledgerlite.core.usecase.dto.result.vo

import java.math.BigDecimal

data class ProfitAndLossLine(
    val accountCode: String,
    val accountName: String,
    val amount: BigDecimal
)
