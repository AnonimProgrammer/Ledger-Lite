package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.JournalLine
import com.ledgerlite.core.dto.command.MarkInvoicePaidCommand
import com.ledgerlite.core.usecase.`in`.MarkInvoicePaidUseCase
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.InvoiceRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort
import java.time.LocalDate

class MarkInvoicePaidService(
    private val invoiceRepository: InvoiceRepository,
    private val journalEntryRepository: JournalEntryRepository,
    private val accountRepository: AccountRepository,
    private val transactionPort: TransactionPort
) : MarkInvoicePaidUseCase {

    override fun execute(command: MarkInvoicePaidCommand) {
        transactionPort.inTransaction {

            val invoice = invoiceRepository.findById(command.invoiceId)
            val paidInvoice = invoice.markPaid()

            val cashAccount = accountRepository.findCashAccount()
            val arAccount = accountRepository.findAccountsReceivable()

            val totalAmount = paidInvoice.totalAmount()

            val journalEntry = JournalEntry.create(
                date = LocalDate.now(),
                description = "Invoice paid: ${paidInvoice.id}",
                lines = listOf(
                    JournalLine.debit(cashAccount.id!!, totalAmount),
                    JournalLine.credit(arAccount.id!!, totalAmount)
                )
            )

            invoiceRepository.save(paidInvoice)
            journalEntryRepository.save(journalEntry)
        }
    }
}
