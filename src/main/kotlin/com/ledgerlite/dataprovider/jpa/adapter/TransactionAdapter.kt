package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.usecase.out.TransactionPort
import jakarta.transaction.Transactional

open class TransactionAdapter : TransactionPort {

    @Transactional
    override fun <T> inTransaction(block: () -> T): T =
        block()
}
