package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.usecase.out.InvoiceRepository
import com.ledgerlite.dataprovider.exception.InvoiceNotFoundException
import com.ledgerlite.dataprovider.jpa.mapper.InvoiceJpaMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringInvoiceJpaRepository
import java.util.UUID

class InvoiceRepositoryAdapter(
    private val jpaRepository: SpringInvoiceJpaRepository
) : InvoiceRepository {

    override fun save(invoice: Invoice): Invoice {
        val entity = InvoiceJpaMapper.toJpa(invoice)
        val saved = jpaRepository.save(entity)
        return InvoiceJpaMapper.toDomain(saved)
    }

    override fun findById(id: UUID): Invoice {
        val entity = jpaRepository.findById(id)
            .orElseThrow {
                InvoiceNotFoundException(id)
            }
        return InvoiceJpaMapper.toDomain(entity)
    }
}
