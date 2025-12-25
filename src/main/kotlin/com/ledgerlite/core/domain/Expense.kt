package com.ledgerlite.core.domain

import com.ledgerlite.core.domain.vo.Money
import java.time.LocalDate

data class Expense private constructor(
    var id: Long?,
    val vendor: String,
    val grossAmount: Money,
    val taxAmount: Money,
    val date: LocalDate
) {
    init {
        require(grossAmount.amount >= taxAmount.amount) {
            "Tax cannot exceed gross amount."
        }
    }

    companion object {
        fun create(
            vendor: String,
            grossAmount: Money,
            taxAmount: Money,
            date: LocalDate
        ): Expense =
            Expense(
                id = null,
                vendor = vendor,
                grossAmount = grossAmount,
                taxAmount = taxAmount,
                date = date
            )
    }
}

