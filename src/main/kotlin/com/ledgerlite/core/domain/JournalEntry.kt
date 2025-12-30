package com.ledgerlite.core.domain

import com.ledgerlite.core.domain.vo.JournalLine
import com.ledgerlite.core.domain.vo.Money
import java.time.LocalDate

@ConsistentCopyVisibility
data class JournalEntry private constructor(
    var id: Long?,
    val date: LocalDate,
    val description: String,
    val lines: List<JournalLine>
) {

    init {
        require(lines.isNotEmpty()) { "Journal entry must have lines." }
        require(isBalanced()) { "Journal entry must be balanced." }
    }

    private fun isBalanced(): Boolean {
        val totalDebit = lines.fold(Money.zero()) { acc, l -> acc + l.debit }
        val totalCredit = lines.fold(Money.zero()) { acc, l -> acc + l.credit }
        return totalDebit == totalCredit
    }

    companion object {
        fun create(
            date: LocalDate,
            description: String,
            lines: List<JournalLine>
        ): JournalEntry =
            JournalEntry(
                id = null,
                date = date,
                description = description,
                lines = lines
            )
    }
}

