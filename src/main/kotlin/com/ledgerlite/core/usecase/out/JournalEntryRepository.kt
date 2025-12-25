package com.ledgerlite.core.usecase.out

import com.ledgerlite.core.domain.JournalEntry

interface JournalEntryRepository {
    fun save(entry: JournalEntry)
}