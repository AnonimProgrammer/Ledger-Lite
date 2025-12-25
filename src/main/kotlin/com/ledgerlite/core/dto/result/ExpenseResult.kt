package com.ledgerlite.core.dto.result

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.domain.vo.Money
import java.time.LocalDate

data class ExpenseResult(
    val id: Long?,
    val vendor: String,
    val grossAmount: Money,
    val taxAmount: Money,
    val date: LocalDate
) {
    companion object {
        fun from(expense: Expense): ExpenseResult {
            return ExpenseResult(
                id = expense.id,
                vendor = expense.vendor,
                grossAmount = expense.grossAmount,
                taxAmount = expense.taxAmount,
                date = expense.date
            )
        }
    }
}