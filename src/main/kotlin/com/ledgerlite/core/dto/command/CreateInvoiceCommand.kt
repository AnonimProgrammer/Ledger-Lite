package com.ledgerlite.core.dto.command

import com.ledgerlite.core.domain.vo.InvoiceItem
import java.time.LocalDate
import java.util.UUID

data class CreateInvoiceCommand(
    val customerId: UUID,
    val dueDate: LocalDate,
    val items: List<InvoiceItem>
)
