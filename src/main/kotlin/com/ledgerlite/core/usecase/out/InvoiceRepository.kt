package com.ledgerlite.core.usecase.out

import com.ledgerlite.core.domain.Invoice
import java.util.UUID

interface InvoiceRepository {

    fun findById(id: UUID): Invoice

    fun save(invoice: Invoice): Invoice
}
