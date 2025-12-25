package com.ledgerlite.core.usecase.out

interface TransactionPort {
    fun <T> inTransaction(block: () -> T): T
}