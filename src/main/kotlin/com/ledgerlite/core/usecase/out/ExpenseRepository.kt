package com.ledgerlite.core.usecase.out

import com.ledgerlite.core.domain.Expense

interface ExpenseRepository {
    fun save(expense: Expense): Expense
}