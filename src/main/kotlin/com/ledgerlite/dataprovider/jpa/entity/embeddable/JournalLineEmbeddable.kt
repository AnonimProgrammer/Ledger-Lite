package com.ledgerlite.dataprovider.jpa.entity.embeddable

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal
import java.util.UUID

@Embeddable
data class JournalLineEmbeddable(

    @Column(name = "account_id", nullable = false)
    val accountId: UUID,

    @Column(nullable = false)
    val debit: BigDecimal,

    @Column(nullable = false)
    val credit: BigDecimal
)
