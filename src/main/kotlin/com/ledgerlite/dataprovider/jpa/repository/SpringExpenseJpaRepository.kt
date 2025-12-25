package com.ledgerlite.dataprovider.jpa.repository

import com.ledgerlite.dataprovider.jpa.entity.ExpenseJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringExpenseJpaRepository : JpaRepository<ExpenseJpaEntity, Long> {
}