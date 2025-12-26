package com.ledgerlite.core.usecase.dto.result

import com.ledgerlite.core.domain.vo.InvoiceStatus
import com.ledgerlite.core.usecase.dto.command.InvoiceItemObject
import java.time.LocalDate
import java.util.UUID

data class InvoiceResult(
    val id: UUID?,
    val customerId: UUID,
    val status: InvoiceStatus,
    val dueDate: LocalDate,
    val items: List<InvoiceItemObject>
)