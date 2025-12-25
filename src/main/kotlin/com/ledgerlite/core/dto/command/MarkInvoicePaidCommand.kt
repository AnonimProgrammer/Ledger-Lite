package com.ledgerlite.core.dto.command

import java.util.UUID

data class MarkInvoicePaidCommand(
    val invoiceId: UUID
)