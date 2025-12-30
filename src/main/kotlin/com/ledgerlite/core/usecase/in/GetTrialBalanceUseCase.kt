package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.usecase.dto.result.TrialBalanceResult
import java.time.LocalDate

interface GetTrialBalanceUseCase {
    fun execute(from: LocalDate, to: LocalDate): TrialBalanceResult
}