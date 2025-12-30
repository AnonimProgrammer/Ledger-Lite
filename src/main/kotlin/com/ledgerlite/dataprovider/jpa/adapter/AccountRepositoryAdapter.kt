package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.dataprovider.exception.MissingAccountException
import com.ledgerlite.dataprovider.jpa.mapper.AccountJpaMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringAccountJpaRepository

class AccountRepositoryAdapter(
    private val jpaRepository: SpringAccountJpaRepository,

) : AccountRepository {

    override fun findAll(): List<Account> {
        val entities = jpaRepository.findAll()
        return entities.map { AccountJpaMapper.toDomain(it) }
    }

    override fun findByCode(code: String): Account {
        val entity = jpaRepository.findByCode(code)
            .orElseThrow { MissingAccountException(code) }
        return AccountJpaMapper.toDomain(entity)
    }
}
