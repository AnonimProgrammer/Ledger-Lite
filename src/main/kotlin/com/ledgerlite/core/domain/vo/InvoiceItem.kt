package com.ledgerlite.core.domain.vo

data class InvoiceItem(
    val description: String,
    val quantity: Int,
    val unitPrice: Money
)