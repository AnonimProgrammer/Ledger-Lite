package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.vo.JournalLine
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.dataprovider.exception.MissingJournalEntryException
import com.ledgerlite.dataprovider.jpa.mapper.JournalEntryJpaMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringJournalEntryJpaRepository
import java.time.LocalDate

class JournalEntryRepositoryAdapter(
    private val jpaRepository: SpringJournalEntryJpaRepository
) : JournalEntryRepository {

    override fun save(entry: JournalEntry) {
        val entity = JournalEntryJpaMapper.toJpa(entry)
        jpaRepository.save(entity)
    }

    override fun findJournalLinesBetween(
        from: LocalDate,
        to: LocalDate
    ): List<JournalLine> {
        val entities = jpaRepository.getEntriesBetween(from, to)
            .orElseThrow{ MissingJournalEntryException() }

        val entries = entities.map { JournalEntryJpaMapper.toDomain(it) }
        return entries.stream().map { it.lines }.flatMap { it.stream() }.toList()
    }
}
