package com.ledgerlite.core.usecase.dto.command

import com.ledgerlite.core.domain.vo.InvoiceItem
import com.ledgerlite.core.domain.vo.Money
import java.math.BigDecimal

data class InvoiceItemObject(
    val description: String,
    val quantity: Int,
    val unitPrice: BigDecimal
) {
    companion object {
        fun to(request: InvoiceItemObject) =
            InvoiceItem(
                description = request.description,
                quantity = request.quantity,
                unitPrice = Money.of(request.unitPrice)
            )

        fun from(item: InvoiceItem) =
            InvoiceItemObject(
                description = item.description,
                quantity = item.quantity,
                unitPrice = item.unitPrice.amount
            )
    }
}