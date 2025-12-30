package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.usecase.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.usecase.dto.result.InvoiceResult
import com.ledgerlite.core.usecase.`in`.CreateInvoiceUseCase
import com.ledgerlite.core.usecase.mapper.InvoiceMapper
import com.ledgerlite.core.usecase.out.InvoiceRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateInvoiceService(
    private val invoiceRepository: InvoiceRepository,
    private val logger: Logger = LoggerFactory.getLogger(CreateInvoiceService::class.java.name),
) : CreateInvoiceUseCase {

    override fun execute(command: CreateInvoiceCommand): InvoiceResult {
        logger.info("Creating invoice. Customer ID: ${command.customerId}")

        val invoice = InvoiceMapper.to(command)
        val savedInvoice = invoiceRepository.save(invoice)
        logger.info("Invoice created. ID: ${savedInvoice.id}")

        return InvoiceMapper.from(savedInvoice)
    }
}
