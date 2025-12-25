package com.ledgerlite.dataprovider.jpa.mapper

import com.ledgerlite.core.domain.Account
import com.ledgerlite.dataprovider.jpa.entity.AccountJpaEntity

object AccountMapper {

    fun toDomain(entity: AccountJpaEntity): Account =
        Account(
            id = entity.id,
            name = entity.name,
            type = entity.type
        )

    fun toJpa(domain: Account): AccountJpaEntity =
        AccountJpaEntity(
            id = domain.id,
            name = domain.name,
            type = domain.type
        )
}
