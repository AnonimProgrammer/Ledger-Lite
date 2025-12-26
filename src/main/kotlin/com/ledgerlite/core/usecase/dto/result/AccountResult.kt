package com.ledgerlite.core.usecase.dto.result

import com.ledgerlite.core.domain.vo.AccountType
import java.util.UUID

data class AccountResult(
    var id: UUID?,
    var name: String,
    var type: AccountType,
)
