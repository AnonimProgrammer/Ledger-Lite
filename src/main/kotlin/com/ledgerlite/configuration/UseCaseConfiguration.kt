package com.ledgerlite.configuration

import com.ledgerlite.core.usecase.`in`.CreateExpenseUseCase
import com.ledgerlite.core.usecase.`in`.CreateInvoiceUseCase
import com.ledgerlite.core.usecase.`in`.GetTrialBalanceUseCase
import com.ledgerlite.core.usecase.`in`.MarkInvoicePaidUseCase
import com.ledgerlite.core.usecase.`in`.SendInvoiceUseCase
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.core.usecase.out.InvoiceRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort
import com.ledgerlite.core.usecase.service.CreateExpenseService
import com.ledgerlite.core.usecase.service.CreateInvoiceService
import com.ledgerlite.core.usecase.service.GetTrialBalanceService
import com.ledgerlite.core.usecase.service.MarkInvoicePaidService
import com.ledgerlite.core.usecase.service.SendInvoiceService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun createExpenseUseCase(
        expenseRepository: ExpenseRepository,
        journalEntryRepository: JournalEntryRepository,
        accountRepository: AccountRepository,
        transactionPort: TransactionPort
    ): CreateExpenseUseCase = CreateExpenseService(
        expenseRepository = expenseRepository,
        journalEntryRepository = journalEntryRepository,
        accountRepository = accountRepository,
        transactionPort = transactionPort,
    )

    @Bean
    fun createInvoiceUseCase(
        invoiceRepository: InvoiceRepository,
    ): CreateInvoiceUseCase = CreateInvoiceService(invoiceRepository)

    @Bean
    fun sendInvoiceUseCase(
        invoiceRepository: InvoiceRepository,
        journalEntryRepository: JournalEntryRepository,
        accountRepository: AccountRepository,
        transactionPort: TransactionPort
    ): SendInvoiceUseCase = SendInvoiceService(
        invoiceRepository = invoiceRepository,
        journalEntryRepository = journalEntryRepository,
        accountRepository = accountRepository,
        transactionPort = transactionPort,
    )

    @Bean
    fun markInvoicePaidUseCase(
        invoiceRepository: InvoiceRepository,
        journalEntryRepository: JournalEntryRepository,
        accountRepository: AccountRepository,
        transactionPort: TransactionPort
    ): MarkInvoicePaidUseCase = MarkInvoicePaidService(
        invoiceRepository = invoiceRepository,
        journalEntryRepository = journalEntryRepository,
        accountRepository = accountRepository,
        transactionPort = transactionPort,
    )

    @Bean
    fun getTrialBalanceUseCase(
        accountRepository: AccountRepository,
        journalEntryRepository: JournalEntryRepository
    ): GetTrialBalanceUseCase = GetTrialBalanceService(
        accountRepository = accountRepository,
        journalEntryRepository = journalEntryRepository,
    )
}