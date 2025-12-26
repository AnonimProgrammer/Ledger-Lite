package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.usecase.dto.command.MarkInvoicePaidCommand

interface MarkInvoicePaidUseCase {
    fun execute(command: MarkInvoicePaidCommand)
}