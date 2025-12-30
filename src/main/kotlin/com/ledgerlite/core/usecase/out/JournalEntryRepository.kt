package com.ledgerlite.core.usecase.out

import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.vo.JournalLine
import java.time.LocalDate

interface JournalEntryRepository {

    fun save(entry: JournalEntry)

    fun findJournalLinesBetween(from: LocalDate, to: LocalDate): List<JournalLine>
}