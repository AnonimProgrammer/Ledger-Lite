package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.usecase.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.usecase.dto.result.InvoiceResult

interface CreateInvoiceUseCase {
    fun execute(command: CreateInvoiceCommand): InvoiceResult
}