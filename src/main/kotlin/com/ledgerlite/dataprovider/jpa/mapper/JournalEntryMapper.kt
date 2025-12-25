package com.ledgerlite.dataprovider.jpa.mapper

import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.JournalLine
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.dataprovider.jpa.entity.JournalEntryJpaEntity
import com.ledgerlite.dataprovider.jpa.entity.embeddable.JournalLineEmbeddable

object JournalEntryMapper {

    fun toJpa(domain: JournalEntry): JournalEntryJpaEntity =
        JournalEntryJpaEntity(
            id = domain.id,
            date = domain.date,
            description = domain.description,
            lines = domain.lines.map {
                JournalLineEmbeddable(
                    accountId = it.accountId,
                    debit = it.debit.amount,
                    credit = it.credit.amount
                )
            }
        )

    fun toDomain(entity: JournalEntryJpaEntity): JournalEntry {
        val journalEntry = JournalEntry.create(
            date = entity.date,
            description = entity.description,
            lines = entity.lines.map {
                JournalLine.reconstitute(
                    accountId = it.accountId,
                    debit = Money.of(it.debit),
                    credit = Money.of(it.credit)
                )
            }
        )
        journalEntry.id = entity.id
        return journalEntry
    }
}
