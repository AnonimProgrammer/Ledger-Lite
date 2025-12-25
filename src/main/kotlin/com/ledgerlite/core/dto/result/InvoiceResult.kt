package com.ledgerlite.core.dto.result

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.domain.vo.InvoiceItem
import com.ledgerlite.core.domain.vo.InvoiceStatus
import java.time.LocalDate
import java.util.UUID

data class InvoiceResult(
    val id: UUID?,
    val customerId: UUID,
    val status: InvoiceStatus,
    val dueDate: LocalDate,
    val items: List<InvoiceItem>
) {
    companion object {
        fun from(invoice: Invoice): InvoiceResult {
            return InvoiceResult(
                id = invoice.id,
                customerId = invoice.customerId,
                status = invoice.status,
                dueDate = invoice.dueDate,
                items = invoice.items,
            )
        }
    }
}