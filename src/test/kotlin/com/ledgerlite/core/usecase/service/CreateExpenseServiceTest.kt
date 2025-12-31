package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.domain.vo.AccountType
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.core.usecase.dto.command.CreateExpenseCommand
import com.ledgerlite.core.usecase.dto.result.ExpenseResult
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

class CreateExpenseServiceTest {
    private val expenseRepository: ExpenseRepository = mock()
    private val journalEntryRepository: JournalEntryRepository = mock()
    private val accountRepository: AccountRepository = mock()
    private val transactionPort: TransactionPort = mock()

    private val createExpenseService = CreateExpenseService(
        expenseRepository,
        journalEntryRepository,
        accountRepository,
        transactionPort
    )

    @Test
    fun createsExpenseSuccessfully() {
        val command = getCommand()
        val expenseAccount = getExpenseAccount()
        val cashAccount = getCashAccount()
        val savedExpense = getSavedExpense()

        whenever(accountRepository.findExpenseAccount()).thenReturn(expenseAccount)
        whenever(accountRepository.findCashAccount()).thenReturn(cashAccount)
        whenever(transactionPort.inTransaction<ExpenseResult>(any()))
            .thenAnswer { it.getArgument<() -> ExpenseResult>(0).invoke() }

        whenever(expenseRepository.save(any())).thenReturn(savedExpense)
        createExpenseService.execute(command)

        verify(expenseRepository).save(any())
        verify(journalEntryRepository).save(any())
        verify(transactionPort).inTransaction<ExpenseResult>(any())
    }

    private fun getCommand(): CreateExpenseCommand =
        CreateExpenseCommand(
            vendor = "Test Vendor",
            grossAmount = BigDecimal(100.0),
            taxAmount = BigDecimal(10.0),
            date = LocalDate.of(2025, 1, 1)

    )

    private fun getSavedExpense(): Expense =
        Expense.create(
            vendor = "Test Vendor",
            grossAmount = Money.of(BigDecimal.valueOf(100.0)),
            taxAmount = Money.of(BigDecimal.valueOf(10.0)),
            date = LocalDate.of(2025, 1, 1)
        )

    private fun getExpenseAccount(): Account =
        Account(
            id = UUID.randomUUID(),
            name = "Expense Account",
            code = "EXP",
            type = AccountType.EXPENSE,
            isSystemAccount = true,
            currency = "AZN"
        )

    private fun getCashAccount(): Account =
        Account(
            id = UUID.randomUUID(),
            name = "Cash Account",
            code = "CASH",
            type = AccountType.ASSET,
            isSystemAccount = true,
            currency = "AZN"
        )
}