package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.JournalLine
import com.ledgerlite.core.dto.command.CreateExpenseCommand
import com.ledgerlite.core.dto.result.ExpenseResult
import com.ledgerlite.core.usecase.`in`.CreateExpenseUseCase
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort

class CreateExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val journalEntryRepository: JournalEntryRepository,
    private val accountRepository: AccountRepository,
    private val transactionPort: TransactionPort
) : CreateExpenseUseCase {

    override fun execute(command: CreateExpenseCommand): ExpenseResult {
        return transactionPort.inTransaction {

            val expenseAccount = accountRepository.findExpenseAccount()
            val cashAccount = accountRepository.findCashAccount()

            val expense = Expense.create(
                vendor = command.vendor,
                grossAmount = command.grossAmount,
                taxAmount = command.taxAmount,
                date = command.date,
            )

            val journalEntry = JournalEntry.create(
                date = command.date,
                description = "Expense: ${command.vendor}",
                lines = listOf(
                    JournalLine.debit(expenseAccount.id!!, command.grossAmount),
                    JournalLine.credit(cashAccount.id!!, command.grossAmount)
                )
            )

            val savedExpense = expenseRepository.save(expense)
            journalEntryRepository.save(journalEntry)

            ExpenseResult.from(savedExpense)
        }
    }
}
