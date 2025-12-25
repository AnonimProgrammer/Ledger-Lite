package com.ledgerlite.dataprovider.jpa.adapter

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.dataprovider.jpa.mapper.ExpenseMapper
import com.ledgerlite.dataprovider.jpa.repository.SpringExpenseJpaRepository

class ExpenseRepositoryAdapter(
    private val jpaRepository: SpringExpenseJpaRepository
) : ExpenseRepository {

    override fun save(expense: Expense): Expense {
        val entity = ExpenseMapper.toJpa(expense)
        val saved = jpaRepository.save(entity)
        return ExpenseMapper.toDomain(saved)
    }
}
