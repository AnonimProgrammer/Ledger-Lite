package com.ledgerlite.dataprovider.jpa.entity

import com.ledgerlite.dataprovider.jpa.entity.embeddable.JournalLineEmbeddable
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "journal_entries")
data class JournalEntryJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val date: LocalDate,

    @Column(nullable = false)
    val description: String,

    @ElementCollection
    @CollectionTable(
        name = "journal_lines",
        joinColumns = [JoinColumn(name = "journal_entry_id")]
    )
    val lines: List<JournalLineEmbeddable>
)

