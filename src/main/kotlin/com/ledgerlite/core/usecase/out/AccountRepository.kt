package com.ledgerlite.core.usecase.out

import com.ledgerlite.core.domain.Account

interface AccountRepository {

    fun findByCode(code: String): Account

    fun findCashAccount(): Account =
        findByCode("CASH")

    fun findAccountsReceivable(): Account =
        findByCode("AR")

    fun findRevenueAccount(): Account =
        findByCode("REV")

    fun findExpenseAccount(): Account =
        findByCode("EXP")
}
