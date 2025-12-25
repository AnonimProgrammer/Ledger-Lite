package com.ledgerlite.dataprovider.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "expenses")
data class ExpenseJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val vendor: String,

    @Column(nullable = false)
    val grossAmount: BigDecimal,

    @Column(nullable = false)
    val taxAmount: BigDecimal,

    @Column(nullable = false)
    val date: LocalDate,
)