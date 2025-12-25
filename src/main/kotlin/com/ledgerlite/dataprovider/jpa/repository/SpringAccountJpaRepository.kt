package com.ledgerlite.dataprovider.jpa.repository

import com.ledgerlite.core.domain.vo.AccountType
import com.ledgerlite.dataprovider.jpa.entity.AccountJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface SpringAccountJpaRepository : JpaRepository<AccountJpaEntity, UUID> {

    fun findByNameAndType(name: String, type: AccountType): Optional<AccountJpaEntity>
}
