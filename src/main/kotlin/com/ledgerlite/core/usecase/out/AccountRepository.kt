package com.ledgerlite.core.usecase.out

import com.ledgerlite.core.domain.Account

interface AccountRepository {

    fun save(account: Account): Account

    fun findCashAccount(): Account

    fun findExpenseAccount(): Account

    fun findAccountsReceivable(): Account

    fun findRevenueAccount(): Account
}