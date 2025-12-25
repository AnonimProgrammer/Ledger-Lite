package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.dto.result.InvoiceResult

interface CreateInvoiceUseCase {
    fun execute(command: CreateInvoiceCommand): InvoiceResult
}