package com.ledgerlite.core.usecase.dto.command

import java.util.UUID

data class SendInvoiceCommand(
    val invoiceId: UUID
)