package com.ledgerlite.dataprovider.jpa.repository

import com.ledgerlite.dataprovider.jpa.entity.JournalEntryJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringJournalEntryJpaRepository : JpaRepository<JournalEntryJpaEntity, Long> {
}