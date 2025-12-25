package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.dto.command.MarkInvoicePaidCommand

interface MarkInvoicePaidUseCase {
    fun execute(command: MarkInvoicePaidCommand)
}