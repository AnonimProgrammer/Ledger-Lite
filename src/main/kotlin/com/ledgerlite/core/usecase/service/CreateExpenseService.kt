package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Expense
import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.JournalLine
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.core.usecase.dto.command.CreateExpenseCommand
import com.ledgerlite.core.usecase.dto.result.ExpenseResult
import com.ledgerlite.core.usecase.`in`.CreateExpenseUseCase
import com.ledgerlite.core.usecase.mapper.ExpenseMapper
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.ExpenseRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort
import java.util.logging.Logger

class CreateExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val journalEntryRepository: JournalEntryRepository,
    private val accountRepository: AccountRepository,
    private val transactionPort: TransactionPort,
    private val logger: Logger = Logger.getLogger(CreateExpenseService::class.java.name),
) : CreateExpenseUseCase {

    override fun execute(command: CreateExpenseCommand): ExpenseResult {
        return transactionPort.inTransaction {
            logger.info("Creating expense. Vendor: ${command.vendor}, Amount: ${command.grossAmount}")

            val expenseAccount = accountRepository.findExpenseAccount()
            val cashAccount = accountRepository.findCashAccount()

            val expense = Expense.create(
                vendor = command.vendor,
                grossAmount = Money.of(command.grossAmount),
                taxAmount = Money.of(command.taxAmount),
                date = command.date,
            )
            val journalEntry = JournalEntry.create(
                date = command.date,
                description = "Expense: ${command.vendor}",
                lines = listOf(
                    JournalLine.debit(expenseAccount.id!!, Money.of(command.grossAmount)),
                    JournalLine.credit(cashAccount.id!!, Money.of(command.grossAmount))
                )
            )
            val savedExpense = expenseRepository.save(expense)
            journalEntryRepository.save(journalEntry)

            logger.info("Expense created. ID: ${savedExpense.id}")
            logger.info("Journal entry created. ID: ${journalEntry.id}")

            ExpenseMapper.from(savedExpense)
        }
    }
}
