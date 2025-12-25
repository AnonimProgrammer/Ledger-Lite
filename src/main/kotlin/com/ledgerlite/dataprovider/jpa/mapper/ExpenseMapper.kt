package com.ledgerlite.dataprovider.jpa.mapper

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.dataprovider.jpa.entity.ExpenseJpaEntity

object ExpenseMapper {

    fun toDomain(entity: ExpenseJpaEntity): Expense {
        val expense = Expense.create(
            entity.vendor,
            Money.of(entity.grossAmount),
            Money.of(entity.taxAmount),
            entity.date,
        )
        expense.id = entity.id
        return expense
    }

    fun toJpa(domain: Expense): ExpenseJpaEntity =
        ExpenseJpaEntity(
            id = domain.id,
            vendor = domain.vendor,
            grossAmount = domain.grossAmount.amount,
            taxAmount = domain.taxAmount.amount,
            date = domain.date,
        )
}