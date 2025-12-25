package com.ledgerlite.dataprovider.jpa.repository

import com.ledgerlite.dataprovider.jpa.entity.InvoiceJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringInvoiceJpaRepository : JpaRepository<InvoiceJpaEntity, UUID> {
}