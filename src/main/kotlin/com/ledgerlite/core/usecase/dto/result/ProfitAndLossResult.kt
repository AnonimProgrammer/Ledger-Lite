package com.ledgerlite.core.usecase.dto.result

import com.ledgerlite.core.usecase.dto.result.vo.ProfitAndLossLine
import java.math.BigDecimal
import java.time.LocalDate

data class ProfitAndLossResult(
    val from: LocalDate,
    val to: LocalDate,
    val revenueLines: List<ProfitAndLossLine>,
    val expenseLines: List<ProfitAndLossLine>,
    val totalRevenue: BigDecimal,
    val totalExpense: BigDecimal,
    val netProfit: BigDecimal
)
