package com.ledgerlite.core.usecase.dto.command

import java.util.UUID

data class MarkInvoicePaidCommand(
    val invoiceId: UUID
)