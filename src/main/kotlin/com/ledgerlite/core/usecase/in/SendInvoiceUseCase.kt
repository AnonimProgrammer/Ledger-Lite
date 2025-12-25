package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.dto.command.SendInvoiceCommand

interface SendInvoiceUseCase {
    fun execute(command: SendInvoiceCommand)
}