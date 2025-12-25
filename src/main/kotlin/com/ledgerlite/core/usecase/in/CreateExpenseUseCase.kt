package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.dto.command.CreateExpenseCommand
import com.ledgerlite.core.dto.result.ExpenseResult

interface CreateExpenseUseCase {
    fun execute(command: CreateExpenseCommand): ExpenseResult
}
