package com.ledgerlite.dataprovider.jpa.repository

import com.ledgerlite.core.domain.vo.AccountType
import com.ledgerlite.dataprovider.jpa.entity.AccountJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringAccountJpaRepository : JpaRepository<AccountJpaEntity, UUID> {

    fun findByType(type: AccountType): List<AccountJpaEntity>

    fun findByName(name: String): AccountJpaEntity?
}
