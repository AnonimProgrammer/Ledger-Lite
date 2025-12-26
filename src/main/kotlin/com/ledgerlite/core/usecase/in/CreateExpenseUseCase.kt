package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.usecase.dto.command.CreateExpenseCommand
import com.ledgerlite.core.usecase.dto.result.ExpenseResult

interface CreateExpenseUseCase {
    fun execute(command: CreateExpenseCommand): ExpenseResult
}
