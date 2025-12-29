package com.ledgerlite.dataprovider.jpa.entity

import com.ledgerlite.core.domain.vo.AccountType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.util.UUID

@Entity
@Table(
    name = "accounts",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["code"]),
        UniqueConstraint(columnNames = ["name"])
    ]
)
data class AccountJpaEntity(

    @Id
    val id: UUID,

    @Column(nullable = false, updatable = false)
    val code: String,

    @Column(nullable = false, updatable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    val type: AccountType,

    @Column(nullable = false, updatable = false)
    val isSystemAccount: Boolean,

    @Column(nullable = false, updatable = false)
    val currency: String,
)

