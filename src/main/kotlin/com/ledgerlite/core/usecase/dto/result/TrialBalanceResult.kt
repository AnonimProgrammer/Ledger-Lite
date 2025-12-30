package com.ledgerlite.core.usecase.dto.result

import com.ledgerlite.core.usecase.dto.result.vo.TrialBalanceLine
import java.math.BigDecimal
import java.time.LocalDate

data class TrialBalanceResult(
    val from: LocalDate,
    val to: LocalDate,
    val lines: List<TrialBalanceLine>,
    val totalDebit: BigDecimal,
    val totalCredit: BigDecimal
)
