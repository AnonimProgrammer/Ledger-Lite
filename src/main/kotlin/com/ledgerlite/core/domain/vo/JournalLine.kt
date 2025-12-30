package com.ledgerlite.core.domain.vo

import java.util.UUID

@ConsistentCopyVisibility
data class JournalLine private constructor(
    val accountId: UUID,
    val debit: Money,
    val credit: Money
) {
    companion object {

        fun debit(accountId: UUID, amount: Money): JournalLine {
            require(amount.isPositive()) { "Debit amount must be positive." }
            return JournalLine(accountId, amount, Money.zero())
        }

        fun credit(accountId: UUID, amount: Money): JournalLine {
            require(amount.isPositive()) { "Credit amount must be positive." }
            return JournalLine(accountId, Money.zero(), amount)
        }

        fun reconstitute(
            accountId: UUID,
            debit: Money,
            credit: Money,
            ) = JournalLine(accountId, debit, credit)
    }
}