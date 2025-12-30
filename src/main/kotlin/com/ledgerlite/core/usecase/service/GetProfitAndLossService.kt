package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.domain.vo.AccountType
import com.ledgerlite.core.domain.vo.JournalLine
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.core.usecase.dto.result.ProfitAndLossResult
import com.ledgerlite.core.usecase.dto.result.vo.ProfitAndLossLine
import com.ledgerlite.core.usecase.`in`.GetProfitAndLossUseCase
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.util.UUID

class GetProfitAndLossService(
    private val accountRepository: AccountRepository,
    private val journalEntryRepository: JournalEntryRepository,
    private val logger: Logger = LoggerFactory.getLogger(GetProfitAndLossService::class.java)
) : GetProfitAndLossUseCase {

    override fun execute(from: LocalDate, to: LocalDate): ProfitAndLossResult {
        logger.info("Getting P&L. Period: from $from to $to")

        val accounts = accountRepository.findAll()
        val lines = journalEntryRepository.findJournalLinesBetween(from, to)

        return buildResult(from, to, accounts, lines)
    }

    private fun buildResult(
        from: LocalDate,
        to: LocalDate,
        accounts: List<Account>,
        lines: List<JournalLine>
    ): ProfitAndLossResult {
        val groupedLines = lines.groupBy { it.accountId }

        val revenueLines = buildLines(accounts, groupedLines, AccountType.REVENUE)
        val expenseLines = buildLines(accounts, groupedLines, AccountType.EXPENSE)

        val totalRevenue = revenueLines.sumOf { it.amount }
        val totalExpense = expenseLines.sumOf { it.amount }
        val netProfit = totalRevenue.subtract(totalExpense)

        logger.info("P&L built. Total Revenue: $totalRevenue, Total Expense: $totalExpense, Net Profit: $netProfit")
        return ProfitAndLossResult(
            from = from,
            to = to,
            revenueLines = revenueLines,
            expenseLines = expenseLines,
            totalRevenue = totalRevenue,
            totalExpense = totalExpense,
            netProfit = netProfit
        )
    }

    private fun buildLines(
        accounts: List<Account>,
        groupedLines: Map<UUID, List<JournalLine>>,
        type: AccountType
    ): List<ProfitAndLossLine> =
        accounts
            .filter { it.type == type }
            .mapNotNull { account ->
                val accountLines = groupedLines[account.id].orEmpty()
                if (accountLines.isEmpty()) return@mapNotNull null

                val debit = accountLines.fold(Money.zero()) { acc, line -> acc + line.debit }
                val credit = accountLines.fold(Money.zero()) { acc, line -> acc + line.credit }

                val amount = (credit - debit).absolute()
                ProfitAndLossLine(
                    accountCode = account.code,
                    accountName = account.name,
                    amount = amount.amount
                )
            }
}
