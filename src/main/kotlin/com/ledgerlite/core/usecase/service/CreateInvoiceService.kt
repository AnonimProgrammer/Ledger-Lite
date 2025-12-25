package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.dto.result.InvoiceResult
import com.ledgerlite.core.usecase.`in`.CreateInvoiceUseCase
import com.ledgerlite.core.usecase.out.InvoiceRepository

class CreateInvoiceService(
    private val invoiceRepository: InvoiceRepository
) : CreateInvoiceUseCase {

    override fun execute(command: CreateInvoiceCommand): InvoiceResult {
        val invoice = Invoice.create(
            customerId = command.customerId,
            dueDate = command.dueDate,
            items = command.items
        )

        val saved = invoiceRepository.save(invoice)
        return InvoiceResult.from(saved)
    }
}
