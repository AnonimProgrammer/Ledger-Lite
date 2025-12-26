package com.ledgerlite.core.usecase.mapper

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.usecase.dto.result.ExpenseResult

object ExpenseMapper {

    fun from(expense: Expense): ExpenseResult =
        ExpenseResult(
            id = expense.id,
            vendor = expense.vendor,
            grossAmount = expense.grossAmount.amount,
            taxAmount = expense.taxAmount.amount,
            date = expense.date
        )
}