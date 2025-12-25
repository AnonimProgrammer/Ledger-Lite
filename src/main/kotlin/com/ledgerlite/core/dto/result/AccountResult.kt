package com.ledgerlite.core.dto.result

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.domain.vo.AccountType
import java.util.UUID

data class AccountResult(
    var id: UUID?,
    var name: String,
    var type: AccountType,
) {
    companion object {
        fun from(account: Account): AccountResult {
            return AccountResult(
                id = account.id,
                name = account.name,
                type = account.type
            )
        }
    }
}
