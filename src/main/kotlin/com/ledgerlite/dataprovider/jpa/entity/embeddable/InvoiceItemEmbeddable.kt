package com.ledgerlite.dataprovider.jpa.entity.embeddable

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class InvoiceItemEmbeddable(

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val unitPrice: BigDecimal
)
