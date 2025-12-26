package com.ledgerlite.core.usecase.dto.command

import com.ledgerlite.core.domain.vo.AccountType

data class CreateAccountCommand(
    var name: String,
    var type: AccountType,
)
