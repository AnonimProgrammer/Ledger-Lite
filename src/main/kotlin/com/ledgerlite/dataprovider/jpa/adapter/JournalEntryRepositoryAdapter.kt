package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.dataprovider.jpa.mapper.JournalEntryMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringJournalEntryJpaRepository

class JournalEntryRepositoryAdapter(
    private val jpaRepository: SpringJournalEntryJpaRepository
) : JournalEntryRepository {

    override fun save(entry: JournalEntry) {
        val entity = JournalEntryMapper.toJpa(entry)
        jpaRepository.save(entity)
    }
}
