package com.ledgerlite.core.domain

import com.ledgerlite.core.domain.error.InvalidInvoiceStateException
import com.ledgerlite.core.domain.vo.InvoiceStatus
import com.ledgerlite.core.domain.vo.InvoiceItem
import com.ledgerlite.core.domain.vo.Money
import java.time.LocalDate
import java.util.UUID

data class Invoice(
    val id: UUID?,
    val customerId: UUID,
    val status: InvoiceStatus,
    val dueDate: LocalDate,
    val items: List<InvoiceItem>
) {

    fun send(): Invoice {
        require(status == InvoiceStatus.DRAFT) {
            InvalidInvoiceStateException("Only DRAFT invoices can be sent.")
        }
        return copy(status = InvoiceStatus.SENT)
    }

    fun markPaid(): Invoice {
        require(status == InvoiceStatus.SENT) {
            InvalidInvoiceStateException("Only SENT invoices can be paid.")
        }
        return copy(status = InvoiceStatus.PAID)
    }

    fun totalAmount(): Money =
        items.fold(Money.zero()) { acc, item ->
            acc + item.unitPrice.times(item.quantity)
        }

    companion object {
        fun create(
            customerId: UUID,
            dueDate: LocalDate,
            items: List<InvoiceItem>
        ): Invoice {
            return Invoice(
                id = null,
                customerId = customerId,
                status = InvoiceStatus.DRAFT,
                dueDate = dueDate,
                items = items
            )
        }
    }
}



