package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.usecase.out.InvoiceRepository
import com.ledgerlite.dataprovider.exception.InvoiceNotFoundException
import com.ledgerlite.dataprovider.jpa.mapper.InvoiceMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringInvoiceJpaRepository
import java.util.UUID

class InvoiceRepositoryAdapter(
    private val jpaRepository: SpringInvoiceJpaRepository
) : InvoiceRepository {

    override fun save(invoice: Invoice): Invoice {
        val entity = InvoiceMapper.toJpa(invoice)
        val saved = jpaRepository.save(entity)
        return InvoiceMapper.toDomain(saved)
    }

    override fun findById(id: UUID): Invoice {
        val entity = jpaRepository.findById(id)
            .orElseThrow {
                InvoiceNotFoundException(id)
            }
        return InvoiceMapper.toDomain(entity)
    }
}
