package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.dataprovider.jpa.mapper.ExpenseJpaMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringExpenseJpaRepository

class ExpenseRepositoryAdapter(
    private val jpaRepository: SpringExpenseJpaRepository
) : ExpenseRepository {

    override fun save(expense: Expense): Expense {
        val entity = ExpenseJpaMapper.toJpa(expense)
        val saved = jpaRepository.save(entity)
        return ExpenseJpaMapper.toDomain(saved)
    }
}
