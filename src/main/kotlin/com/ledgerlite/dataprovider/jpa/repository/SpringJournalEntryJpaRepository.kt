package com.ledgerlite.dataprovider.jpa.repository

import com.ledgerlite.dataprovider.jpa.entity.JournalEntryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.util.Optional

interface SpringJournalEntryJpaRepository : JpaRepository<JournalEntryJpaEntity, Long> {

    @Query("SELECT e FROM JournalEntryJpaEntity " +
            "e WHERE e.date >= :from AND e.date <= :to")
    fun getEntriesBetween(from: LocalDate, to: LocalDate): Optional<List<JournalEntryJpaEntity>>
}