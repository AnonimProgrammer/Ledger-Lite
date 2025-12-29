package com.ledgerlite.core.usecase.mapper

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.usecase.dto.result.AccountResult

object AccountMapper {

    fun from(account: Account): AccountResult =
        AccountResult(
            id = account.id,
            code = account.code,
            name = account.name,
            type = account.type,
            isSystemAccount = account.isSystemAccount,
            currency = account.currency,
        )
}