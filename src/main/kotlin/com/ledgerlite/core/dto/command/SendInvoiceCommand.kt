package com.ledgerlite.core.dto.command

import java.util.UUID

data class SendInvoiceCommand(
    val invoiceId: UUID
)