package com.ledgerlite.core.usecase.dto.command

import java.time.LocalDate
import java.util.UUID

data class CreateInvoiceCommand(
    val customerId: UUID,
    val dueDate: LocalDate,
    val items: List<InvoiceItemObject>
)