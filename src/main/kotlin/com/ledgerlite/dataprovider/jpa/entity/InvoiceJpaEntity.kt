package com.ledgerlite.dataprovider.jpa.entity

import com.ledgerlite.core.domain.vo.InvoiceStatus
import com.ledgerlite.dataprovider.jpa.entity.embeddable.InvoiceItemEmbeddable
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "invoices")
data class InvoiceJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val customerId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: InvoiceStatus,

    @Column(nullable = false)
    val dueDate: LocalDate,

    @ElementCollection
    @CollectionTable(
        name = "invoice_items",
        joinColumns = [JoinColumn(name = "invoice_id")]
    )
    val items: List<InvoiceItemEmbeddable>
)
