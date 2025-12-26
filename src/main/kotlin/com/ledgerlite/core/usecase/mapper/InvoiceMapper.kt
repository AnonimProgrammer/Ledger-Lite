package com.ledgerlite.core.usecase.mapper

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.usecase.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.usecase.dto.command.InvoiceItemObject
import com.ledgerlite.core.usecase.dto.result.InvoiceResult

object InvoiceMapper {

    fun to(command: CreateInvoiceCommand): Invoice =
        Invoice.create(
            customerId = command.customerId,
            dueDate = command.dueDate,
            items = command.items.map { InvoiceItemObject.to(it) }
        )

    fun from(invoice: Invoice): InvoiceResult =
        InvoiceResult(
            id = invoice.id,
            customerId = invoice.customerId,
            status = invoice.status,
            dueDate = invoice.dueDate,
            items = invoice.items.map { InvoiceItemObject.from(it) },
        )
}