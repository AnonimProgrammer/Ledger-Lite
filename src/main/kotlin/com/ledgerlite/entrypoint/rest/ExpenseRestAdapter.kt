package com.ledgerlite.entrypoint.rest

import com.ledgerlite.core.usecase.dto.command.CreateExpenseCommand
import com.ledgerlite.core.usecase.dto.result.ExpenseResult
import com.ledgerlite.core.usecase.`in`.CreateExpenseUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/expenses")
class ExpenseRestAdapter(
    private val createExpenseUseCase: CreateExpenseUseCase,
) {

    @PostMapping
    fun createExpense(@RequestBody command: CreateExpenseCommand): ResponseEntity<ExpenseResult> {
        val result = createExpenseUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }
}