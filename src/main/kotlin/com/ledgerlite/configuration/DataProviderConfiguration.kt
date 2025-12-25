package com.ledgerlite.configuration

import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.core.usecase.out.InvoiceRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort
import com.ledgerlite.dataprovider.jpa.adapter.AccountRepositoryAdapter
import com.ledgerlite.dataprovider.jpa.adapter.ExpenseRepositoryAdapter
import com.ledgerlite.dataprovider.jpa.adapter.InvoiceRepositoryAdapter
import com.ledgerlite.dataprovider.jpa.adapter.JournalEntryRepositoryAdapter
import com.ledgerlite.dataprovider.jpa.adapter.TransactionAdapter
import com.ledgerlite.dataprovider.jpa.repository.SpringAccountJpaRepository
import com.ledgerlite.dataprovider.jpa.repository.SpringExpenseJpaRepository
import com.ledgerlite.dataprovider.jpa.repository.SpringInvoiceJpaRepository
import com.ledgerlite.dataprovider.jpa.repository.SpringJournalEntryJpaRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataProviderConfiguration {

    @Bean
    fun transactionPort(): TransactionPort = TransactionAdapter()

    @Bean
    fun invoiceRepository(
        jpaRepo: SpringInvoiceJpaRepository
    ): InvoiceRepository = InvoiceRepositoryAdapter(jpaRepo)

    @Bean
    fun expenseRepository(
        jpaRepo: SpringExpenseJpaRepository
    ): ExpenseRepository = ExpenseRepositoryAdapter(jpaRepo)

    @Bean
    fun accountRepository(
        jpaRepo: SpringAccountJpaRepository
    ): AccountRepository = AccountRepositoryAdapter(jpaRepo)

    @Bean
    fun journalEntryRepository(
        jpaRepo: SpringJournalEntryJpaRepository
    ): JournalEntryRepository = JournalEntryRepositoryAdapter(jpaRepo)
}