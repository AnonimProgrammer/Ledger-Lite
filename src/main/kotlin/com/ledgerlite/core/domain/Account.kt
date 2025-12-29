package com.ledgerlite.core.domain

import com.ledgerlite.core.domain.vo.AccountType
import java.util.UUID

data class Account(
    val id: UUID,
    val code: String,
    val name: String,
    val type: AccountType,
    val isSystemAccount: Boolean,
    val currency: String,
)