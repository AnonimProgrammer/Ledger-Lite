package com.ledgerlite.dataprovider.jpa.mapper

import com.ledgerlite.core.domain.Account
import com.ledgerlite.dataprovider.jpa.entity.AccountJpaEntity

object AccountJpaMapper {

    fun toDomain(entity: AccountJpaEntity): Account =
        Account(
            id = entity.id,
            code = entity.code,
            name = entity.name,
            type = entity.type,
            isSystemAccount = entity.isSystemAccount,
            currency = entity.currency,
        )
}
