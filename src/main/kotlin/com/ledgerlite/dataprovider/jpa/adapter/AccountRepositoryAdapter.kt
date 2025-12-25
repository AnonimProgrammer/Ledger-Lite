package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.domain.vo.AccountType
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.dataprovider.exception.MissingAccountException
import com.ledgerlite.dataprovider.jpa.mapper.AccountMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringAccountJpaRepository

class AccountRepositoryAdapter(
    private val jpaRepository: SpringAccountJpaRepository
) : AccountRepository {

    override fun save(account: Account): Account {
        val entity = AccountMapper.toJpa(account)
        val saved = jpaRepository.save(entity)
        return AccountMapper.toDomain(saved)
    }

    override fun findCashAccount(): Account =
        findRequired(AccountType.ASSET, "Cash")

    override fun findExpenseAccount(): Account =
        findRequired(AccountType.EXPENSE, "Expense")

    override fun findAccountsReceivable(): Account =
        findRequired(AccountType.ASSET, "Accounts Receivable")

    override fun findRevenueAccount(): Account =
        findRequired(AccountType.REVENUE, "Revenue")

    private fun findRequired(type: AccountType, name: String): Account {
        val entity = jpaRepository.findByNameAndType(name, type)
            .orElseThrow { MissingAccountException(name) }

        return AccountMapper.toDomain(entity)
    }
}
