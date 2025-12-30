package com.ledgerlite.core.usecase.dto.result.vo

import com.ledgerlite.core.domain.vo.AccountType
import java.math.BigDecimal
import java.util.UUID

data class TrialBalanceLine(
    val accountId: UUID,
    val accountCode: String,
    val accountName: String,
    val accountType: AccountType,
    val isSystemAccount: Boolean,
    val currency: String,
    val debitTotal: BigDecimal,
    val creditTotal: BigDecimal,
    val balance: BigDecimal,
    val balanceSide: BalanceSide
)
