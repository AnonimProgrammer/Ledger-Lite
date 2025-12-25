package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.JournalEntry
import com.ledgerlite.core.domain.JournalLine
import com.ledgerlite.core.dto.command.SendInvoiceCommand
import com.ledgerlite.core.usecase.`in`.SendInvoiceUseCase
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.InvoiceRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import com.ledgerlite.core.usecase.out.TransactionPort
import java.time.LocalDate

class SendInvoiceService(
    private val invoiceRepository: InvoiceRepository,
    private val journalEntryRepository: JournalEntryRepository,
    private val accountRepository: AccountRepository,
    private val transactionPort: TransactionPort
) : SendInvoiceUseCase {

    override fun execute(command: SendInvoiceCommand) {
        transactionPort.inTransaction {

            val invoice = invoiceRepository.findById(command.invoiceId)
            val sentInvoice = invoice.send()

            val arAccount = accountRepository.findAccountsReceivable()
            val revenueAccount = accountRepository.findRevenueAccount()

            val totalAmount = sentInvoice.totalAmount()

            val journalEntry = JournalEntry.create(
                date = LocalDate.now(),
                description = "Invoice sent: ${sentInvoice.id}",
                lines = listOf(
                    JournalLine.debit(arAccount.id!!, totalAmount),
                    JournalLine.credit(revenueAccount.id!!, totalAmount)
                )
            )

            invoiceRepository.save(sentInvoice)
            journalEntryRepository.save(journalEntry)
        }
    }
}
