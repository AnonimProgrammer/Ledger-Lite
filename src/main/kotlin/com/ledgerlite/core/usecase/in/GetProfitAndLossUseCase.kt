package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.usecase.dto.result.ProfitAndLossResult
import java.time.LocalDate

interface GetProfitAndLossUseCase {
    fun execute(from: LocalDate, to: LocalDate): ProfitAndLossResult
}