package com.ledgerlite.entrypoint.rest

import com.ledgerlite.core.usecase.dto.result.ProfitAndLossResult
import com.ledgerlite.core.usecase.dto.result.TrialBalanceResult
import com.ledgerlite.core.usecase.`in`.GetProfitAndLossUseCase
import com.ledgerlite.core.usecase.`in`.GetTrialBalanceUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/reports")
class ReportRestAdapter(
    private val getTrialBalanceUseCase: GetTrialBalanceUseCase,
    private val getProfitAndLossUseCase: GetProfitAndLossUseCase
) {

    @GetMapping("/trial-balance")
    fun getTrialBalance(
        @RequestParam("from") @DateTimeFormat(pattern = "dd-MM-yyyy")
        from: LocalDate,
        @RequestParam("to") @DateTimeFormat(pattern = "dd-MM-yyyy")
        to: LocalDate,
    ): ResponseEntity<TrialBalanceResult> {
        val trialBalanceResult = getTrialBalanceUseCase.execute(from, to)
        return ResponseEntity.ok(trialBalanceResult)
    }

    @GetMapping("/profit-and-loss")
    fun getProfitAndLoss(
        @RequestParam("from") @DateTimeFormat(pattern = "dd-MM-yyyy")
        from: LocalDate,
        @RequestParam("to") @DateTimeFormat(pattern = "dd-MM-yyyy")
        to: LocalDate,
    ): ResponseEntity<ProfitAndLossResult> {
        val profitAndLossResult = getProfitAndLossUseCase.execute(from, to)
        return ResponseEntity.ok(profitAndLossResult)
    }
}