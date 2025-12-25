package com.ledgerlite.core.domain

import com.ledgerlite.core.domain.vo.AccountType
import java.util.UUID

data class Account(
    var id: UUID?,
    var name: String,
    var type: AccountType,
) {
    companion object {
        fun create(
            name: String,
            type: AccountType
        ) :Account =
            Account(
                id = null,
                name = name,
                type = type
            )
    }
}